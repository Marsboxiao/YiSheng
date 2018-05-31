package com.zty.yisheng.common.utils;

import android.content.Context;
import android.graphics.YuvImage;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import android.util.Log;

import com.zty.yisheng.common.support.YiShengApplication;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import static android.content.Context.WIFI_SERVICE;

/**
 * Created by Marsboxiao on 2017/3/6.
 * 原生代码调用辅助类
 */

public class SystemUtil {

    //检查是否连接wifi
    public static boolean isWifiConnected() {
       ConnectivityManager connectivityManager = (ConnectivityManager) YiShengApplication.getInstance()
                .getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        String wifi = wifiInfo.getExtraInfo();
        return wifi != null;
    }

    //检查是否连接手机网络
    public static boolean isMobileNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) YiShengApplication.getInstance()
                .getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobilenetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager
                .TYPE_MOBILE);
        String mobile = mobilenetworkInfo.getExtraInfo();
        return mobile != null;
    }

    //检查是否有可用网络
    public static boolean isNetworkConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) YiShengApplication.getInstance()
                .getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    public static int getWifiSigint(){
        WifiManager wifiManager = (WifiManager) YiShengApplication.getInstance().getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int gsigint = wifiInfo.getRssi();
        return gsigint;
    }

    /**
     * wifi获取 路由ip地址
     * @return
     */
    public static String getWifiRouteIPAddress() {
        WifiManager wifi_service = (WifiManager) YiShengApplication.getInstance().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        DhcpInfo dhcpInfo = wifi_service.getDhcpInfo();
        //DhcpInfo中的ipAddress是一个int型的变量，通过Formatter将其转化为字符串IP地址
        String routeIp = Formatter.formatIpAddress(dhcpInfo.gateway);
        Log.d("TAG", "wifi route ip：" + routeIp);
        if (routeIp.isEmpty()) {
            routeIp="192.168.1.102";
        }
        return routeIp;
    }

    /***
     * 获取网关IP地址
     * @return
     */
    public static String getHostIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> ipAddr = intf.getInetAddresses(); ipAddr
                        .hasMoreElements();) {
                    InetAddress inetAddress = ipAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
        } catch (Exception e) {
        }
        return null;
    }



    /* 使用WIFI时，获取本机IP地址
     * @param mContext
     * @return
     */
    public static String getWIFILocalIpAdress() {

        //获取wifi服务
        WifiManager wifiManager = (WifiManager)YiShengApplication.getInstance().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //判断wifi是否开启
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = formatIpAddress(ipAddress);
        return ip;
    }
    private static String formatIpAddress(int ipAdress) {

        return (ipAdress & 0xFF ) + "." +
                ((ipAdress >> 8 ) & 0xFF) + "." +
                ((ipAdress >> 16 ) & 0xFF) + "." +
                ( ipAdress >> 24 & 0xFF) ;
    }

}
