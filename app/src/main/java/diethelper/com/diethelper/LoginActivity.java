package diethelper.com.diethelper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import Entity.Diet;
import Entity.User;

public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        seedFunction();
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        final SharedPreferences sharedpreferences = getSharedPreferences("Diet", Context.MODE_PRIVATE);
        if (sharedpreferences.getBoolean("logged", false)) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().length() == 0 || password.getText().toString().length() == 0) {

                    Toast.makeText(getApplicationContext(), "Username/Password required!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<User> log = User.find(User.class, "username = ? and password = ?", username.getText().toString(), password.getText().toString());
                if (log.size() == 0) {
                    Toast.makeText(getApplicationContext(), "Invalid credentials!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(), "Welcome " + log.get(0).name, Toast.LENGTH_SHORT).show();
                sharedpreferences.edit().putBoolean("logged", true).apply();
                sharedpreferences.edit().putLong("userid", log.get(0).getId()).apply();

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                Intent myIntent = new Intent(LoginActivity.this, NotificationService.class);
                startService(myIntent);
                finish();
            }
        });

    }

    private void seedFunction() {
        List<String> dis1breakfast = Arrays.asList("Pancakes", "Oatmeal", "Corn Flakes", "Berries", "Non-Fat Yogurt");
        List<String> dis1lunch = Arrays.asList("Cheese & Veggie Pitas", "Rice Soup", "Vegetable Salad with Lettuce", "Bread & Beef Sandwich");
        List<String> dis1dinner = Arrays.asList("Chicken Soup", "Brown Rice", "Sweet Potato Chips", "Cucumber Salad");

        List<String> dis2breakfast = Arrays.asList("Tomato Carrot Juice", "Orange Juice", "Idlis with Coconut Chutney", "Dosa with Sambar");
        List<String> dis2lunch = Arrays.asList("Bread & Beef Sandwich", "Strawberries & Carrot Sticks", "Mushrooms & Steamed Rice");
        List<String> dis2dinner = Arrays.asList("Roasted Chicken Breast", "Chicken Soup", "Steamed Broccoli", "Lima Beans");

        List<String> dis3breakfast = Arrays.asList("Orange Juice", "Berries", "Corn Flakes", "Oats");
        List<String> dis3lunch = Arrays.asList("Green Salad", "Tomato soup", "Low-Fat Kiwi Yogurt");
        List<String> dis3dinner = Arrays.asList("Steamed Broccoli", "Papaya Salad", "Mango Pudding");

        List<String> dis4breakfast = Arrays.asList("Bananas", "Pulp Free Fruit Juice", "Canned Fruits ", "Corn Flakes");
        List<String> dis4lunch = Arrays.asList("White Rice", "Pasta", "Noodles", "Bread Sandwich", "Oatmeal");
        List<String> dis4dinner = Arrays.asList("Milk", "Salmon & Eggs", "Asparagus", "White Bread");

        List<String> dis5breakfast = Arrays.asList("Corn Flakes", "Banana Cake", "Orange Juice", "Low Fat Popcorn");
        List<String> dis5lunch = Arrays.asList("Tuna Salad Sandwich", "Low Sodium Vegetable Soup", "Apple & Diet Soda", "Carrots");
        List<String> dis5dinner = Arrays.asList("Salmon", "Pineapple Salsa", "Blue Cheese", "Cherry Salad", "Brown Rice");

        List<String> dis6breakfast = Arrays.asList("Citrus Fruits", "Egg Yolks Juice", "Oats", "Tomato Soup");
        List<String> dis6lunch = Arrays.asList("Vegetable Salad with Lettuce", "Tuna Salad Sandwich", "Seasoned Tuna", "Berries");
        List<String> dis6dinner = Arrays.asList("Salmon & Eggs", "Plain Yogurt & RIce", "Sweet Potato Chips", "Shrimp/Prawn");

        List<User> users = User.listAll(User.class);
        // new Notify("","l",true).save();

        if (users.size() == 0) {

            User u = new User();
            u.name = "test1";
            u.username = "test1";
            u.password = "pass";
            u.email = "test1@gmail.com";
            u.age = 22;
            u.dis1 = true;
            u.save();

            User u1 = new User();
            u1.name = "test2";
            u1.username = "test2";
            u1.password = "pass";
            u1.email = "test2@gmail.com";
            u1.age = 20;
            u1.dis2 = true;
            u1.save();

            for (String s : dis1breakfast) {
                Diet d = new Diet();
                d.diettype = getResources().getString(R.string.dis1);
                d.period = "Breakfast";
                d.food = s;
                d.save();

            }

            for (String s : dis2breakfast) {
                Diet d = new Diet();
                d.diettype = getResources().getString(R.string.dis2);
                d.period = "Breakfast";
                d.food = s;
                d.save();

            }

            for (String s : dis3breakfast) {
                Diet d = new Diet();
                d.diettype = getResources().getString(R.string.dis3);
                d.period = "Breakfast";
                d.food = s;
                d.save();

            }

            for (String s : dis4breakfast) {
                Diet d = new Diet();
                d.diettype = getResources().getString(R.string.dis4);
                d.period = "Breakfast";
                d.food = s;
                d.save();
            }

            for (String s : dis5breakfast) {
                Diet d = new Diet();
                d.diettype = getResources().getString(R.string.dis5);
                d.period = "Breakfast";
                d.food = s;
                d.save();
            }

            for (String s : dis6breakfast) {
                Diet d = new Diet();
                d.diettype = getResources().getString(R.string.dis6);
                d.period = "Breakfast";
                d.food = s;
                d.save();
            }

            for (String s : dis1lunch) {
                Diet d = new Diet();
                d.diettype = getResources().getString(R.string.dis1);
                d.period = "Lunch";
                d.food = s;
                d.save();

            }

            for (String s : dis2lunch) {
                Diet d = new Diet();
                d.diettype = getResources().getString(R.string.dis2);
                d.period = "Lunch";
                d.food = s;
                d.save();

            }

            for (String s : dis3lunch) {
                Diet d = new Diet();
                d.diettype = getResources().getString(R.string.dis3);
                d.period = "Lunch";
                d.food = s;
                d.save();

            }

            for (String s : dis4lunch) {
                Diet d = new Diet();
                d.diettype = getResources().getString(R.string.dis4);
                d.period = "Lunch";
                d.food = s;
                d.save();

            }

            for (String s : dis5lunch) {
                Diet d = new Diet();
                d.diettype = getResources().getString(R.string.dis5);
                d.period = "Lunch";
                d.food = s;
                d.save();

            }

            for (String s : dis6lunch) {
                Diet d = new Diet();
                d.diettype = getResources().getString(R.string.dis6);
                d.period = "Lunch";
                d.food = s;
                d.save();

            }

            for (String s : dis1dinner) {
                Diet d = new Diet();
                d.diettype = getResources().getString(R.string.dis1);
                d.period = "Dinner";
                d.food = s;
                d.save();

            }

            for (String s : dis2dinner) {
                Diet d = new Diet();
                d.diettype = getResources().getString(R.string.dis2);
                d.period = "Dinner";
                d.food = s;
                d.save();

            }

            for (String s : dis3dinner) {
                Diet d = new Diet();
                d.diettype = getResources().getString(R.string.dis3);
                d.period = "Dinner";
                d.food = s;
                d.save();

            }

            for (String s : dis4dinner) {
                Diet d = new Diet();
                d.diettype = getResources().getString(R.string.dis4);
                d.period = "Dinner";
                d.food = s;
                d.save();

            }

            for (String s : dis5dinner) {
                Diet d = new Diet();
                d.diettype = getResources().getString(R.string.dis5);
                d.period = "Dinner";
                d.food = s;
                d.save();

            }

            for (String s : dis6dinner) {
                Diet d = new Diet();
                d.diettype = getResources().getString(R.string.dis6);
                d.period = "Dinner";
                d.food = s;
                d.save();

            }
        }
    }

}




