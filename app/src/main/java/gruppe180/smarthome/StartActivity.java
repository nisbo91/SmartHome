package gruppe180.smarthome;

import android.app.Application;
import android.content.Intent;
import android.app.PendingIntent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.Parse;
import com.parse.ParseObject;

public class StartActivity extends AppCompatActivity {

    private NfcAdapter nfcAdapter;
    private byte[] tag;
    private PendingIntent pendingIntent;
    private ParseObject testObject;
    //private String test;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        /*Parse.enableLocalDatastore(this);
        Parse.initialize(this, "GtnNlWWR171VrvWewFC2VK6NlCcN4aukI3G3Q4O9", "NM11Kt9g3I52S1MeKS6PmbjLKSAtKeeUyhPR77Fz");
        testObject = new ParseObject("UserLogin");
        testObject.put("Username", "Peter");
        testObject.put("Password", "Peter");
        testObject.put("Email", "Peter");
        testObject.put("CardID", "Peter");
        testObject.put("IP_Address", "Peter");
        testObject.saveInBackground();*/

        //test = InternalDataSingleton.getInstance().getString();
        //grab a hold of the nfc sensor
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.startFragment, new LoginFragment()).commit();
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //Toast.makeText(this, "hurra nfc! "+intent,Toast.LENGTH_LONG).show();
        LoginFragment.synligInstans.nfcTagSkannet(intent);
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
    public void newUser(String userLogin, String username, String password, String email, String cardID, String iP_Address){
        InternalDataApplicationClass internalDataApplicationClass = ((InternalDataApplicationClass) getApplication())
        .addUser(userLogin, username, password, email, cardID, iP_Address);
    }



    /*
    private void getTagInfo(Intent intent) {
        tag = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
        Bundle bundle = new Bundle();
        bundle.putByteArray("NFC", tag);
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setArguments(bundle);

        Toast.makeText(this,tag.toString(),Toast.LENGTH_LONG).show();
        Log.d("NFC", tag.toString());
    }*/

}
