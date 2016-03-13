package com.ljd.example.eventbus;


import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;



/**
 * A simple {@link Fragment} subclass.
 */
public class RightFragment extends Fragment {

    private final String TAG = "rightFragment";
    private LinearLayout mTextViewLinear;

    public RightFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right, container, false);
        mTextViewLinear = (LinearLayout)view.findViewById(R.id.right_fragment_linear);
        return view;
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

/*
    @Subscribe
    public void onMessage(MessageEvent event) {
        TextView textView = new TextView(getActivity());
        textView.setText(event.message);
        mTextViewLinear.addView(textView);
    }
*/

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onPostingModeMessage(MessageEvent event){

        Log.d(TAG, getResultString("ThreadMode:POSTING",event.message));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainModeMessage(MessageEvent event){

        Log.d(TAG, getResultString("ThreadMode:MAIN",event.message));
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onBackgroundModeMessage(MessageEvent event){

        Log.d(TAG, getResultString("ThreadMode:BACKGROUND",event.message));
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onAsyncModeMessage(MessageEvent event){

        Log.d(TAG, getResultString("ThreadMode:ASYNC",event.message));
    }

    private String getResultString(String threadMode,String msg){
        StringBuilder sb = new StringBuilder("");
        sb.append(threadMode)
                .append("\n接收到的消息：")
                .append(msg)
                .append("\n线程id:")
                .append(Thread.currentThread().getId())
                .append("\n是否是主线程：")
                .append(Looper.getMainLooper() == Looper.myLooper())
                .append("\n");
        return sb.toString();
    }
}
