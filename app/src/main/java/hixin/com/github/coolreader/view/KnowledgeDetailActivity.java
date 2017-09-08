package hixin.com.github.coolreader.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;

import hixin.com.github.coolreader.bean.ArticleBean;
import hixin.com.github.coolreader.model.KnowledgeContentParser;
import hixin.com.github.coolreader.model.KnowledgeTitle;
import hixin.com.github.coolreader.model.cache.KnowledgeCache;
import hixin.com.github.coolreader.utils.DisplayUtil;
import hixin.com.github.coolreader.utils.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import com.bumptech.glide.Glide;

/**
 * Created by hixin on 2017/9/7.
 *
 * @version CoolReader 1.0
 */

public class KnowledgeDetailActivity  extends BaseDetailsActivity{
    private static final String TAG = "KnowledgeDetailActivity";

    private KnowledgeCache mCache;
    private ArticleBean articleBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        articleBean = (ArticleBean) getIntent().getSerializableExtra("id_knowledge");
        initView();
    }

    @Override
    protected void initView() {
        super.initView();
        collapsingToolbar.setTitle(KnowledgeTitle.maps.get(articleBean.getInfo()));
        Glide.with(this).load(articleBean.getSmall_image()).into(topImage);
        progressBar.setVisibility(View.VISIBLE);
        contentView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }
        });

        /*
        *本来是控制背景图片的，采用可折叠式标题栏不再需要
        scrollView.setVisibility(View.VISIBLE);
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.d(TAG, "onScrollChange: ");
                //topImage.setTranslationY(Math.max(-scrollY / 2, -DisplayUtil.dip2px(getBaseContext(), 170)));
            }
        });
        */
    }

    @Override
    protected void refreshData() {
        Log.d(TAG, "refreshData: "+articleBean.getUrl());
        HttpUtil.sendOkHttpRequest(articleBean.getUrl(), new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        networkBtn.setVisibility(View.VISIBLE);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String rawData = response.body().string();
                Log.d(TAG, "onResponse: ");
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        KnowledgeContentParser myParse = new KnowledgeContentParser(rawData);
                        String data = myParse.getEndStr();
                        Log.d(TAG, "pageData: "+data.length());
                        progressBar.setVisibility(View.GONE);
                        networkBtn.setVisibility(View.GONE);
                        contentView.loadDataWithBaseURL("file:///android_asset/", "<link rel=\"stylesheet\" type=\"text/css\" href=\"guokr.css\" />" + data, "text/html", "utf-8", null);
                    }
                });

            }
        });

    }

    @Override
    protected void removeFromCollection() {

    }

    @Override
    protected void addToCollection() {

    }

    @Override
    protected String getShareInfo() {
        return null;
    }
}
