package gruppe180.smarthome;

import android.content.Intent;
import android.app.PendingIntent;
import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity implements ActivityCommunicatorRegister{

    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        String username = preferences.getString("user", null);
        String password = preferences.getString("pass", null);
        String email = preferences.getString("email", null);
        String card = preferences.getString("card", null);
        String ip = preferences.getString("IP", null);


        InternalDataSingleton.getInstance().setUserdata(card,username,password,email,ip);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.startFragment, new LoginFragment()).commit();
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(!((LoginFragment.synligInstans) ==null)){
            LoginFragment.synligInstans.nfcTagSkannet(intent);
        }
        if(!((RegisterFragment.synligInstans) ==null)){
            RegisterFragment.synligInstans.nfcTagSkannet(intent);
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if (nfcAdapter != null) {
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
        }
    }


    @Override
    public void passDataToActivity(String username, String password, String email, String nfcCardID, String homeIPAddress) {
        InternalDataSingleton.getInstance().setUserdata(nfcCardID, username, password, email, homeIPAddress);
        preferences.edit().putString("user", username).apply();
        preferences.edit().putString("pass",password).apply();
        preferences.edit().putString("email",email).apply();
        preferences.edit().putString("card",nfcCardID).apply();
        preferences.edit().putString("IP",homeIPAddress).apply();
    }
}
