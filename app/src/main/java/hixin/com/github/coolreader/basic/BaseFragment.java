package hixin.com.github.coolreader.basic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hixin on 2017/9/6.
 */

public class BaseFragment  extends Fragment{
    private static final String TAG = "BaseFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: "+getClass().getSimpleName());
        FragmentCollector.addFragment(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: "+getClass().getSimpleName());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: "+getClass().getSimpleName());
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause: "+getClass().getSimpleName());
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: "+getClass().getSimpleName());
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: "+getClass().getSimpleName());
        FragmentCollector.removeFragment(this);
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach: "+getClass().getSimpleName());
        super.onDetach();
    }
}
