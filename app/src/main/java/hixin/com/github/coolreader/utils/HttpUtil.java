package hixin.com.github.coolreader.utils;

/**
 * Created by hixin on 2017/9/5.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;


public class HttpUtil {
    public static final int NETWORK_UNCONNECTED=0;
    public static final int NETWORK_CONNECTED=1;
    public static final int NETWORK_CONNECTEDWIFI=2;

    private static final String TAG = "HttpUtil";

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        Log.d(TAG, "RequestUrl: " +address);
        client.newCall(request).enqueue(callback);
    }

    //
    public static int readNetworkState() {

        ConnectivityManager cm = (ConnectivityManager) CoolReaderApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
            boolean isWIFI = (cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI);
            if(isWIFI){
                return NETWORK_CONNECTEDWIFI;
            }else{
                return NETWORK_CONNECTED;
            }
        } else {
            return NETWORK_UNCONNECTED;
        }
    }
}
