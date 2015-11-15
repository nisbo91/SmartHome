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
    private String serverResponse;

    public void getRemoteServerResponse(final String url){
        new AsyncTask(){
            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
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
