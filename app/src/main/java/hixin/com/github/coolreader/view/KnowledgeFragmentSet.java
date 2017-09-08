package hixin.com.github.coolreader.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import hixin.com.github.coolreader.adapter.PagerAdapter;
import hixin.com.github.coolreader.model.KnowledgeTitle;

/**
 * Created by hixin on 2017/9/6.
 */

public class KnowledgeFragmentSet extends TabBaseFragment{
    private static final String TAG = "KnowledgeFragmentSet";
    private PagerAdapter pagerAdapter;

    @Override
    protected PagerAdapter initPagerAdapter() {
        pagerAdapter = new PagerAdapter(getChildFragmentManager(), KnowledgeTitle.channel_title) {
            @Override
            public Fragment getItem(int position) {
                KnowledgeFragmentElement fragmentElement = new KnowledgeFragmentElement();
                Bundle bundle = new Bundle();
                bundle.putInt("position",position);
                bundle.putSerializable("category", KnowledgeTitle.channel_tag[position]);
                Log.d(TAG, "FragmentIndicator: "+KnowledgeTitle.channel_tag[position]);
                fragmentElement.setArguments(bundle);
                return fragmentElement;
            }
        };
        return pagerAdapter;
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: Parent");
        Log.d(TAG, "onDestroyView: FragmentsNum1:"+getChildFragmentManager().getFragments().size());
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: Parent1");
        Log.d(TAG, "onDestroyView: FragmentsNum2:"+getChildFragmentManager().getFragments().size());

    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: Parent");
        Log.d(TAG, "onDestroy: FragmentsNum3:"+getChildFragmentManager().getFragments().size());
        super.onDestroy();
        Log.d(TAG, "onDestroy: FragmentsNum4:"+getChildFragmentManager().getFragments().size());
        if(getChildFragmentManager().getFragments()!=null){
            getChildFragmentManager().getFragments().clear();
        }

        Log.d(TAG, "onDestroy: FragmentsNum5:"+getChildFragmentManager().getFragments().size());

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: Parent");
    }
}

