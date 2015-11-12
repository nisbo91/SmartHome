package gruppe180.smarthome;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;

public class ControlActivity extends AppCompatActivity {

    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        if (savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().add(R.id.topFrame, new StreamFragment()).commit();
        }

        viewPager = (ViewPager) findViewById(R.id.bottomFrame);
        viewPager.setAdapter(new VPAdapter(getSupportFragmentManager()));


    }

    private class VPAdapter extends FragmentPagerAdapter {
        public VPAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new ControlOnOffFragment();
            switch (position) {
                case 1:
                    fragment = new ControlOnOffFragment();
                case 2:
                    fragment = new ControlDirectionFragment();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
