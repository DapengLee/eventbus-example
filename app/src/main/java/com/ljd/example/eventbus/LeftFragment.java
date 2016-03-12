package com.ljd.example.eventbus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;


/**
 * A simple {@link Fragment} subclass.
 */
public class LeftFragment extends Fragment {

    private LinearLayout buttonLinear;
    public LeftFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left, container, false);
        buttonLinear = (LinearLayout)view.findViewById(R.id.left_fragment_linear);
        sendEvent();
        return view;
    }

    private void sendEvent(){
        Button button = new Button(getActivity());
        button.setText("SEND");
        buttonLinear.addView(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent("Hello everyone!"));
            }
        });
    }
}
