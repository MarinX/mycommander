package info.mycommander.mycommander.View;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.util.Log;

import info.mycommander.mycommander.Presenter.SettingsPresenter;
import info.mycommander.mycommander.Presenter.SettingsPresenterImpl;
import info.mycommander.mycommander.R;

public class SettingsActivity extends PreferenceActivity implements Preference.OnPreferenceClickListener {

    private SettingsPresenter presenter;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        presenter = SettingsPresenterImpl.create(this);
        setupSimplePreferencesScreen();

    }

    private void setupSimplePreferencesScreen() {

        addPreferencesFromResource(R.xml.pref_general);


        PreferenceCategory cgServer = new PreferenceCategory(this);
        cgServer.setTitle(R.string.pref_title_server);
        getPreferenceScreen().addPreference(cgServer);
        addPreferencesFromResource(R.xml.pref_server);


        PreferenceCategory cgAbout = new PreferenceCategory(this);
        cgAbout.setTitle(R.string.pref_title_about);
        getPreferenceScreen().addPreference(cgAbout);
        addPreferencesFromResource(R.xml.pref_about);

        findPreference("pref_about").setOnPreferenceClickListener(this);
        findPreference("pref_licence").setOnPreferenceClickListener(this);
        findPreference("pref_opensource").setOnPreferenceClickListener(this);

        bindPreferenceSummaryToValue(findPreference("server_ip"));



    }



    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            preference.setSummary(stringValue);

            return true;
        }
    };


    private static void bindPreferenceSummaryToValue(Preference preference) {

        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()){
            case "pref_about":
                presenter.onApplicationClick();
                break;
            case "pref_licence":
                presenter.onLicenceClick();
                break;
            case "pref_opensource":
                presenter.onOpenSorceClick();
                break;
        }
        return false;
    }
}
