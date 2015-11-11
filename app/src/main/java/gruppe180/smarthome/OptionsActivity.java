package gruppe180.smarthome;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OptionsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        getSupportFragmentManager().beginTransaction().add(R.id.mOptionsFrameLayout, new OptionsFragment()).commit();
    }

    public void changeFragment(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.listCardsButton:
                getSupportFragmentManager().beginTransaction().replace(R.id.mOptionsFrameLayout, new ListCardsFragment()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();
                break;
            case R.id.controlButton:
                intent = new Intent(this, ControlActivity.class);
                this.startActivity(intent);
                break;
            case R.id.environmentalButton:
                intent = new Intent(this, GraphActivity.class);
                this.startActivity(intent);

                //getSupportFragmentManager().beginTransaction().replace(R.id.mOptionsFrameLayout, new GraphFragment()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();
                break;
            case R.id.logoutButton:
                finish();
                break;
        }
    }

}
