package info.mycommander.mycommander.Model;


public class CmdRequest {

    protected String deviceName;
    protected String user;
    protected String text;

    public static CmdRequest create(){
        return new CmdRequest();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public CmdRequest setDeviceName(String deviceName) {
        this.deviceName = deviceName;
        return this;
    }

    public String getUser() {
        return user;
    }

    public CmdRequest setUser(String user) {
        this.user = user;
        return this;
    }

    public String getText() {
        return text;
    }

    public CmdRequest setText(String text) {
        this.text = text;
        return this;
    }
}
