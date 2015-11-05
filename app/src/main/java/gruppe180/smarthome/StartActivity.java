package gruppe180.smarthome;

import android.app.FragmentManager;
import android.content.Intent;
import android.app.PendingIntent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.app.PendingIntent;
import android.support.v7.app.AppCompatActivity;
import android.content.IntentFilter.MalformedMimeTypeException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class StartActivity extends AppCompatActivity{

    private NfcAdapter nfcAdapter;
    private byte[] tag;
    private PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
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

    /*
    private void getTagInfo(Intent intent) {
        tag = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
        Bundle bundle = new Bundle();
        bundle.putByteArray("NFC", tag);
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setArguments(bundle);

        Toast.makeText(this,tag.toString(),Toast.LENGTH_LONG).show();
        Log.d("NFC", tag.toString());
    }
*/


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
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
    }*/
}
