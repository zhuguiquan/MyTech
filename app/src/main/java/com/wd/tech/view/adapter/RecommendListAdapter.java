package com.wd.tech.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.ConsultListBean;
import com.wd.tech.util.NetUtil;
import com.wd.tech.weight.OnClickItem;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *@auther:王彦敏
 *@Date: 2020/4/25
 *@Time:0:26
 *@Description:
 * */
public class RecommendListAdapter extends RecyclerView.Adapter<RecommendListAdapter.RecommendViewHolder> {

    private List<ConsultListBean.ResultBean> consultlist;
    private int xin=1;
    public RecommendListAdapter( List<ConsultListBean.ResultBean> consultlist) {

        this.consultlist = consultlist;
    }

    @NonNull
    @Override
    public RecommendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.consult_list, parent, false);

        return new RecommendViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendViewHolder holder, int position) {
        ConsultListBean.ResultBean resultBean = consultlist.get(position);
        if (resultBean.getWhetherAdvertising()==1){
            holder.itemRela1.setVisibility(View.VISIBLE);
            holder.itemRela2.setVisibility(View.GONE);
            ConsultListBean.ResultBean.InfoAdvertisingVoBean infoAdvertisingVo = resultBean.getInfoAdvertisingVo();
            NetUtil.getInstance().getPhoto(infoAdvertisingVo.getPic(),holder.consultItemImage);
        }else {
            holder.itemRela1.setVisibility(View.GONE);
            holder.itemRela2.setVisibility(View.VISIBLE);
            NetUtil.getInstance().getPhoto(resultBean.getThumbnail(),holder.itemImage);
            holder.itemTitle.setText(resultBean.getTitle());
            String substring = resultBean.getSummary().substring(0, 20);
            holder.itemSummary.setText(substring+"...");
            holder.itemSource.setText(resultBean.getSource());
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("M月dd日");
            String format = simpleDateFormat1.format(resultBean.getReleaseTime());
            holder.itemTime.setText(format);

            holder.itemCollection.setText(resultBean.getCollection()+"");
            holder.itemShare.setText(resultBean.getShare()+"");


        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (resultBean.getWhetherAdvertising()==1){
                    ConsultListBean.ResultBean.InfoAdvertisingVoBean infoAdvertisingVo = resultBean.getInfoAdvertisingVo();
                    int id = infoAdvertisingVo.getId();
                    onClickItem.onClickInt(id);
                }else {
                    onClickItem.onClickInt(resultBean.getId());
                }
            }
        });
        holder.itemCollectionImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (xin==1){
                    holder.itemCollectionImage.setImageResource(R.drawable.dianzan_hong);
                    xin=2;
                    resultBean.setWhetherCollection(1);
                    Toast.makeText(holder.itemView.getContext(), "收藏成功", Toast.LENGTH_SHORT).show();
                }else {
                    holder.itemCollectionImage.setImageResource(R.drawable.dianzan_hei);
                    xin=1;
                    resultBean.setWhetherCollection(2);
                    Toast.makeText(holder.itemView.getContext(), "取消收藏", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return consultlist.size();
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_image)
        ImageView itemImage;
        @BindView(R.id.item_title)
        TextView itemTitle;
        @BindView(R.id.item_summary)
        TextView itemSummary;
        @BindView(R.id.item_source)
        TextView itemSource;
        @BindView(R.id.item_time)
        TextView itemTime;
        @BindView(R.id.item_collection_image)
        ImageView itemCollectionImage;
        @BindView(R.id.item_collection)
        TextView itemCollection;
        @BindView(R.id.item_share_image)
        ImageView itemShareImage;
        @BindView(R.id.item_share)
        TextView itemShare;
        @BindView(R.id.item_rela2)
        RelativeLayout itemRela2;
        @BindView(R.id.consult_item_image)
        ImageView consultItemImage;
        @BindView(R.id.item_rela1)
        RelativeLayout itemRela1;
        @BindView(R.id.consult_list_click)
        LinearLayout consultListClick;
        public RecommendViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

}
