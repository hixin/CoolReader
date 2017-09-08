package hixin.com.github.coolreader.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import hixin.com.github.coolreader.R;
import hixin.com.github.coolreader.adapter.PagerAdapter;
import hixin.com.github.coolreader.basic.BaseFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

/**
 * Created by hixin on 2017/9/6.
 */

public abstract class TabBaseFragment  extends BaseFragment{
    private static final String TAG = "TabBaseFragment";
    private   SmartTabLayout smartTabLayout;
    protected View parentView;
    protected ViewPager viewPager;
    protected PagerAdapter pagerAdapter;

    protected abstract  PagerAdapter initPagerAdapter();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = View.inflate(getContext(), R.layout.pager_navigation,null);
        viewPager = (ViewPager) parentView.findViewById(R.id.inner_viewpager);
        smartTabLayout = (SmartTabLayout) getActivity().findViewById(R.id.tab_layout);
        smartTabLayout.setVisibility(View.VISIBLE);
        pagerAdapter = initPagerAdapter();
        viewPager.setAdapter(pagerAdapter);
        smartTabLayout.setViewPager(viewPager);
        return parentView;
    }
}
