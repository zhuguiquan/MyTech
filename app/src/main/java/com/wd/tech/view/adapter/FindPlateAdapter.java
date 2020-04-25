package com.wd.tech.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.ConsultModuleBean;
import com.wd.tech.util.NetUtil;
import com.wd.tech.weight.OnClickItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *@auther:王彦敏
 *@Date: 2020/4/25
 *@Time:14:08
 *@Description:
 * */
public class FindPlateAdapter extends RecyclerView.Adapter<FindPlateAdapter.FindViewHolder> {

    private List<ConsultModuleBean.ResultBean> list;

    public FindPlateAdapter(List<ConsultModuleBean.ResultBean> list) {

        this.list = list;
    }

    @NonNull
    @Override
    public FindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_find_plate, parent, false);

        return new FindViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FindViewHolder holder, int position) {
        ConsultModuleBean.ResultBean resultBean = list.get(position);
        NetUtil.getInstance().getPhoto(resultBean.getPic(),holder.findPlateImage);
        holder.findPlateName.setText(resultBean.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItem.onClickInt(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class FindViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.find_plate_image)
        ImageView findPlateImage;
        @BindView(R.id.find_plate_name)
        TextView findPlateName;

        public FindViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    //自定义接口回调
    OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }


}
