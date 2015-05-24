package info.mycommander.mycommander.Model;


import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Patterns;

import java.util.regex.Pattern;

public class CmdManager {

    public static final String KEY_IP = "server_ip";

    private SharedPreferences preferences;
    private Account[] accounts;
    private String deviceUser;

    public static CmdManager create(Context ctx){
        return new CmdManager(ctx);
    }

    private CmdManager(Context ctx){
        preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        accounts = AccountManager.get(ctx).getAccounts();
    }

    public boolean isSettingsSet(){
        String ip = preferences.getString(KEY_IP,"");
        return ip.length() != 0;
    }

    public String getApplicationIP() {
        return preferences.getString(KEY_IP,"");
    }

    public String getDeviceName(){
        return android.os.Build.MODEL;
    }

    public String getDeviceUser(){
        if(deviceUser != null){
            return deviceUser;
        }

        Pattern emailPattern = Patterns.EMAIL_ADDRESS;
        String name = "";
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                name = account.name;
                int index = name.indexOf('@');
                name = name.substring(0,index);
            }
        }

        deviceUser = name;
        return name;
    }
}
