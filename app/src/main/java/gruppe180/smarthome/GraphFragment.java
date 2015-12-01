package gruppe180.smarthome;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


import java.io.InputStream;
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

    // names of the switches on the remote server

    private String graphString;
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

    private int[] data = new int[]{1,1};
    private LineGraphSeries<DataPoint> series;
    private LineGraphSeries<DataPoint> newSeries;
    Calendar calendar = Calendar.getInstance();
    GraphView graph;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph, container, false);

        graph = (GraphView) view.findViewById(R.id.graph);

        //series= new LineGraphSeries<>();
        //series = new LineGraphSeries<DataPoint>(generateData());
        //graph.addSeries(series);

        //updateView();


        return view;
    }



    private void updateView(){



        for (int i=0; i<data.length;i++){
            Date x =calendar.getTime();
            calendar.add(Calendar.DATE, 1);
            series.appendData(new DataPoint(x, data[i]), false, 500);
            // System.out.println(x + ":" + data[i]);
        }
        graph.addSeries(series);


        // set date label formatter
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
        graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space

        // set manual x bounds to have nice steps
        //graph.getViewport().setMinX(d1.getTime());
        //graph.getViewport().setMaxX(d3.getTime());
        // graph.getViewport().setXAxisBoundsManual(true);
    }




    int[] nyData = new int[]{2,3,4};

    private void updateEntry(String time, double temp, double hum, double pres){

//        //series = new LineGraphSeries<DataPoint>(new DataPoint[]{ new DataPoint(0,temp)});

        for (int i=0; i<nyData.length;i++){
            Date x =calendar.getTime();
            calendar.add(Calendar.DATE, 1);
            newSeries.appendData(new DataPoint(x, nyData[i]), false, 500);
            // System.out.println(x + ":" + data[i]);
        }

        graph.addSeries(newSeries);

/*
        System.out.println("TIME  : "+time);
        System.out.println("TEMPERATUE : "+temp);
        System.out.println("HUMIDITY : "+hum);
        System.out.println("PRESSURE : "+pres);
*/
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


        LineGraphSeries nyseries= new LineGraphSeries<>();

        try {
            String str = output;
            int count= 0;

            DateFormat df = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
            for (String line : str.split("\\$")){

                line=line.trim(); // dataformatet er 2015-10-30 10:47:06,12.7,67.3,1.008
                if (line.length()==0 || line.startsWith("  "))continue;
                System.out.println("LIINE : "+line);


               String[] fields = line.split(",");

                Date time =  df.parse(fields[0]);
                double temperature = Double.parseDouble(fields[1]);
                double humidity = Double.parseDouble(fields[2]);
                double pressure = Double.parseDouble(fields[3]);

                /*
                System.out.println("TIME  : "+time);
                System.out.println("TEMPERATUE : "+temperature);
                System.out.println("HUMIDITY : "+humidity);
                System.out.println("PRESSURE : "+pressure);
                */
                System.out.println("TIME  : "+fields[0]);
                System.out.println("TEMP : "+ temperature);
                System.out.println("TIME FORMAT : "+time);

                count++;
                System.out.println(count);
                nyseries.appendData(new DataPoint(time,temperature), false,count);
            }
            //graph.removeSeries(nyseries);
            graph.addSeries(nyseries);
            graph.setBackgroundColor(0x83BFAF);
            // graph.setBackgroundColor(Color.DKGRAY); // make the background dark grey
            graph.setTitle("Temperature");
            nyseries.setColor(Color.RED);
            nyseries.setDrawDataPoints(true);
            nyseries.setThickness(8);
            nyseries.setDrawBackground(true); // make the area under the line in a different color
            //graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.BLACK);


           // graph.getGridLabelRenderer().setVerticalLabelsColor(Color.BLACK);

            //graph.getSecondScale().addSeries();
            //series.setBackgroundColor(Color.RED);





        }catch (Exception ex){
            ex.printStackTrace();
            // SÆT et textview som fortæller fejlen eller andet. ...
            System.out.print("FEJL: "+ ex.toString());
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
