package gruppe180.smarthome;

import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

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

    private final String prefix = "http://";            // fast
    private final String serverURL = "87.72.39.104";    // fra database ved registrering, ændres fra final senere
    private final String mPage = "camera.php?";
    private final String mStatus = "st=";
    private final String mDivider = "&";
    private final String mControl = "cn=";
    private String controlString;

    private ImageButton cUp, cRight, cDown, cLeft, cTag;

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

        cUp = (ImageButton) view.findViewById(R.id.controlUpButton);
        cDown = (ImageButton) view.findViewById(R.id.controlDownButton);
        cLeft = (ImageButton) view.findViewById(R.id.controlLeftButton);
        cRight = (ImageButton) view.findViewById(R.id.controlRightButton);
        cTag = (ImageButton) view.findViewById(R.id.controlTagButton);

        cUp.setOnClickListener(this);
        cLeft.setOnClickListener(this);
        cRight.setOnClickListener(this);
        cDown.setOnClickListener(this);
        cTag.setOnClickListener(this);

        cLeft.setRotation(-90);
        cRight.setRotation(90);
        cDown.setRotation(180);

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

    private void animateImageButton(ImageButton imageButton){
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0.9f,1, 0.9f, imageButton.getWidth()/2, imageButton.getHeight()/2);
        scaleAnimation.setDuration(100);
        scaleAnimation.setRepeatCount(1);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        scaleAnimation.setInterpolator(new DecelerateInterpolator());
        imageButton.startAnimation(scaleAnimation);
    }
    private void updateControl(String string){
        System.out.println(string);
    }

    private void setControlDirectionValue(Integer action){
        final String controlUrl = prefix+serverURL+"/"+mPage+mControl+action;
        System.out.println(controlUrl);
        new AsyncTask(){
            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(controlUrl).openStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = bufferedReader.readLine();
                    while (line != null){
                        stringBuilder.append(line+"\n");
                        line = bufferedReader.readLine();
                    }
                    controlString = stringBuilder.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                updateControl(controlString);
            }
        }.execute();
    }

    private void tagStream(){
        System.out.println("tagged in stream and sent on e-mail");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.controlUpButton:
                setControlDirectionValue(1);
                System.out.println("UP!!");
                break;
            case R.id.controlDownButton:
                setControlDirectionValue(2);
                System.out.println("DOWN!!");
                break;
            case R.id.controlLeftButton:
                setControlDirectionValue(3);
                System.out.println("LEFT!!");
                break;
            case R.id.controlRightButton:
                setControlDirectionValue(4);
                System.out.println("RIGHT!!");
                break;
            case R.id.controlTagButton:
                tagStream();
                System.out.println("TAG!!");
                animateImageButton(cTag);
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
