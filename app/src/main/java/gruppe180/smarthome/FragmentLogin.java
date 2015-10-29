package gruppe180.smarthome;

import android.content.Intent;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentLogin extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentLogin.
     */
    public static FragmentLogin newInstance(String param1, String param2) {
        FragmentLogin fragment = new FragmentLogin();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentLogin() {
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

    private Button loginbutton;
    private Button registerbutton;
    private EditText passwordedittext;
    private TextView smarthometextview;
    private TextView placenfccardtextview;
    private TextView activatenfchere;
    private NfcAdapter nfcAdapter;
    private NfcManager nfcManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        Log.d("FragmentLogin","Fragment onCreate()");
        View login = inflater.inflate(R.layout.fragment_login,container,false);

        loginbutton = (Button) login.findViewById(R.id.loginButton);
        registerbutton = (Button) login.findViewById(R.id.registerButton);
        passwordedittext = (EditText) login.findViewById(R.id.passwordEdittext);
        smarthometextview = (TextView) login.findViewById(R.id.smartHomeTextView);
        placenfccardtextview = (TextView) login.findViewById(R.id.placeNfcCardTextView);
        activatenfchere = (TextView) login.findViewById(R.id.activateNfcHereTextView);
        activatenfchere.setOnClickListener(this);
        loginbutton.setOnClickListener(this);


        if (nfcAdapter.getDefaultAdapter(getActivity()).isEnabled()){
            //Toast.makeText(getActivity(),"NFC available",Toast.LENGTH_LONG).show();
        }
        else {
            //Toast.makeText(getActivity(),"NFC not available",Toast.LENGTH_LONG).show();
            activatenfchere.setVisibility(View.VISIBLE);
            /*Intent intent = new Intent();
            intent.setAction(Settings.ACTION_NFC_SETTINGS);
            startActivity(intent);*/
        }

        return login;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.activateNfcHereTextView:
                intent.setAction(Settings.ACTION_NFC_SETTINGS);
                startActivity(intent);
                break;
            case R.id.loginButton:
                intent = new Intent(getActivity(), OptionActivity.class);
                this.startActivity(intent);
        }
    }


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
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
