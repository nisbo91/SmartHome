package gruppe180.smarthome;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ControlOnOffFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ControlOnOffFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ControlOnOffFragment extends Fragment implements ExternalDatabaseResponse{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String[] switchNames = new String[] {"Alpha","Beta","Gamma","Delta","Epsilon","Zeta","Eta","Theta"};
    private boolean[] status = {false, false, false, false, false, false, false, false};
    private ListView listView;
    private List<HashMap<String,Object>> controlList;
    private SimpleAdapter adapter;
    private ExternalDatabaseManager externalDatabaseManager = new ExternalDatabaseManager();
    final String cPage = "control.php?";

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
        externalDatabaseManager.delegate = this;
        externalDatabaseManager.getRemoteServerResponse(cPage);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_control_on_off, container, false);

        controlList = new ArrayList<>();
        for(int i = 0 ; i < switchNames.length; i++){
            HashMap<String, Object> hm = new HashMap<>();
            hm.put("txt", switchNames[i]);
            hm.put("stat", status[i]);
            controlList.add(hm);
        }

        String[] from = {"txt","stat"};
        int[] to = {R.id.mControlSwitch};
        adapter = new SimpleAdapter(getActivity(), controlList, R.layout.control_layout_single_row, from, to) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                Switch mSwitch = (Switch) view.findViewById(R.id.mControlSwitch);
                @SuppressWarnings("unchecked")
                HashMap<String,Object> hm = (HashMap) controlList.get(position);
                Boolean chk = (Boolean) hm.get("stat");
                mSwitch.setChecked(chk);
                return view;
            }
        };

        listView = (ListView) view.findViewById(R.id.controlListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, Object> hm = new HashMap<>();
                LinearLayout mLayout = (LinearLayout) view;
                Switch aSwitch = (Switch) mLayout.getChildAt(0);
                aSwitch.setChecked(!aSwitch.isChecked());
                hm.put("txt", switchNames[position]);
                hm.put("stat", aSwitch.isChecked());
                controlList.set(position, hm);
                adapter.notifyDataSetChanged();
                externalDatabaseManager.setRemoteSwitch(position, aSwitch.isChecked());
            }
        });
        listView.setAdapter(adapter);
        return view;
    }

    private void updateSwitchStatus(String string){
        for(Integer i=0; i<string.length(); i++){
            status[i] = string.charAt(i) != '0';
        }
        for(int i=0;i< switchNames.length;i++){
            HashMap<String, Object> hm = controlList.get(i);
            hm.put("txt", switchNames[i]);
            hm.put("stat", status[i]);
        }
        adapter.notifyDataSetChanged();
        System.out.println("UpdateString: " + string);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void processFinish(String output) {
        updateSwitchStatus(output);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
