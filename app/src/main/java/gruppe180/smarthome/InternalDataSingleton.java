package gruppe180.smarthome;

import java.util.ArrayList;

/**
 * Singelton class data storage used for ListCardsActivity
 */

public class InternalDataSingleton {
    private static InternalDataSingleton mInstance = null;
    /**
     * variable declaration
     */
    private ArrayList<String> user_data = new ArrayList<>();
    private ArrayList<String> card_data = new ArrayList<>();
    private ArrayList<String> pass_data = new ArrayList<>();
    private String[] cards;
    private String[][] users;
    /**
     * If no singleton then start new singleton.
     */
     public static InternalDataSingleton getInstance(){

        if(mInstance == null)
        {
            mInstance = new InternalDataSingleton();
                    }

         return mInstance;
    }
    /**
     * Insert string cardID into card_data arraylist & string user, pass, email and ip into user_data arraylist.
     */
    public void setUserdata(String cardID, String user, String pass, String email, String ip ){

        card_data.add(cardID);

        user_data.add(user);
        user_data.add(pass);
        pass_data.add(pass);
        user_data.add(email);
        user_data.add(ip);
    }
    /**
     * Convert arraylist card_data to array cards.
     */
    private void convert_cards() {

        int size = card_data.size();
        cards = new String[size];

        for (int i = 0; i < size; i++) {
            String y = card_data.get(i);
            cards[i] = y;
        }
    }
    /**
     * Convert arraylist user_data to multidimensional array users.
     */
    private void convert_users() {

        int size = user_data.size();
        int rows = size/4;
        int col = 4;
        users = new String[rows][col];

        for(int y = 0; y < rows; y++){
            users[y] = new String[col];
            for(int x = 0; x < col; x++){
                users[y][x] = user_data.get(y*col + x);
            }
        }

    }
    /**
     * Retrieve data from array cards.
     */
    public String[] getCarddata(){
        convert_cards();
        return cards;
    }
    /**
     * Retrieve data from multidimensional array users.
     */
    public String[][] getUserdata(){
        convert_users();
        return users;
    }

    public boolean verify(String card, String password){
        boolean result =  false;
        int count = card_data.size();

        for(int x = 0; x < count; x++){

            if (card.equals(card_data.get(x))){
                if(password.equals(pass_data.get(x))){
                    result = true;
                }
            }
            else{
                result = false;
            }
        }
        return result;
    }
}
