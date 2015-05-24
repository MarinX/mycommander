package info.mycommander.mycommander.Presenter;


import android.app.AlertDialog;
import android.content.Context;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

import info.mycommander.mycommander.R;

public class SettingsPresenterImpl implements SettingsPresenter {

    private Context ctx;
    private AlertDialog.Builder builder;

    public static SettingsPresenterImpl create(Context ctx){
        return new SettingsPresenterImpl(ctx);
    }

    private SettingsPresenterImpl(Context ctx){
        this.ctx = ctx;
        builder = new AlertDialog.Builder(ctx);
    }

    @Override
    public void onApplicationClick() {
        builder.setTitle("Application")
                .setMessage(ctx.getString(R.string.application_about))
                .setCancelable(true)
                .setPositiveButton("OK", null)
                .show();
    }

    @Override
    public void onLicenceClick() {
        builder.setTitle("License")
                .setMessage(ctx.getString(R.string.application_license))
                .setCancelable(true)
                .setPositiveButton("OK", null)
                .show();
    }

    @Override
    public void onOpenSorceClick() {
        builder.setTitle("Source")
                .setMessage(ctx.getString(R.string.application_opensource))
                .setCancelable(true)
                .setPositiveButton("OK", null)
                .show();
    }
}
