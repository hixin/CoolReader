package hixin.com.github.coolreader.utils;

import android.app.Application;
import android.content.Context;

import hixin.com.github.coolreader.model.KnowledgeTitle;

/**
 * Created by hixin on 2017/9/6.
 *
 * @version CoolReader 1.0
 */

public class CoolReaderApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        KnowledgeTitle.initMap();
       context  = getApplicationContext();
    }
    public static Context getContext(){
        return context;
    }
}
