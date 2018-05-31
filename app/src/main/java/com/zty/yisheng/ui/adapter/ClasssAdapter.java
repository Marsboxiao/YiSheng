package com.zty.yisheng.ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zty.yisheng.R;
import com.zty.yisheng.model.bean.ClassBean;
import com.zty.yisheng.model.bean.ClassListBean;
import com.zty.yisheng.model.bean.LabelBean;

import java.util.ArrayList;
import java.util.List;

import javax.sql.StatementEvent;

import me.grantland.widget.AutofitTextView;
import me.gujun.android.taggroup.TagGroup;

/**
 * Created by MarsBoxiao on 2017/5/9.
 */

public class ClasssAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<ClassListBean.DataBean> mList;
    private static final int VIEW_TYPE = -1;
    private static final int BODY_TYPE = -3;
    private OnItemClickListener mOnItemClickListener;

    public ClasssAdapter(Context mContext, List<ClassListBean.DataBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (viewType == BODY_TYPE) {
            return new ClasssAdapter.KnowledgeViewHolder(inflater.inflate(R.layout.item_knows_recy, parent, false));
        }else {
            return new ClasssAdapter.EmptyViewHolder(inflater.inflate(R.layout.item_null,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
//        holder.setIsRecyclable(false);
        if (holder instanceof KnowledgeViewHolder) {
            ((KnowledgeViewHolder) holder).knowledgestitle.setText(mList.get(position).getKnowledgename());
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
        if (holder instanceof EmptyViewHolder) {
            ((EmptyViewHolder) holder).transferNull.setText(mContext.getString(R.string.kmmm_null));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.size() <= 0) {
            return VIEW_TYPE;
        }else {
            return BODY_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size() > 0 ? mList.size() : 1;
    }

    class KnowledgeViewHolder extends RecyclerView.ViewHolder {

        TextView knowledgestitle;

        public KnowledgeViewHolder(View itemView) {
            super(itemView);
            knowledgestitle=itemView.findViewById(R.id.knows_title);
        }
    }

    class EmptyViewHolder extends RecyclerView.ViewHolder {

        TextView transferNull;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            transferNull = (TextView) itemView.findViewById(R.id.item_order_null);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

}
