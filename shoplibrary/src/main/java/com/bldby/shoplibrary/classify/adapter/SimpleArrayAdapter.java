package com.bldby.shoplibrary.classify.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.List;

public class SimpleArrayAdapter<T>  extends RecyclerView.Adapter<SimpleArrayAdapter.ViewHolder>implements SectionIndexer {
    private final Context mContext;
    private final @LayoutRes
    int mResource;
    private List<T> mObjects;
    private SectionIndexer mRealSectionIndexer; // 代理

    public SimpleArrayAdapter(@NonNull Context context, @NonNull List<T> objects, SectionIndexer realSectionIndexer) {
        mContext = context;
        mResource = android.R.layout.simple_list_item_1;
        mObjects = objects;
        mRealSectionIndexer = realSectionIndexer;
    }
    @NonNull
    @Override
    public SimpleArrayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(View.inflate(mContext, mResource, null));
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleArrayAdapter.ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(mObjects.get(i).toString());
        viewHolder.textView.setOnClickListener(view -> new Object());
    }

    @Override
    public int getItemCount() {
        return mObjects==null? 0: mObjects.size();
    }

    @Override
    public Object[] getSections() {
        return (Integer[]) mRealSectionIndexer.getSections();
    }

    @Override
    public int getPositionForSection(int i) {
        return mRealSectionIndexer.getPositionForSection(i);
    }

    @Override
    public int getSectionForPosition(int i) {
        return  mRealSectionIndexer.getSectionForPosition(i);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
        }
    }
}
