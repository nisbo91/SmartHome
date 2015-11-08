package gruppe180.smarthome;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ControlActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        if (savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().add(R.id.topFrame, new StreamFragment(), "top").commit();
            //getSupportFragmentManager().beginTransaction().add(R.id.bottomFrame, new ControlOnOffFragment(), "bot").commit();
            //getSupportFragmentManager().beginTransaction().add(R.id.bottomFrame, new ControlDirectionFragment(), "bot").commit();
        }

        viewPager = (ViewPager) findViewById(R.id.bottomFrame);
        viewPager.setAdapter(new VPAdapter(getSupportFragmentManager()));
    }

    public void setNewBottomFragment(){
        // TODO: 29-10-2015 slide mellem de to forskellige fragmenter


    }

    private class VPAdapter extends FragmentPagerAdapter {
        public VPAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return new ControlOnOffFragment();
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
