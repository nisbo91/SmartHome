package gruppe180.smarthome;


import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;


public class GraphActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  // disable landscape view

        setContentView(R.layout.activity_graph);

            if (savedInstanceState==null) {
              getSupportFragmentManager().beginTransaction().add(R.id.graphFrame, new GraphFragment()).commit();
            //getSupportFragmentManager().beginTransaction().add(R.id.graphFrame, new GraphHumidityFragment()).addToBackStack(null).commit();
            }

        viewPager = (ViewPager) findViewById(R.id.graphFrame);
        viewPager.setAdapter(new VPAdapter(getSupportFragmentManager()));

    }
    private class VPAdapter extends FragmentPagerAdapter {

        public VPAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new GraphFragment();
            if (position == 1) fragment = new GraphHumidityFragment();
           // if (position== 2) fragment = new GraphPressureFragment();
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
            //return 3;
        }
    }

}
