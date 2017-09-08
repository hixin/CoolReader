package hixin.com.github.coolreader.model.cache;

import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import hixin.com.github.coolreader.bean.ArticleBean;
import hixin.com.github.coolreader.bean.KnowledgeBean;
import hixin.com.github.coolreader.utils.CONSTANT;
import hixin.com.github.coolreader.utils.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import com.google.gson.Gson;

/**
 * Created by hixin on 2017/9/6.
 *
 * @version CoolReader 1.0
 */

public class KnowledgeCache extends BaseCache<ArticleBean>{
    private static final String TAG = "KnowledgeCache";

    public KnowledgeCache(Handler handler, String category, String url) {
        super(handler, category, url);
    }

    @Override
    public void saveCache() {

    }

    @Override
    public void loadFromCache() {

    }

    @Override
    public void loadFromNet() {
        HttpUtil.sendOkHttpRequest(mUrl, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.sendEmptyMessage(CONSTANT.ID_FAILURE);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() == false) {
                    mHandler.sendEmptyMessage(CONSTANT.ID_FAILURE);
                    return;
                }

                ArrayList<String> collectionTitles = new ArrayList<String>();
                for(int i = 0 ; i<mList.size() ; i++ ){
                    if(mList.get(i).getIs_collected() == 1){
                        collectionTitles.add(mList.get(i).getTitle());
                    }
                }

                mList.clear();
                Gson gson = new Gson();
                ArticleBean[] articleBeans = (gson.fromJson(response.body().string(), KnowledgeBean.class)).getResult();
                for (ArticleBean articleBean : articleBeans) {
                    articleBean.setInfo(getmCategory());//为每条消息增加分类
                    mList.add(articleBean);
                }

                for(String title:collectionTitles){
                    for(int i=0 ; i<mList.size() ; i++){
                        if(title.equals(mList.get(i).getTitle())){
                            mList.get(i).setIs_collected(1);
                        }
                    }
                }
                mHandler.sendEmptyMessage(CONSTANT.ID_SUCCESS);
            }
        });

    }
}
