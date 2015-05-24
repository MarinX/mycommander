package info.mycommander.mycommander.Model;


import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface CmdRest {

    @POST("/say")
    void execute(@Body CmdRequest req, Callback<CmdResponse> res);
}
