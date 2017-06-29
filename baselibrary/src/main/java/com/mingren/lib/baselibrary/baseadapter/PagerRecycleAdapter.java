package com.mingren.lib.baselibrary.baseadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingren.lib.baselibrary.R;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */

public class PagerRecycleAdapter extends RecyclerView.Adapter<PagerRecycleAdapter.ViewHolder> {

    List<String> list;
    Context context;
    OnItemClickLisitener onItemClickLisitener;
    int perPosition = 0;
    boolean isClick = false;

    public PagerRecycleAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void select(int position){
        if (isClick){
            isClick = false;
        }else{
            perPosition = position;
            notifyDataSetChanged();
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                context).inflate(R.layout.recycleitem, parent,
                false);
        ViewHolder holder = new ViewHolder(view);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClick = true;
                int p = (int) v.getTag();
                perPosition = p;
                if (onItemClickLisitener != null){
                    onItemClickLisitener.OnItemClick((Integer) v.getTag());
                }

                notifyDataSetChanged();
            }
        });

        return holder;

    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.textView.setText(list.get(position));

        if (perPosition == position){
            holder.textView.setTextColor(context.getResources().getColor(R.color.text_blue));
            holder.imageView.setVisibility(View.VISIBLE);
        }else{
            holder.textView.setTextColor(context.getResources().getColor(R.color.text_black));
            holder.imageView.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends  RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        View itemView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
            imageView = (ImageView) itemView.findViewById(R.id.iv_line);
            this.itemView = itemView;
        }
    }

    public void setOnItemClickLisitener(OnItemClickLisitener onItemClickLisitener){
        this.onItemClickLisitener = onItemClickLisitener;
    }

    public static interface OnItemClickLisitener{
        void OnItemClick(int position);
    }

}
