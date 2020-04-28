package com.wd.tech.view.adapter.consult;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.ConsultParticularsBean;
import com.wd.tech.util.NetUtil;
import com.wd.tech.weight.OnClickItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *@auther:王彦敏
 *@Date: 2020/4/27
 *@Time:10:18
 *@Description:
 * */
public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.InfoViewHolder> {

    private List<ConsultParticularsBean.ResultBean.InformationListBean> informationList;

    public InformationAdapter(List<ConsultParticularsBean.ResultBean.InformationListBean> informationList) {

        this.informationList = informationList;
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_consult_info, parent, false);

        return new InfoViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        ConsultParticularsBean.ResultBean.InformationListBean info= informationList.get(position);
        NetUtil.getInstance().getPhoto(info.getThumbnail(),holder.infoImage);
        holder.infoText.setText(info.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItem.onClickInt(info.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return informationList.size();
    }

    class InfoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.info_image)
        ImageView infoImage;
        @BindView(R.id.info_text)
        TextView infoText;
        public InfoViewHolder(@NonNull View itemView) {
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
