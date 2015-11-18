package gruppe180.smarthome;

/**
 * Created by Peter1972 on 18-11-2015.
 */
public class UserDataObject {

    private String username;
    private String password;
    private String email;
    private String cardid;
    private String ip;

    public String getUsername(){
        return username;
    }

    public void setUsername(String u){
        username = u;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String p){
        password = p;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String e){
        email = e;
    }

    public String getCardid(){
        return cardid;
    }

    public void setCardid(String c){
        cardid = c;
    }

    public String getIp(){
        return ip;
    }

    public void setIp(String i){
        ip = i;
    }
}

