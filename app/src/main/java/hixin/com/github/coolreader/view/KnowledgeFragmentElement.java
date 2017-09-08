package hixin.com.github.coolreader.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;

import hixin.com.github.coolreader.R;
import hixin.com.github.coolreader.adapter.KnowledgeAdapter;
import hixin.com.github.coolreader.basic.BaseFragment;
import hixin.com.github.coolreader.model.KnowledgeTitle;
import hixin.com.github.coolreader.model.cache.KnowledgeCache;
import hixin.com.github.coolreader.utils.CONSTANT;
import hixin.com.github.coolreader.utils.CoolReaderApplication;

/**
 * Created by hixin on 2017/9/6.
 * before use SimpleDraweeView,you should run the below sentence first,
 * Fresco.initialize(getActivity());
 */

public class KnowledgeFragmentElement extends BaseFragment{

    private static final String TAG = "KnowledgeFragmentElemen";
    protected View parentView;
    protected RecyclerView recyclerView;
    protected View placeHolder;
    protected SwipeRefreshLayout swipeRefresh;
    protected RecyclerView.LayoutManager mLayoutManager;
    private String mCategory;
    private String mUrl;
    private KnowledgeAdapter knowledgeAdapter;
    private KnowledgeCache knowledgeCache;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getActivity());// very important
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        
        parentView = inflater.inflate(R.layout.common_list_layout, container, false);
        recyclerView = (RecyclerView) parentView.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(CoolReaderApplication.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        placeHolder = (View) parentView.findViewById(R.id.placeholder);
        getArgs();
        Log.d(TAG, "onCreateView: "+getArguments().getString("category"));
        knowledgeCache = new KnowledgeCache(handler, mCategory, mUrl);
        knowledgeCache.loadFromNet();
        swipeRefresh = (SwipeRefreshLayout) parentView.findViewById(R.id.pull_to_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                knowledgeCache.loadFromNet();
            }
        });
        return  parentView;

    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: "+getArguments().getString("category"));
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: "+getArguments().getString("category"));
        super.onDestroy();
    }

    public void getArgs(){
        mUrl = KnowledgeTitle.konwledge_channel_url+KnowledgeTitle.channel_tag[getArguments().getInt("position")];
        mCategory = getArguments().getString("category");
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case CONSTANT.ID_FAILURE:
                    if(isAdded()) {
                        Log.d(TAG, "handleMessage: fail");
                    }
                    break;
                case CONSTANT.ID_SUCCESS:
                    if(isAdded()) {
                        Log.d(TAG, "handleMessage: sucess");
                        Log.d(TAG, "handleMessage: listsize:" +knowledgeCache.getmList().size());
                        knowledgeAdapter = new KnowledgeAdapter(knowledgeCache.getmList());
                        recyclerView.setAdapter(knowledgeAdapter);
                        knowledgeAdapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);

                    }
                    break;
            }
            return true;
        }
    });

}
