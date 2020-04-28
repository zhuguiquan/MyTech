package com.wd.tech.view.adapter.consult;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.ConsultParticularsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *@auther:王彦敏
 *@Date: 2020/4/27
 *@Time:10:02
 *@Description:
 * */
public class PlateAdapter extends RecyclerView.Adapter<PlateAdapter.PlateViewHolder> {

    private List<ConsultParticularsBean.ResultBean.PlateBean> plate;

    public PlateAdapter(List<ConsultParticularsBean.ResultBean.PlateBean> plate) {

        this.plate = plate;
    }

    @NonNull
    @Override
    public PlateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_consult_guang, parent, false);

        return new PlateViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PlateViewHolder holder, int position) {
        ConsultParticularsBean.ResultBean.PlateBean plateBean = plate.get(position);
        holder.guangTv.setText(plateBean.getName());
    }

    @Override
    public int getItemCount() {
        return plate.size();
    }

    class PlateViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.guang_tv)
        TextView guangTv;
        public PlateViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
