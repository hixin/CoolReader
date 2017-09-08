package hixin.com.github.coolreader.model.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import hixin.com.github.coolreader.utils.CoolReaderApplication;
import hixin.com.github.coolreader.utils.DatabaseHelper;

/**
 * Created by hixin on 2017/9/6.
 *
 * @version CoolReader 1.0
 */

public abstract class BaseCache<T> implements ICache<T> {
    protected Context mContext = CoolReaderApplication.getContext();
    protected DatabaseHelper mHelper;
    protected SQLiteDatabase db;

    protected Handler mHandler;
    protected String mCategory;

    protected String mUrl;
    protected String[] mUrls;

    protected ContentValues values;
    protected List<T> mList = new ArrayList<>();

    public  abstract void loadFromCache();

    public BaseCache() {
        mHelper = DatabaseHelper.instance(mContext);
    }

    protected BaseCache(Handler handler, String category){
        mHelper = DatabaseHelper.instance(mContext);
        mCategory = category;
        mHandler = handler;
    }
    protected BaseCache(Handler handler,String category,String[] urls){
        this(handler,category);
        mUrls = urls;
    }
    protected BaseCache(Handler handler,String category,String url){
        this(handler,category);
        mUrl = url;
    }

    public synchronized void cache(){
        db = mHelper.getWritableDatabase();
        db.beginTransaction();
        values = new ContentValues();
        saveCache();
        db.setTransactionSuccessful();
        db.endTransaction();
    }
    public List<T> getmList(){
        return mList;
    }

    public String getmCategory() {
        return mCategory;
    }
}
