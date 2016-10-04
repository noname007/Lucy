package com.lqcode.lucytv;

/**
 * Created by Administrator on 2016/9/2.
 */
public final class Constants {
    public static final String serverPrefix = TVSettings.getInstance().getServerPrefix();
    public static final String desktopUrl = serverPrefix + "TVServlet";
    public static final String liveUrl = serverPrefix + "TVServlet";
    public static final String cctvListUrl = serverPrefix + "TransmitApiServlet";
    public static final String cctvThumbnailUrl = serverPrefix + "CCTVThumbnail";
    public static final String cctvPlayerUrl = serverPrefix + "PlayerUrlServlet";
    public static final String movieListUrl = serverPrefix + "VideoServlet";
    public static final String moviePlayerUrl = serverPrefix + "MoviePlayerServlet?id=";

}
