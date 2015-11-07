package gruppe180.smarthome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OptionActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_option);
        (findViewById(R.id.listCardsButton)).setOnClickListener(this);
        (findViewById(R.id.controlButton)).setOnClickListener(this);
        (findViewById(R.id.environmentalButton)).setOnClickListener(this);
        (findViewById(R.id.logoutButton)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.listCardsButton:
                // TODO: 05-11-2015 skift fragment til List Cards
                getSupportFragmentManager().beginTransaction().replace(R.id.optionsLayout, new ListCardsFragment()).setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();
                break;
            case R.id.controlButton:
                intent = new Intent(this, ControlActivity.class);
                this.startActivity(intent);
                break;
            case R.id.environmentalButton:
                intent = new Intent(this, GraphActivity.class);
                this.startActivity(intent);
                break;
            case R.id.logoutButton:
                finish();
                break;
        }
    }
}
