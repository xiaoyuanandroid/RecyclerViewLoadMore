package com.xiaoyuan.interfaces;


import com.xiaoyuan.viewholder.CommonViewHolder;

/**
 * @author xiaoyuan
 */
public interface OnMultiTypeItemClickListeners<T> {
    void onItemClick(CommonViewHolder viewHolder, T data, int position, int viewType);
}
