package com.lqcode.lucytv;

/**
 * Created by Administrator on 2016/9/2.
 */
public final class Constants {

    //    public static final String desktopUrl = "https://github.com/jingjing0327/tv_show/blob/master/TVList.json?raw=true";
    public static final String desktopUrl = TVSettings.getInstance().getServerPrefix() + "TVServlet";
    public static final String liveUrl = TVSettings.getInstance().getServerPrefix() + "TVServlet";
    //    public static final String cctvCDNUrl = TVSettings.getInstance().getServerPrefix()+"http://vdn.live.cntv.cn/api2/liveHtml5.do?client=flash&channel=pa://cctv_p2p_hdcctv1";
    public static final String cctvListUrl = TVSettings.getInstance().getServerPrefix() + "TransmitApiServlet";
    public static final String cctvThumbnailUrl = TVSettings.getInstance().getServerPrefix() + "CCTVThumbnail";
    public static final String cctvPlayerUrl = TVSettings.getInstance().getServerPrefix() + "PlayerUrlServlet";
    public static final String movieListUrl = TVSettings.getInstance().getServerPrefix() + "MovieListServlet";
    public static final String moviePlayerUrl = TVSettings.getInstance().getServerPrefix() + "MoviePlayerServlet?id=";

}
