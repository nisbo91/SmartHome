package gruppe180.smarthome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *  interface
 * to handle interaction events.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener,FragmentCommunicatorRegister{

    public Context context;
    private ActivityCommunicatorRegister activityCommunicatorRegister;
    private TextView smartHomeTextField2;
    private EditText userNameEditText;
    private EditText passwordEditText2;
    private EditText repeatPasswordEditText;
    static RegisterFragment synligInstans;
    private EditText emailEditText;
    private EditText nfcCardIDEditText;
    private EditText homeIPAdressEditText;
    private CheckBox termsCheckBox;
    private Button registerButton2;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z]+\\.+[a-z]+";
    String username = "[a-zA-Z0-9]{5,15}$";
    String password = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
    String repeatPassword = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
    String nfcCardID = "[a-zA-Z0-9]{8,8}$";
    String homeIPAddress = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        context = getActivity();
        activityCommunicatorRegister =(ActivityCommunicatorRegister)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        synligInstans = this;
        Log.d("RegisterFragment", "Fragment onCreate()");
        View register = inflater.inflate(R.layout.fragment_register, container, false);

        smartHomeTextField2 = (TextView) register.findViewById(R.id.smartHomeTextView2);
        userNameEditText = (EditText) register.findViewById(R.id.userNameEditText);
        passwordEditText2 = (EditText) register.findViewById(R.id.passwordEdittext2);
        repeatPasswordEditText = (EditText) register.findViewById(R.id.repeatPasswordEditText);
        emailEditText = (EditText) register.findViewById(R.id.emailEditText);
        nfcCardIDEditText = (EditText) register.findViewById(R.id.nfcCardIDEditText);
        homeIPAdressEditText = (EditText) register.findViewById(R.id.homeIPAdressEditText);
        termsCheckBox = (CheckBox) register.findViewById(R.id.termsCheckBox);
        registerButton2 = (Button) register.findViewById(R.id.registerButton2);
        registerButton2.setOnClickListener(this);

        userNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (userNameEditText.getText().toString().matches(username)) {
                    userNameEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.presence_online, 0);
                } else {
                    userNameEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.presence_offline, 0);
                }
            }
        });
        passwordEditText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (passwordEditText2.getText().toString().matches(password)) {
                    passwordEditText2.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.presence_online, 0);
                } else {
                    passwordEditText2.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.presence_offline, 0);
                }
            }
        });
        repeatPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (repeatPasswordEditText.getText().toString().matches(repeatPassword)) {
                    if (repeatPasswordEditText.getText().toString().equals(passwordEditText2.getText().toString())){
                        repeatPasswordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.presence_online, 0);
                    }
                } else {
                    repeatPasswordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.presence_offline, 0);
                }
            }
        });
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (emailEditText.getText().toString().matches(emailPattern)) {
                    emailEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.presence_online, 0);
                } else {
                    emailEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.presence_offline, 0);
                }
            }
        });
        nfcCardIDEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (nfcCardIDEditText.getText().toString().matches(nfcCardID)) {
                    nfcCardIDEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.presence_online, 0);
                } else {
                    nfcCardIDEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.presence_offline, 0);
                }
            }
        });
        homeIPAdressEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (homeIPAdressEditText.getText().toString().matches(homeIPAddress)) {
                    homeIPAdressEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.presence_online, 0);
                } else {
                    homeIPAdressEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.presence_offline, 0);
                }
            }
        });


        // Inflate the layout for this fragment
        return register;
    }
    @Override
    public void onDestroyView() {
        synligInstans = null;
        super.onDestroyView();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerButton2:
                if (userNameEditText.getText().toString().matches(username)){
                    if (passwordEditText2.getText().toString().matches(password)){
                        if (passwordEditText2.getText().toString().equals(repeatPasswordEditText.getText().toString())){
                            if(emailEditText.getText().toString().matches(emailPattern) && emailEditText.length() >0 ){
                                if (nfcCardIDEditText.getText().toString().matches(nfcCardID)){
                                    if(homeIPAdressEditText.getText().toString().matches(homeIPAddress)){
                                        if(termsCheckBox.isChecked()){
                                            activityCommunicatorRegister.passDataToActivity(userNameEditText.getText().toString(), passwordEditText2.getText().toString(), emailEditText.getText().toString(), nfcCardIDEditText.getText().toString(), homeIPAdressEditText.getText().toString());
                                            Toast.makeText(getActivity(), "data transmitted", Toast.LENGTH_LONG).show();
                                        }
                                        else{
                                            Toast.makeText(getActivity(), "terms and conditions isn't accepted", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    else{
                                        Toast.makeText(getActivity(), "Invalid Home IP Address \nmust content 4 numbers from 0-255 separated by a dot and only number", Toast.LENGTH_LONG).show();
                                    }
                                }
                                else{
                                    Toast.makeText(getActivity(), "Invalid NFC Card ID \nmust content 8 characters \nonly number and letters", Toast.LENGTH_LONG).show();
                                }
                            }
                            else{
                                Toast.makeText(getActivity(), "Invalid email", Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(getActivity(), "Password isn't equal to repeat password", Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(getActivity(), "Invalid password \nmust content at least one digit \nmust contain one lowercase characters \nmust contain on uppercase characters", Toast.LENGTH_LONG).show();

                    }
                }
                else{
                    Toast.makeText(getActivity(), "Invalid username \nmust contain 5-15 letters or numbers", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void passDataToFragment(String parseClass, String username, String password, String email, String nfcCardID, String homeIPAddress) {

    }
    public void nfcTagSkannet(Intent intent) {
        byte[] nfcTag = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
        String hexArrayNfcTag = byteToHex(nfcTag);
        nfcCardIDEditText.setText(hexArrayNfcTag);
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
