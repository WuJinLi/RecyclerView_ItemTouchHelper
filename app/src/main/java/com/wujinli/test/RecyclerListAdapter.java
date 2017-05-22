package com.wujinli.test;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * author: WuJinLi
 * time  : 17/5/21
 * desc  : recyclerview的适配器
 * <p>
 * 实现ItemTouchHelperAdapter里的抽象方法，便于操作数据
 */

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ItemViewHolder>
        implements ItemTouchHelperAdapter {
    /**
     * 变量声明
     */
    private List<String> mItems = new ArrayList<>();
    private LayoutInflater inflater;
    private Activity activity;

    public RecyclerListAdapter(Context context, Activity activity, List<String> mItems) {
        this.mItems = mItems;
        this.activity = activity;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent,
                false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        holder.textView.setText(mItems.get(position));

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    /**
     * 实现数据的重新排序
     *
     * @param fromPosition
     * @param toPosition
     * @return
     */
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mItems, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).refreshData(mItems, "1");
        }
        return true;
    }

    /**
     * 数据删除后，刷新数据
     *
     * @param position
     */
    @Override
    public void onItemDismiss(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).refreshData(mItems, "0");
        }
    }

    public void addData(List<String> _list, Boolean isClear) {
        if (isClear) {
            mItems.clear();
        }
        mItems.addAll(_list);
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public final TextView textView;
        public final ImageView handleView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
            handleView = (ImageView) itemView.findViewById(R.id.handle);
        }

    }
}
