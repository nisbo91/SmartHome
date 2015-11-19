package gruppe180.smarthome;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by Jimmy on 18-11-2015.
 */
public class InternalDataApplicationClass extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
        //add your initialization code here
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "GtnNlWWR171VrvWewFC2VK6NlCcN4aukI3G3Q4O9", "NM11Kt9g3I52S1MeKS6PmbjLKSAtKeeUyhPR77Fz");
    }

    public void login(String cardID, String password) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("newObject");
        query.whereEqualTo("CardID", cardID);
        query.whereEqualTo("Password", password);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    //Intent intent = new Intent(startActivity,OptionsActivity.class);
                    //startActivity(intent);
                } else {
                    Log.d("login Error: ", String.valueOf(e));
                }
            }
        });
    }

    public static void getData() {
    }

    public static void setData(String parseClass, String username, String password, String email, String nfcCardID, String homeIPAddress) {
        ParseObject newObject = new ParseObject(parseClass);
        newObject.put("Username", username);
        newObject.put("Password", password);
        newObject.put("Email", email);
        newObject.put("CardID", nfcCardID);
        newObject.put("IP_Address", homeIPAddress);
        newObject.saveInBackground();
    }
}
