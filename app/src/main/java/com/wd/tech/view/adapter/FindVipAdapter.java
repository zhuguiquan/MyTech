package com.wd.tech.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.FindVipBean;
import com.wd.tech.weight.OnRecyclerItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *@auther:王彦敏
 *@Date: 2020/4/29
 *@Time:19:04
 *@Description:
 * */
public class FindVipAdapter extends RecyclerView.Adapter<FindVipAdapter.FindViewHolder> {

    private List<FindVipBean.ResultBean> result;

    public FindVipAdapter(List<FindVipBean.ResultBean> result) {

        this.result = result;
    }

    @NonNull
    @Override
    public FindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_find_pay, parent, false);
        return new FindViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FindViewHolder holder, int position) {
        FindVipBean.ResultBean resultBean = result.get(position);
        holder.payItemText.setText(resultBean.getCommodityName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class FindViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pay_item_text)
        TextView payItemText;
        public FindViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    OnRecyclerItemClickListener listener;

    public void setListener(OnRecyclerItemClickListener listener) {
        this.listener = listener;
    }
}
