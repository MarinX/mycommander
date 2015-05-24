package info.mycommander.mycommander.View;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Locale;

import info.mycommander.mycommander.Presenter.VoicePresenter;
import info.mycommander.mycommander.Presenter.VoicePresenterImpl;
import info.mycommander.mycommander.R;

public class VoiceActivity extends ActionBarActivity implements View.OnClickListener {

    public static final int VOICE_CODE = 1712;
    private VoicePresenter presenter;
    private Dialog dialog;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = VoicePresenterImpl.create(this);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.spinner_layout);
        dialog.setCancelable(true);
        dialog.setTitle(getString(R.string.dialog_title));

        spinner = (Spinner)dialog.findViewById(R.id.spinner);
        Button btnOk = (Button) dialog.findViewById(R.id.btn_ok);

        btnOk.setOnClickListener(this);

        startActivityForResult(getVoiceIntent(), VOICE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode != VOICE_CODE || data == null){
            return;
        }

        ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        spinner.setAdapter(getSpinnerAdapter(result));

        dialog.show();



    }

    private Intent getVoiceIntent(){
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.ENGLISH);
        return i;
    }

    private ArrayAdapter<String> getSpinnerAdapter(ArrayList<String> result){
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, result);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        return spinnerArrayAdapter;
    }


    @Override
    public void onClick(View view) {
        dialog.dismiss();
        String selected = (String)spinner.getSelectedItem();
        presenter.execute(selected);
        finish();
    }
}
