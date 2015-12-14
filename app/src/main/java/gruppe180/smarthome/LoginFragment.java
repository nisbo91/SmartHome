package gruppe180.smarthome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginFragment extends Fragment implements View.OnClickListener {


    private Button loginbutton;
    private Button registerbutton;
    private EditText passwordedittext;
    private TextView smarthometextview;
    private TextView placenfccardtextview;
    private TextView activatenfchere;
    private NfcAdapter nfcAdapter;
    private byte[] nfcTag;
    static LoginFragment synligInstans;
    private String hexArrayNfcTag=null;
    private boolean loginCheck= false;


    //private void nfcTagHex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        synligInstans = this;
        //TESTKODE TIL SINGLETON OG LISTCARDACTIVITY

        Log.d("LoginFragment", "Fragment onCreate()");
        View login = inflater.inflate(R.layout.fragment_login, container, false);

        getActivity().registerReceiver(broadcastReceiver, new IntentFilter(NfcAdapter.ACTION_ADAPTER_STATE_CHANGED));

        loginbutton = (Button) login.findViewById(R.id.loginButton);
        registerbutton = (Button) login.findViewById(R.id.registerButton);
        passwordedittext = (EditText) login.findViewById(R.id.passwordEdittext);
        smarthometextview = (TextView) login.findViewById(R.id.smartHomeTextView);
        placenfccardtextview = (TextView) login.findViewById(R.id.placeNfcCardTextView);
        activatenfchere = (TextView) login.findViewById(R.id.activateNfcHereTextView);
        activatenfchere.setOnClickListener(this);
        loginbutton.setOnClickListener(this);
        registerbutton.setOnClickListener(this);

        if (nfcAdapter.getDefaultAdapter(getActivity()).isEnabled()) {
            //Toast.makeText(getActivity(),"NFC available",Toast.LENGTH_LONG).show();
            updateNFCScreen(true);
        } else {
            //Toast.makeText(getActivity(),"NFC not available",Toast.LENGTH_LONG).show();
            updateNFCScreen(false);
        }
        System.out.println("nfc:"+nfcAdapter.getDefaultAdapter(getActivity()).isEnabled()+" adapter " +nfcAdapter);
        return login;
    }

    @Override
    public void onDestroyView() {
        synligInstans = null;
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.activateNfcHereTextView:
                intent.setAction(Settings.ACTION_NFC_SETTINGS);
                startActivity(intent);
                break;
            case R.id.loginButton:
                System.out.println(passwordedittext.getText().toString());
                System.out.println(hexArrayNfcTag);
                loginCheck = InternalDataSingleton.getInstance().verify(hexArrayNfcTag,passwordedittext.getText().toString());
                System.out.println(loginCheck);
                if(loginCheck==true){
                    Log.d("login","login");
                    intent = new Intent(getActivity(), OptionsActivity.class);
                    this.startActivity(intent);
                }
                else{
                    Toast.makeText(getActivity(), "Login don't exist", Toast.LENGTH_LONG).show();
                }
                /*internalDataApplicationClass = getApplication();
                try{
                    //internalDataApplicationClass.login(hexArrayNfcTag, passwordedittext.getText().toString());
                }
                catch(Exception e){
                    Log.d("login", String.valueOf(e));
                }*/
                break;
            case R.id.registerButton:
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.startFragment, new RegisterFragment()).addToBackStack(null).commit();
                System.out.println("resiter");
                break;
        }
    }



    public void updateNFCScreen(boolean b) {
        if (b==true) {
            activatenfchere.setVisibility(View.INVISIBLE);
        }
        if (!b==true) {
            activatenfchere.setVisibility(View.VISIBLE);
        }
        else{

        }
        System.out.println(b);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateNFCScreen(nfcAdapter.getDefaultAdapter(getActivity()).isEnabled());
        }
    };

    public void nfcTagSkannet(Intent intent) {
        nfcTag = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
        hexArrayNfcTag = byteToHex(nfcTag);
        System.out.println(hexArrayNfcTag);
        String text = "Card ID: " + hexArrayNfcTag+ "\n\nEnter password for login";
        placenfccardtextview.setText(text);
        placenfccardtextview.setTextSize(25);
        placenfccardtextview.setGravity(Gravity.CENTER_HORIZONTAL);

        //Toast.makeText(getActivity(), "hurra nfc2! " + hexArray, Toast.LENGTH_LONG).show();
    }
    public String byteToHex(byte[] src){
        StringBuilder stringBuilder = new StringBuilder();
        if(src == null || src.length<=0){
            return null;
        }
        char[] buffer = new char[2];
        for(int i=0; i<src.length; i++) {
            buffer[0] = Character.forDigit((src[i]>>>4)&0x0F, 16);
            buffer[1] = Character.forDigit((src[i]) & 0x0F, 16);
            stringBuilder.append(buffer);
        }
        return stringBuilder.toString();
    }

}

