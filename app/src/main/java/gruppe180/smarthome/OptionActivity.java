package gruppe180.smarthome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.listCardsButton:
                System.out.println("Settings");
                break;
            case R.id.controlButton:
                System.out.println("control -> new activity");
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
