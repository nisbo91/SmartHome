package gruppe180.smarthome;

import java.util.ArrayList;

public class InternalDataSingleton {
    private static InternalDataSingleton mInstance = null;

    // variable declaration
    private ArrayList<String> user_data;
    private ArrayList<String> card_data;
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

        user_data = new ArrayList<>();
        card_data = new ArrayList<>();

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
            String y = card_data.get(i);
            cards[i] = y;
        }
    }

    private void convert_users() {

        int size = user_data.size();
        System.out.println("size = "+size);
        int rows = size/4;
        System.out.println("rows = "+rows);
        int col = 4;
        users = new String[rows][col];

        for(int y = 0; y < rows; y++){
            users[y] = new String[col];
            for(int x = 0; x < col; x++){
                users[y][x] = user_data.get(y*col + x);
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
