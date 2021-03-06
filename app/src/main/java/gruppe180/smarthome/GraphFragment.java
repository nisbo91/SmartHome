package gruppe180.smarthome;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


import java.io.InputStream;
import java.net.ConnectException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Zana.
 */
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GraphFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GraphFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GraphFragment extends Fragment implements ExternalDatabaseResponse {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    ExternalDatabaseManager externalDatabaseManager=new ExternalDatabaseManager();

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GraphFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GraphFragment newInstance(String param1, String param2) {
        GraphFragment fragment = new GraphFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public GraphFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        externalDatabaseManager.delegate=this;
        externalDatabaseManager.getRemoteServerResponse("sensor.php?action=0&range=50");
    }

    GraphView graph;
    TextView txt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

             View view = inflater.inflate(R.layout.fragment_graph, container, false);
             graph = (GraphView) view.findViewById(R.id.graphTemp);
             txt = (TextView) view.findViewById(R.id.textViewTemp);

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
    public void processFinish(String output) {

        LineGraphSeries seriesTemp= new LineGraphSeries<>();

        try {
            String str = output;  // get the data from the database in a string
            int count= 0;

            DateFormat df = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");  // sets the custom format for the date
            for (String line : str.split("\\$")){

                line=line.trim(); // dataformatet er 2015-10-30 10:47:06,12.7,67.3,1.008
                if (line.length()==0 || line.startsWith("  "))continue;  // if there is
               // System.out.println("LIINE : "+line);


               String[] fields = line.split(",");

                Date time =  df.parse(fields[0]); // makes the string ("time") into a date format
                double temperature = Double.parseDouble(fields[1]);
                /*
                System.out.println("TIME  : "+fields[0]);
                System.out.println("TEMP : "+ temperature);
                System.out.println("TIME FORMAT : "+time.getHours());
                */
                //System.out.println(" TIME : "+time.getTime());
                count++;
                seriesTemp.appendData(new DataPoint(time.getHours(), temperature), false, count); // add datapoints to the graph.
            }
            graph.addSeries(seriesTemp);


            graph.setBackgroundColor(Color.TRANSPARENT); //  makes the background colour of the graph "transparent"
            // graph.setBackgroundColor(Color.DKGRAY); // make the background dark grey
            graph.setTitle("Graph for temperature");
            seriesTemp.setColor(Color.RED);
            seriesTemp.setDrawDataPoints(true); // make bullet points on the graph
            seriesTemp.setThickness(8);   // set th thickness of the line
            seriesTemp.setDrawBackground(true); // make the area under the line in a different color
            graph.getGridLabelRenderer().setHorizontalAxisTitle("Time");  // label on the y axis
            //graph.getGridLabelRenderer().setVerticalAxisTitle("Degree");

            graph.getViewport().setXAxisBoundsManual(true);  // enable the manipulation of the x axis
            graph.getViewport().setMinX(seriesTemp.getLowestValueX()); // sets the lowest x value in the graph plot x axis
            graph.getViewport().setMaxX(seriesTemp.getHighestValueX()); // sets the highest x value in the graph plot x axis



        }catch (Exception ex){

            ex.printStackTrace();

            txt.setText("Error. Check network");  // To warn the user

            System.out.print("FEJL !!: "+ ex.toString());
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
