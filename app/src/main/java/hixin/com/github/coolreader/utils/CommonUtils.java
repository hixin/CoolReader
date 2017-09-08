package hixin.com.github.coolreader.utils;

import android.content.Context;

import java.io.InputStream;

/**
 * Created by hixin on 2017/9/7.
 *
 * @version CoolReader 1.0
 */

public class CommonUtils {
    private static Context mContext = CoolReaderApplication.getContext();
    public static InputStream readFileFromRaw(int fileID){
        return mContext.getResources()
                .openRawResource(fileID);
    }

    public static boolean hasString(String str){
        if(str == null || str.equals("")) return false;
        return true;
    }
}
