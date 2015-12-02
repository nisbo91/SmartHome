package gruppe180.smarthome;


import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;


public class GraphActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  // disable landscape view

        setContentView(R.layout.activity_graph);

        getSupportFragmentManager().beginTransaction().add(R.id.graphFrame, new GraphFragment()).addToBackStack(null).commit();



    }

}
