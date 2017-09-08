package hixin.com.github.coolreader.view;

import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import hixin.com.github.coolreader.R;
import hixin.com.github.coolreader.bean.Weather;

/**
 * Created by hixin on 2017/9/6.
 */

public class ViewHolderWeather {
    private static final String TAG = "ViewHolderWeather";
    TextView cityText;
    TextView tempText;
    TextView weatherText;
    private static ViewHolderWeather instance = null;

    private ViewHolderWeather(NavigationView view){
        cityText = (TextView) view.getHeaderView(0).findViewById(R.id.position);
        tempText = (TextView) view.getHeaderView(0).findViewById(R.id.temp);
        weatherText = (TextView) view.getHeaderView(0).findViewById(R.id.weather);
    }

    public static synchronized  ViewHolderWeather getInstance(NavigationView navigationView){
        if(null == instance){
            instance = new ViewHolderWeather(navigationView);
        }
        return instance;
    }
    public void setContentView(Weather weather){
        Log.d(TAG, "setContentView:"+weather.toString());
        cityText.setText(weather.getCity());
        tempText.setText(weather.getTemp());
        weatherText.setText(weather.getWeather());
    }

}
