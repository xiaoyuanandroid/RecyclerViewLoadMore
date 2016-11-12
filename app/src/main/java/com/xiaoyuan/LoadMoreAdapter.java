package com.xiaoyuan;

import android.content.Context;

import com.xiaoyuan.base.MultiTypeBaseAdapter;
import com.xiaoyuan.viewholder.CommonViewHolder;

import java.util.List;

/**
 * @author xiaoyuan
 */
public class LoadMoreAdapter extends MultiTypeBaseAdapter<String> {

    public LoadMoreAdapter(Context context, List<String> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(CommonViewHolder holder, final String data, int viewType) {
        if (viewType == 0) {

        } else {
            holder.setText(R.id.text1, data);
        }
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        if (viewType == 0) {
            return R.layout.item_head;
        }
        return R.layout.item_layout1;
    }

    @Override
    protected int getViewType(int position, String data) {

        if (data == null) {
            return 0;
        } else {
            return 1;
        }


    }

}
