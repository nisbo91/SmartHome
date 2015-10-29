package gruppe180.smarthome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ControlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        getSupportFragmentManager().beginTransaction().add(R.id.topFrame, new StreamFragment(), "top").commit();
        //getSupportFragmentManager().beginTransaction().add(R.id.bottomFrame, new ControlOnOffFragment(), "bot").commit();
        getSupportFragmentManager().beginTransaction().add(R.id.bottomFrame, new ControlDirectionFragment(), "bot").commit();
    }
}
