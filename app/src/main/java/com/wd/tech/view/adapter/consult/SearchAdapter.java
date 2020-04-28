package com.wd.tech.view.adapter.consult;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.ConsultTitleBean;
import com.wd.tech.weight.OnClickItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *@auther:王彦敏
 *@Date: 2020/4/28
 *@Time:18:39
 *@Description:
 * */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<ConsultTitleBean.ResultBean> result;

    public SearchAdapter(List<ConsultTitleBean.ResultBean> result) {

        this.result = result;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_itema, parent, false);

        return new SearchViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        ConsultTitleBean.ResultBean resultBean = result.get(position);

        holder.tvTitle.setText(resultBean.getTitle());
        holder.tvName.setText(resultBean.getSource());
        Date date = new Date(resultBean.getReleaseTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd  hh:mm");
        holder.tvTime.setText(simpleDateFormat.format(date));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItem.onClickInt(resultBean.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //接口回调
    OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }
}
