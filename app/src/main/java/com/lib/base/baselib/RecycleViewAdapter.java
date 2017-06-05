package com.lib.base.baselib;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecyHolder> {
    List<String> list;
    Context context;

    public RecycleViewAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyHolder holder = new RecyHolder(LayoutInflater.from(
                context).inflate(R.layout.layout, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyHolder holder, int position) {
            holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class  RecyHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public RecyHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);

        }
    }


}
