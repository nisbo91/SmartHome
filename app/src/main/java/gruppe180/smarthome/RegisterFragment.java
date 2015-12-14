package gruppe180.smarthome;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
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
    public void onClick(View v) {
        System.out.println("resistor button");
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





 /*   // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
*/
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    /*// TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
*/

/*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    //@Override


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }*/

}
