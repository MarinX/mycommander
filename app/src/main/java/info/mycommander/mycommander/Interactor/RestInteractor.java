package info.mycommander.mycommander.Interactor;


import info.mycommander.mycommander.Model.CmdRest;
import retrofit.RestAdapter;

public class RestInteractor {

    private static CmdRest restApi;

    public static CmdRest getApplicationApi(String address){
        if(restApi == null){
            restApi = new RestAdapter
                    .Builder()
                    .setEndpoint(convert(address))
                    .build()
                    .create(CmdRest.class);
        }
        return restApi;
    }

    private static String convert(String addr){
        return "http://"+addr+":8080";
    }
}
