package gruppe180.smarthome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OptionActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        (findViewById(R.id.listCardsButton)).setOnClickListener(this);
        (findViewById(R.id.controlButton)).setOnClickListener(this);
        (findViewById(R.id.environmentalButton)).setOnClickListener(this);
        (findViewById(R.id.logoutButton)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.listCardsButton:
                System.out.println("Settings");
                break;
            case R.id.controlButton:
                Intent intent = new Intent(this, ControlActivity.class);
                this.startActivity(intent);
                break;
            case R.id.environmentalButton:
                System.out.println("Environmental Control and overview");
                break;
            case R.id.logoutButton:
                System.out.println("Dooooo something!!!! fast! we are sinking....");
                break;
        }
    }
}
