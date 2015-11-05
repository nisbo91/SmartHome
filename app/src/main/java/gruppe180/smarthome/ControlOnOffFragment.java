package gruppe180.smarthome;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


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
    // preset values
    private boolean[] status = {false, false, false, false, false, false, false, false};
    private ListView listView;
    private List<HashMap<String,Object>> aList;

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

    private String setRemoteSwitch(Integer position, boolean b){
        // TODO: 01-11-2015 skal nok køre som async task



        //http://87.72.39.104/control.php?st=false&cn=0

        return "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_control_on_off, container, false);

        aList = new ArrayList<HashMap<String,Object>>();
        for(int i=0;i<8;i++){
            HashMap<String, Object> hm = new HashMap<String,Object>();
            hm.put("txt", data[i]);
            hm.put("stat", status[i]);
            aList.add(hm);
        }

        String[] from = {"txt","stat" };
        int[] to = { R.id.mControlSwitch };
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), aList, R.layout.control_layout_single_row, from, to) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                Switch mSwitch = (Switch) view.findViewById(R.id.mControlSwitch);
                HashMap<String,Object> hm = (HashMap) aList.get(position);
                Boolean chk = (Boolean) hm.get("stat");
                mSwitch.setChecked(chk);
                return view;
            }
        };

        listView = (ListView) view.findViewById(R.id.controlListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SimpleAdapter adapter = (SimpleAdapter) listView.getAdapter();
                HashMap<String, Object> hm = new HashMap<String,Object>();
                LinearLayout mLayout = (LinearLayout) view;
                Switch tgl = (Switch) mLayout.getChildAt(0);
                // TODO: 04-11-2015 når der skiftes, så skriv til control.php for at skifte på serveren
                if (tgl.isChecked()) {
                    tgl.setChecked(!tgl.isChecked());
                    hm.put("txt", data[position]);
                    hm.put("stat", tgl.isChecked());
                    aList.set(position, hm);
                    adapter.notifyDataSetChanged();
                    setRemoteSwitch(position, !tgl.isChecked());
                    System.out.println(false + "@" + position);
                } else {
                    tgl.setChecked(!tgl.isChecked());
                    hm.put("txt", data[position]);
                    hm.put("stat", tgl.isChecked());
                    aList.set(position, hm);
                    adapter.notifyDataSetChanged();
                    setRemoteSwitch(position, !tgl.isChecked());
                    System.out.println(true + "@" + position);
                }

            }
        });

        listView.setAdapter(adapter);

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
