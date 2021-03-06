package diethelper.com.diethelper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import Entity.Diet;
import Entity.User;

public class DietActivity extends AppCompatActivity {

    TextView lunch, breakfast, dinner;
    CardView c1, c2, c3;

    private static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        c1 = (CardView) findViewById(R.id.card_view1);
        c2 = (CardView) findViewById(R.id.card_view2);
        c3 = (CardView) findViewById(R.id.card_view3);

        Intent in = getIntent();
        if (in.hasExtra("notifytype")) {
            if (in.getStringExtra("notifytype").contentEquals("b")) {
                c2.setVisibility(View.GONE);
                c3.setVisibility(View.GONE);
            } else if (in.getStringExtra("notifytype").contentEquals("l")) {
                c1.setVisibility(View.GONE);
                c3.setVisibility(View.GONE);
            } else if (in.getStringExtra("notifytype").contentEquals("d")) {
                c1.setVisibility(View.GONE);
                c2.setVisibility(View.GONE);
            }

        }
        final SharedPreferences sharedpreferences = getSharedPreferences("Diet", Context.MODE_PRIVATE);
        lunch = (TextView) findViewById(R.id.lunch);
        breakfast = (TextView) findViewById(R.id.breakfast);
        dinner = (TextView) findViewById(R.id.dinner);

        long id = sharedpreferences.getLong("userid", 1);
        User u = User.findById(User.class, id);
        String disease = "";
        if (u.dis1) {
            disease = disease + " or diettype='" + getResources().getString(R.string.dis1) + "'";
        }
        if (u.dis2) {
            disease = disease + " or diettype='" + getResources().getString(R.string.dis2) + "'";
        }
        if (u.dis3) {
            disease = disease + " or diettype='" + getResources().getString(R.string.dis3) + "'";
        }
        if (u.dis4) {
            disease = disease + " or diettype='" + getResources().getString(R.string.dis4) + "'";
        }
        if (u.dis5) {
            disease = disease + " or diettype='" + getResources().getString(R.string.dis5) + "'";
        }
        if (u.dis6) {
            disease = disease + " or diettype='" + getResources().getString(R.string.dis6) + "'";
        }
        String output = disease.replaceFirst("or", "");

        Log.d("test", disease);
        String l = "", b = "", d = "", b1 = "", d1 = "", l1 = "";

        List<Diet> blist = Diet.findWithQuery(Diet.class, "Select * from  Diet where period = ? and  (" + output + ")"
                , "Breakfast");
        for (Diet dd : blist) {
            if (!b.contains(dd.food))
                b = b + " " + dd.food + ",";
            else
                b1 = b1 + " " + dd.food + ",";
        }

        List<Diet> dlist = Diet.findWithQuery(Diet.class, "Select * from  Diet where period = ? and  (" + output + ")"
                , "Dinner");
        for (Diet dd : dlist) {
            if (!d.contains(dd.food))
                d = d + " " + dd.food + ",";
            else
                d1 = d1 + " " + dd.food + ",";
        }

        List<Diet> llist = Diet.findWithQuery(Diet.class, "Select * from  Diet where period = ? and  (" + output + ")"
                , "Lunch");
        for (Diet dd : llist) {
            if (!l.contains(dd.food))
                l = l + " " + dd.food + ",";
            else
                l1 = l1 + " " + dd.food + ",";
        }

        if (b1.equals("")) {
            b = removeLastChar(b);
            breakfast.setText(b);
        } else {
            b1 = removeLastChar(b1);
            breakfast.setText(b1);
        }

        if (l1.equals("")) {
            l = removeLastChar(l);
            lunch.setText(l);
        } else {
            l1 = removeLastChar(l1);
            lunch.setText(l1);
        }

        if (d1.equals("")) {
            d = removeLastChar(d);
            dinner.setText(d);
        } else {
            d1 = removeLastChar(d1);
            dinner.setText(d1);
        }

    }
}