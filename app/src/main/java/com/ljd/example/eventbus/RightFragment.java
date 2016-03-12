package com.ljd.example.eventbus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;



/**
 * A simple {@link Fragment} subclass.
 */
public class RightFragment extends Fragment {

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

    @Subscribe
    public void onMessage(MessageEvent event) {
        TextView textView = new TextView(getActivity());
        textView.setText(event.message);
        mTextViewLinear.addView(textView);
    }
}
