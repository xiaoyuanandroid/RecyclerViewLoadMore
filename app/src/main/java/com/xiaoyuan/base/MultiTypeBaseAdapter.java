package com.xiaoyuan.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoyuan.interfaces.OnMultiTypeItemClickListeners;
import com.xiaoyuan.viewholder.CommonViewHolder;

import java.util.List;

/**
 * @author xiaoyuan
 */
public abstract class MultiTypeBaseAdapter<T> extends BaseAdapter<T> {
    private OnMultiTypeItemClickListeners<T> mItemClickListener;

    public MultiTypeBaseAdapter(Context context, List<T> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    protected abstract void convert(CommonViewHolder holder, T data, int viewType);

    protected abstract int getItemLayoutId(int viewType);

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (isCommonItemView(viewType)) {
            return CommonViewHolder.create(mContext, getItemLayoutId(viewType), parent);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        if (isCommonItemView(viewType)) {
            bindCommonItem(holder, position, viewType);
        }
    }

    private void bindCommonItem(RecyclerView.ViewHolder holder, final int position, final int viewType) {
        final CommonViewHolder viewHolder = (CommonViewHolder) holder;
        convert(viewHolder, mDatas.get(position), viewType);

        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener != null){
                    mItemClickListener.onItemClick(viewHolder, mDatas.get(position), position, viewType);
                }
            }
        });
    }

    public void setOnMultiTypeItemClickListener(OnMultiTypeItemClickListeners<T> itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
