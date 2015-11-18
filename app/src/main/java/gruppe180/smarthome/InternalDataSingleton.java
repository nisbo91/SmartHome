package gruppe180.smarthome;

/**
 * Created by Peter1972 on 12-11-2015.
 */
public class InternalDataSingleton{
    private static InternalDataSingleton mInstance = null;
    // variable declaration
    private String mString;

    // variable value settings
    protected InternalDataSingleton(){
        mString = "Hello";
    }

    // If no singleton then start new singleton
    public static InternalDataSingleton getInstance(){
        if(mInstance == null)
        {
            mInstance = new InternalDataSingleton();
        }
        return mInstance;
    }

    // Singleton methods

    public String getString(){
        return this.mString;
    }

    public void setString(String value){
        mString = value;
        System.out.println("hej");
    }

}
