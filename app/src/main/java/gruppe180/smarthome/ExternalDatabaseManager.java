package gruppe180.smarthome;


import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Jesper de Fries on 14-11-2015.
 */
public class ExternalDatabaseManager extends AsyncTask{

    public ExternalDatabaseResponse delegate = null;

    private final String prefix = "http://";            // fast
    private final String mStatus = "st=";
    private final String mDivider = "&";
    private final String mControl = "cn=";
    private final String serverURL = "87.72.39.104";    // fra database ved registrering, ændres senere
    private final String mPage = "control.php?";

    private String serverResponse;

    public void getRemoteServerResponse(final String page){
        final String gUrl = prefix+serverURL+"/"+page;
        new AsyncTask(){
            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(gUrl).openStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = bufferedReader.readLine();
                    stringBuilder.append(line);
                    serverResponse = line.replace(" ", "");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                delegate.processFinish(serverResponse);
            }
        }.execute();
    }

    public void setRemoteSwitch(Integer position, boolean b){
        final String controlSetUrl = prefix+serverURL+"/"+mPage+mStatus+b+mDivider+mControl+position;
        new AsyncTask(){
            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(controlSetUrl).openStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = bufferedReader.readLine();
                    stringBuilder.append(line);
                    serverResponse = line.replace(" ", "");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                delegate.processFinish(serverResponse);
            }
        }.execute();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        return null;
    }
}
