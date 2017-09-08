package hixin.com.github.coolreader.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by hixin on 2017/9/6.
 */

public abstract class PagerAdapter extends FragmentStatePagerAdapter{

    private String [] titles;
    public PagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles =titles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
