package com.example.xu.mystatustest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xu.mystatustest.R;

/**
 * Created by xu on 2018/7/24.
 */

public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    public TestAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TestViewHolder(View.inflate(context, R.layout.test, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TestViewHolder) holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    public class TestViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_item;

        public TestViewHolder(View itemView) {
            super(itemView);
            tv_item = itemView.findViewById(R.id.tv_item);
        }

        public void setData(int position) {
            tv_item.setText("测试数据" + position);
        }
    }
}
