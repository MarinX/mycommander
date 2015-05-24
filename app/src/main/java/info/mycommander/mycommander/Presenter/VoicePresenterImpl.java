package info.mycommander.mycommander.Presenter;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import info.mycommander.mycommander.Interactor.RestInteractor;
import info.mycommander.mycommander.Model.CmdManager;
import info.mycommander.mycommander.Model.CmdRequest;
import info.mycommander.mycommander.Model.CmdResponse;
import info.mycommander.mycommander.R;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class VoicePresenterImpl implements VoicePresenter, Callback<CmdResponse> {

    private Context ctx;
    private CmdManager manager;

    public static VoicePresenterImpl create(Context ctx){
        return new VoicePresenterImpl(ctx);
    }

    private VoicePresenterImpl(Context ctx){
        this.ctx = ctx;
        manager = CmdManager.create(ctx);
    }

    @Override
    public void execute(String text) {

        if(!manager.isSettingsSet()){
            showToast(ctx.getString(R.string.ip_not_set));
            return;
        }

        showToast("Contacting server at "+manager.getApplicationIP());

        CmdRequest r = CmdRequest
                            .create()
                            .setDeviceName(manager.getDeviceName())
                            .setUser(manager.getDeviceUser())
                            .setText(text)
                            ;
        RestInteractor
                .getApplicationApi(manager.getApplicationIP())
                .execute(r,this);
    }

    private void showToast(String message){
        Toast.makeText(ctx,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void success(CmdResponse cmdResponse, Response response) {
        showToast(cmdResponse.getMessage());
        ((Activity) ctx).finish();
    }

    @Override
    public void failure(RetrofitError error) {
        showToast("Error in contacting the server");
        ((Activity) ctx).finish();
    }
}
