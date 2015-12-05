package gruppe180.smarthome;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GraphHumidityFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GraphHumidityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GraphHumidityFragment extends Fragment implements ExternalDatabaseResponse{
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
     * @return A new instance of fragment GraphHumidityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GraphHumidityFragment newInstance(String param1, String param2) {
        GraphHumidityFragment fragment = new GraphHumidityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public GraphHumidityFragment() {
        // Required empty public constructor
    }
    ExternalDatabaseManager externalDatabaseManager=new ExternalDatabaseManager();

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
    GraphView graphHum;
    TextView txt, txt1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewHum = inflater.inflate(R.layout.fragment_graph_humidity, container, false);

        graphHum = (GraphView) viewHum.findViewById(R.id.graphHum);
        txt = (TextView) viewHum.findViewById(R.id.textViewHum);
        txt1 = (TextView) viewHum.findViewById(R.id.textViewHum1);
        txt1.setVisibility(View.INVISIBLE); // make visible if the third graph makes it.

        // Inflate the layout for this fragment
        return viewHum;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void processFinish(String output) {
        LineGraphSeries seriesHum= new LineGraphSeries<>();

        try {
            String str = output;  // get the data from the database in a string
            int count= 0;

            DateFormat df = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");  // sets the custom format for the date
            for (String line : str.split("\\$")){

                line=line.trim(); // dataformatet er 2015-10-30 10:47:06,12.7,67.3,1.008
                if (line.length()==0 || line.startsWith("  "))continue;  // if there is

                String[] fields = line.split(",");

                Date time =  df.parse(fields[0]); // makes the string ("time") into a date format
                double humidity = Double.parseDouble(fields[2]);
                double pressure = Double.parseDouble(fields[3]);

                count++;
                seriesHum.appendData(new DataPoint(time.getHours(), humidity), false, count); // add datapoints to the graph.
            }
            graphHum.addSeries(seriesHum);

            graphHum.setBackgroundColor(Color.TRANSPARENT); // makes the background colour of the graph "transparent"
            // graph.setBackgroundColor(Color.DKGRAY); // make the background dark grey
            graphHum.setTitle("Graph for humidity");
            seriesHum.setColor(Color.RED);
            seriesHum.setDrawDataPoints(true); // make bullet points on the graph
            seriesHum.setThickness(8);   // set th thickness of the line
            seriesHum.setDrawBackground(true); // make the area under the line in a different color
            graphHum.getGridLabelRenderer().setHorizontalAxisTitle("Time");  // label on the y axis


            graphHum.getViewport().setXAxisBoundsManual(true);  // enable the manipulation of the x axis
            graphHum.getViewport().setMinX(seriesHum.getLowestValueX()); // sets the lowest x value in the graph plot x axis
            graphHum.getViewport().setMaxX(seriesHum.getHighestValueX()); // sets the highest x value in the graph plot x axis


        }catch (Exception ex){
            ex.printStackTrace();

            txt.setText("Error. Check network");
            txt1.setVisibility(View.INVISIBLE);

            System.out.print("FEJL: "+ ex.toString());
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
