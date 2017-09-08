package hixin.com.github.coolreader.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import hixin.com.github.coolreader.bean.Weather;

/**
 * Created by hixin on 2017/9/5.
 */

//城市IP请求地址： http://api.yytianqi.com/observe?city=ip&key=2gjorn4e1ajjo3gd
    
public class WeatherUtil {
    private static final String TAG = "WeatherUtil";
    public static Weather getWeatherinfo(String jsonData){
        try{
            Log.d(TAG, "json:"+jsonData);
            JSONObject jsonObject = new JSONObject(jsonData);
            String msg = jsonObject.getString("msg");
            if(msg.equalsIgnoreCase("sucess")){
                //JSONArray jsonArray = jsonObject.getJSONArray("list");
                JSONObject jsonObject_weatherinfo = jsonObject.getJSONObject("data");
                String city = jsonObject_weatherinfo.getString("cityName");
                String temp=jsonObject_weatherinfo.getString("qw")+"℃";
                String weather=jsonObject_weatherinfo.getString("tq");
                Log.d(TAG, "getWeatherinfo: city:"+city);
                Log.d(TAG, "getWeatherinfo: temp:"+temp);
                Log.d(TAG, "getWeatherinfo: weather:"+weather);
                return new Weather(city,temp,weather);
            }else{
                return null;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
