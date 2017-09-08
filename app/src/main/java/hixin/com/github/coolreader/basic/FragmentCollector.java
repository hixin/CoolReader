package hixin.com.github.coolreader.basic;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hixin on 2017/9/6.
 */

public class FragmentCollector {
    public static List<Fragment> fragments = new ArrayList<>();

    public static void addFragment(Fragment fragment){
        fragments.add(fragment);
    }

    public static void removeFragment(Fragment fragment){
        fragments.remove(fragment);
    }
}
