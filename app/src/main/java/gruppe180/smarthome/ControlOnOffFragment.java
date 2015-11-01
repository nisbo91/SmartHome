package gruppe180.smarthome;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ControlOnOffFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ControlOnOffFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ControlOnOffFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    final String prefix = "http://";            // fast
    final String serverURL = "87.72.39.104";    // fra database ved registrering, ændres senere
    private final String mPage = "control.php?";
    private final String mStatus = "st=";
    private final String mDivider = "&";
    private final String mControl = "cn=";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // names of the switches on the remote server
    private String[] data = new String[] {"Alpha","Beta","Gamma","Delta","Epsilon","Zeta","Eta","Theta"};
    ListView listView;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ControlOnOffFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ControlOnOffFragment newInstance(String param1, String param2) {
        ControlOnOffFragment fragment = new ControlOnOffFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ControlOnOffFragment() {
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

    private String setRemoteControl(Integer i, boolean s){
        // TODO: 01-11-2015 skal nok køre som async task

        //http://87.72.39.104/control.php?st=false&cn=0

        return "";
    }

    private void updateRemoteControlView(String settings){


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_control_on_off, container, false);
        listView = (ListView) view.findViewById(R.id.controlListView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.control_layout_single_row, R.id.mControlSwitch, this.data);
        listView.setAdapter(adapter);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               System.out.println(position);
               // TODO: 31-10-2015 get status checked/unchecked at the position
               Boolean status = false; // dummy
               // TODO: 31-10-2015 update remote server
               String response = setRemoteControl(position, status);
               // TODO: 31-10-2015 update list with info from server
               updateRemoteControlView(response);
           }
       });

        return view;
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
