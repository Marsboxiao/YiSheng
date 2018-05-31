package com.zty.yisheng.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kevin.wraprecyclerview.BaseRecyclerAdapter;
import com.zty.yisheng.R;
import com.zty.yisheng.model.bean.ClassBean;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 92915 on 2018/4/18.
 */

public class KnowledgeAdapter extends BaseRecyclerAdapter<ClassBean.DataBean,KnowledgeAdapter.MyViewHolder> {

    private OnItemClickListener mOnItemClickListener;

    public KnowledgeAdapter(Context context) {
        super(context);
    }

    public KnowledgeAdapter(Context mContext, LinkedList mItemLists) {
        super(mContext, mItemLists);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_know_recy, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.knowledgetext.setText(mItemLists.get(position).getClassname());
        if (mItemLists.get(position).getImgname() != null) {
            Glide.with(mContext).load(mItemLists.get(position).getImgname()).into(holder.knowledgeimage);
        }
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(v,position);
                }
            });
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView knowledgeimage;
        TextView knowledgetext;

        public MyViewHolder(View view) {
            super(view);
            knowledgeimage=itemView.findViewById(R.id.know_image);
            knowledgetext = itemView.findViewById(R.id.fun_text);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

}
