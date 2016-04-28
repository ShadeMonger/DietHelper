package diethelper.com.diethelper;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.text.format.Time;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Entity.Notify;

public class NotificationService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public NotificationService(String name) {
        super(name);
    }

    public NotificationService() {
        super("NotificationService");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Toast.makeText(this, "Service hit!", Toast.LENGTH_LONG).show();

        int h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int m = Calendar.getInstance().get(Calendar.MINUTE);
        int hour = (h * 100) + m;
        Date d = new Date();
        CharSequence s = DateFormat.format("MMMMdyyyy ", d.getTime());
        if (hour == 800) {
            List<Notify> notify = Notify.findWithQuery(Notify.class, "select * from Notify where date='" + s + "' and period='b'");
            if (notify.size() == 0) {
                Notify("Diet Manager", "Hey its time to take your breakfast for the day.Tap to view diet.", "b");
                new Notify(s + "", "b", true).save();

            }
        } else if (hour == 1300) {
            List<Notify> notify = Notify.findWithQuery(Notify.class, "select * from Notify where date='" + s + "' and period='l'");
            if (notify.size() == 0) {
                Notify("Diet Manager", "Hey its time to take your lunch for the day.Tap to view diet.", "l");
                new Notify(s + "", "l", true).save();

            }
        }

        if (hour == 2100) {
            List<Notify> notify = Notify.findWithQuery(Notify.class, "select * from Notify where date='" + s + "' and period='d'");
            if (notify.size() == 0) {
                Notify("Diet Manager", "Hey its time to take your dinner for the day.Tap to view diet.", "d");
                new Notify(s + "", "d", true).save();

            }
        }

        scheduleNextUpdate();
    }

    private void Notify(String notificationTitle, String notificationMessage, String type) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        @SuppressWarnings("deprecation")

        Intent notificationIntent = new Intent(this, DietActivity.class);
        notificationIntent.putExtra("notifytype", type);
        notificationIntent.setAction(Long.toString(System.currentTimeMillis()));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification.Builder builder = new Notification.Builder(NotificationService.this);
        builder.setSmallIcon(R.drawable.healthdiet)
                .setContentTitle(notificationTitle)
                .setContentText(notificationMessage)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
        Notification notification = builder.build();
        notificationManager.notify(R.drawable.notification_template_icon_bg, notification);
    }

    private void scheduleNextUpdate() {
        Intent intent = new Intent(this, this.getClass());
        PendingIntent pendingIntent =
                PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // The update frequency should often be user configurable.  This is not.

        long currentTimeMillis = System.currentTimeMillis();
        long nextUpdateTimeMillis = currentTimeMillis + 5000;
        Time nextUpdateTime = new Time();
        nextUpdateTime.set(nextUpdateTimeMillis);

        if (nextUpdateTime.hour < 8 || nextUpdateTime.hour >= 20) {
            nextUpdateTime.hour = 8;
            nextUpdateTime.minute = 0;
            nextUpdateTime.second = 0;
            nextUpdateTimeMillis = nextUpdateTime.toMillis(false) + DateUtils.DAY_IN_MILLIS;
        }
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, nextUpdateTimeMillis, pendingIntent);
    }
}
