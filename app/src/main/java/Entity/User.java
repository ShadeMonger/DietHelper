package Entity;

import com.orm.SugarRecord;

public class User extends SugarRecord {
    public String username, name, email;
    public int age;
    public String password;

    public boolean dis1, dis2, dis3, dis4, dis5, dis6;

    // You must provide an empty constructor
    public User() {
    }

}
