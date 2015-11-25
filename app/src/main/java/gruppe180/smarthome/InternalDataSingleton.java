package gruppe180.smarthome;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter1972 on 12-11-2015.
 */
public class InternalDataSingleton {
    private static InternalDataSingleton mInstance = null;

    // variable declaration
    private ArrayList user_data = new ArrayList();
    private ArrayList card_data = new ArrayList();
    private String[] cards;
    private String[][] users;



    // If no singleton then start new singleton
    public static InternalDataSingleton getInstance(){

        if(mInstance == null)
        {
            mInstance = new InternalDataSingleton();
        }
        return mInstance;
    }

    // Singleton methods
    public void setUserdata(String cardid, String user, String pass, String email, String ip ){
        card_data.add(cardid);
        user_data.add(user);
        user_data.add(pass);
        user_data.add(email);
        user_data.add(ip);
    }

    private void convert_cards() {

        int size = card_data.size();
        cards = new String[size];

        for (int i = 0; i < size; i++) {
            String y = (String) (String) card_data.get(i);
            cards[i] = y;
        }
    }

    private void convert_users() {

        int size = user_data.size();
        System.out.println("size = " + size);
        int rows = size / 4;
        System.out.println("rows = " + rows);
        int col = 4;
        users = new String[rows][col];

        for (int y = 0; y < rows; y++) {
            users[y] = new String[col];
            for (int x = 0; x < col; x++) {
                users[y][x] = (String) (String) user_data.get(y * col + x);
            }
        }

    }

    public String[] getCarddata(){
        convert_cards();
        return cards;

    }

    public String[][] getUserdata(){
        convert_users();
        return users;

    }



}
