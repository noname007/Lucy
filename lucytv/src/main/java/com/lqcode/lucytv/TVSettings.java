package com.lqcode.lucytv;

/**
 * Created by Administrator on 2016/9/7.
 */
public class TVSettings {
    private final String colon = ":";
    private final String slash = "/";
    private static TVSettings ourInstance = new TVSettings();

    public static TVSettings getInstance() {
        return ourInstance;
    }

    private TVSettings() {

    }

    public String getServerPrefix() {
        return getServerHead() +//
                colon +//
                slash +//
                slash +//
                getServerIP() +//
                getServerPort() +//
                slash +//
                getServerName() +//
                slash;
    }

    private String serverHead;
    private String serverIP;
    private String serverPort;
    private String serverName;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public String getServerHead() {
        return serverHead;
    }

    public void setServerHead(String serverHead) {
        this.serverHead = serverHead;
    }

}
