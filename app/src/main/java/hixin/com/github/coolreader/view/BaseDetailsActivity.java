package hixin.com.github.coolreader.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ScrollingView;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.IOException;

import hixin.com.github.coolreader.R;
import hixin.com.github.coolreader.basic.SwipeBackActivity;
import hixin.com.github.coolreader.utils.CommonUtils;
import hixin.com.github.coolreader.utils.CoolReaderApplication;
import hixin.com.github.coolreader.utils.DisplayUtil;
import hixin.com.github.coolreader.utils.HttpUtil;
import hixin.com.github.coolreader.utils.ImageUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hixin on 2017/9/7.
 *
 * @version CoolReader 1.0
 */

public abstract class BaseDetailsActivity extends SwipeBackActivity {

    private static final String TAG = "BaseDetailsActivity";
    protected Toolbar toolbar;
    protected CollapsingToolbarLayout collapsingToolbar;
    protected WebView contentView;
    protected SimpleDraweeView topImage;
    protected NestedScrollView scrollView;
    protected CoordinatorLayout mainContent;
    protected ProgressBar progressBar;
    protected ImageButton networkBtn;
    protected boolean isCollected;


    protected abstract void refreshData();

    private int mLang = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_details_bettter);
    }


    public void displayLoading() {
        if(progressBar != null){
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideLoading() {
        if(progressBar != null){
            progressBar.setVisibility(View.GONE);
        }
    }

    public void displayNetworkError() {
        if(networkBtn != null){
            networkBtn.setVisibility(View.VISIBLE);
        }
    }

    protected void initView() {
        //对toolbar进行下移
      /*  int height = DisplayUtil.getScreenHeight(CoolReaderApplication.getContext());
        LinearLayout ll = (LinearLayout) findViewById(R.id.stbar);
        LinearLayout.LayoutParams llp = (LinearLayout.LayoutParams) ll.getLayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            llp.height = (int) (height * 0.03);
            ll.setLayoutParams(llp);
        }*/
        mainContent = (CoordinatorLayout) findViewById(R.id.main_content);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        networkBtn = (ImageButton) findViewById(R.id.networkBtn);
        topImage = (SimpleDraweeView) findViewById(R.id.topImage);
        scrollView = (NestedScrollView) findViewById(R.id.scrollView);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
         });
        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.top_gradient));
        contentView = (WebView) findViewById(R.id.content_view);

        contentView.getSettings().setJavaScriptEnabled(true);

        // 开启缓存
        contentView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        contentView.getSettings().setDomStorageEnabled(true);
        contentView.getSettings().setDatabaseEnabled(true);

        contentView.getSettings().setBlockNetworkImage(false);

        contentView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideLoading();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                displayNetworkError();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                contentView.loadUrl(url);
                return false;
            }
        });





        /**
         * 网络异常就显示
         */
        networkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                refreshData();
            }
        });

        refreshData();
    }



    /**
     * 设置布局背景，其实就是边缘空隙的颜色，颜色取自顶部图片的主色调
     *
     * @param url
     */
    protected void setMainContentBg(final String url) {
        if (CommonUtils.hasString(url) == false) {
            setDefaultColor();
            return;
        }

        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {

                    @Override
                    public void run() {
                        Log.d(TAG, "onFailure: "+ url);
                        setDefaultColor();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if(bitmap == null){
                            setDefaultColor();
                            Log.d(TAG, "onResponse bitmap null: " + url);
                            return;
                        }
                       /* if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                            topImage.setBackground(new BitmapDrawable(getResources(), bitmap));
                        } else{
                            topImage.setImageURI(Uri.parse(url));
                        }*/
                        Log.d(TAG, "onResponse: " + url);
                        mainContent.setBackgroundColor(ImageUtil.getImageColor(bitmap));

                    }
                });
            }
        });

    }


    protected void setDefaultColor(){
        mainContent.setBackgroundColor(Color.rgb(67,76,66));
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share,menu);
        updateCollectionMenu(menu.findItem(R.id.menu_collect));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, getShareInfo());
            startActivity(Intent.createChooser(sharingIntent,getString(R.string.hint_share_to)));
            return super.onOptionsItemSelected(item);
        }else if(item.getItemId() == R.id.menu_collect){
            if(isCollected){
                removeFromCollection();
                isCollected = false;
                updateCollectionMenu(item);
                Snackbar.make(mainContent, R.string.notify_remove_from_collection,Snackbar.LENGTH_SHORT).show();
            }else {
                addToCollection();
                isCollected = true;
                updateCollectionMenu(item);
                Snackbar.make(mainContent, R.string.notify_add_to_collection,Snackbar.LENGTH_SHORT).show();

            }
        }
        return true;
    }

    protected void updateCollectionMenu(MenuItem item){
        if(isCollected){
            item.setIcon(R.mipmap.ic_star_black);
        }else {
            item.setIcon(R.mipmap.ic_star_white);
        }
    }
    protected abstract void removeFromCollection();
    protected abstract void addToCollection();
    protected abstract String getShareInfo();

}

