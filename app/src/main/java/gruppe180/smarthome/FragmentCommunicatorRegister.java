package gruppe180.smarthome;

/**
 * Created by Jimmy on 19-11-2015.
 */
public interface FragmentCommunicatorRegister {
    public void passDataToFragment(String parseClass,String username, String password, String email, String nfcCardID, String homeIPAddress);
}
