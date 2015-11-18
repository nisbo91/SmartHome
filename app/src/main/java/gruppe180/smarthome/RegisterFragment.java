package gruppe180.smarthome;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
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
public class RegisterFragment extends Fragment implements View.OnClickListener {
    private TextView smartHomeTextField;
    private EditText userNameEditText;
    private EditText passwordEditText;
    private EditText repeatPasswordEditText;
    private EditText emailEditText;
    private EditText nfcCardIDEditText;
    private EditText homeIPAdressEditText;
    private CheckBox termsCheckBox;
    private Button registerButton;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String username = "[a-zA-Z0-9]{5,15}$";
    String password = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    String repeatPassword = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    String nfcCardID = "[a-zA-Z0-9]{8,8}$";
    String homeIPAddress = "[0-255].[0-255].[0-255].[0-255]";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        Log.d("LoginFragment", "Fragment onCreate()");
        View register = inflater.inflate(R.layout.fragment_register, container, false);
        smartHomeTextField = (TextView) register.findViewById(R.id.smartHomeTextView);
        userNameEditText = (EditText) register.findViewById(R.id.userNameEditText);
        passwordEditText = (EditText) register.findViewById(R.id.passwordEdittext);
        repeatPasswordEditText = (EditText) register.findViewById(R.id.repeatPasswordEditText);
        emailEditText = (EditText) register.findViewById(R.id.emailEditText);
        nfcCardIDEditText = (EditText) register.findViewById(R.id.nfcCardIDEditText);
        homeIPAdressEditText = (EditText) register.findViewById(R.id.homeIPAdressEditText);
        termsCheckBox = (CheckBox) register.findViewById(R.id.termsCheckBox);
        registerButton = (Button) register.findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerButton:
                if (userNameEditText.getText().toString().matches(username)){

                }
                else{
                    Toast.makeText(getActivity(), "Invalid username \n must contain 5-15 letters and numbers", Toast.LENGTH_LONG).show();
                }
                if (passwordEditText.getText().toString().matches(password)){

                }
                else{
                    Toast.makeText(getActivity(), "Invalid password \n must content at least one digit \n must contain one lowercase characters \n must contain on uppercase characters", Toast.LENGTH_LONG).show();

                }
                if (nfcCardIDEditText.getText().toString().matches(nfcCardID)){

                }
                else{
                    Toast.makeText(getActivity(), "Invalid NFC Card ID \n must content 8 characters \n only number and letters", Toast.LENGTH_LONG).show();
                }

                if (passwordEditText.getText().toString()==repeatPasswordEditText.getText().toString()){
                    if(emailEditText.getText().toString().matches(emailPattern) && emailEditText.length() >0 ){

                        ((StartActivity) getActivity()).newUser("UserLogin", userNameEditText.getText().toString(), passwordEditText.getText().toString(), emailEditText.getText().toString(), nfcCardIDEditText.getText().toString(),homeIPAdressEditText.getText().toString());
                    }
                    else{
                        Toast.makeText(getActivity(), "Invalid email", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getActivity(), "Password and repeat password not the same", Toast.LENGTH_LONG).show();
                }
                break;
        }
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
