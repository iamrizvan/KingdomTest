package work.rizvan.com.kingdomtest.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.format.Formatter;
import android.util.Log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import static android.content.Context.WIFI_SERVICE;

/**
 * Created by Ms2 on 2/6/2016.
 */
public class CheckNetworkConnection {
    public static boolean isConnectionAvailable(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
                if (netInfo != null && netInfo.isConnected() && netInfo.isConnectedOrConnecting() && netInfo.isAvailable()) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }
}
