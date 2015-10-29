package gruppe180.smarthome;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ControlDirectionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ControlDirectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ControlDirectionFragment extends Fragment implements View.OnClickListener{
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
     * @return A new instance of fragment ControlDirectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ControlDirectionFragment newInstance(String param1, String param2) {
        ControlDirectionFragment fragment = new ControlDirectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ControlDirectionFragment() {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_control_direction, container, false);

        (view.findViewById(R.id.controlUpButton)).setOnClickListener(this);
        (view.findViewById(R.id.controlDownButton)).setOnClickListener(this);
        (view.findViewById(R.id.controlLeftButton)).setOnClickListener(this);
        (view.findViewById(R.id.controlRightButton)).setOnClickListener(this);
        (view.findViewById(R.id.controlTagButton)).setOnClickListener(this);


        // Inflate the layout for this fragment
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.controlUpButton:
                System.out.println("UP!!");
                break;
            case R.id.controlDownButton:
                System.out.println("DOWN!!");
                break;
            case R.id.controlLeftButton:
                System.out.println("LEFT!!");
                break;
            case R.id.controlRightButton:
                System.out.println("RIGHT!!");
                break;
            case R.id.controlTagButton:
                System.out.println("TAG!!");
                break;
        }
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