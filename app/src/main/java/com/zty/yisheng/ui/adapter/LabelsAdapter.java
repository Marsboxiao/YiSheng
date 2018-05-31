package com.zty.yisheng.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zty.yisheng.R;
import com.zty.yisheng.model.bean.LabelBean;
import com.zty.yisheng.model.bean.LabelListBean;

import java.util.List;

/**
 * Created by MarsBoxiao on 2017/5/9.
 */

public class LabelsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<LabelListBean.DataBean> mList;
    private static final int VIEW_TYPE = -1;
    private static final int BODY_TYPE = -3;
    private OnItemClickListener mOnItemClickListener;

    public LabelsAdapter(Context mContext, List<LabelListBean.DataBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (viewType == BODY_TYPE) {
            return new KnowledgeViewHolder(inflater.inflate(R.layout.item_knows_recy, parent, false));
        }else {
            return new EmptyViewHolder(inflater.inflate(R.layout.item_null,parent,false));
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
            knowledgestitle=(TextView) itemView.findViewById(R.id.knows_title);
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
