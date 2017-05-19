package com.mingren.lib.baselibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mingren.lib.baselibrary.inject.InjectView;


public class Fragment1 extends BaseFragment {

    TextView tv;
    @Override
    protected int getLayoutId() {
        return  R.layout.fragment_fragment1;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
     tv = (TextView) view.findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("tabhost");
                intent.putExtra("id",1);
                getActivity().sendBroadcast(intent);
            }
        });
    }


}
