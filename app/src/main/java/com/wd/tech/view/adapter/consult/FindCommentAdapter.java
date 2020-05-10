package com.wd.tech.view.adapter.consult;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.ConsultCommentListBean;
import com.wd.tech.util.NetUtil;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *@auther:王彦敏
 *@Date: 2020/4/27
 *@Time:11:08
 *@Description:
 * */
public class FindCommentAdapter extends RecyclerView.Adapter<FindCommentAdapter.FindViewHolder> {

    private List<ConsultCommentListBean.ResultBean> result;

    public FindCommentAdapter(List<ConsultCommentListBean.ResultBean> result) {

        this.result = result;
    }

    @NonNull
    @Override
    public FindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_consult_commlist, parent, false);

        return new FindViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FindViewHolder holder, int position) {
        ConsultCommentListBean.ResultBean resultBean = result.get(position);
        //头像
        NetUtil.getInstance().getCiclePhoto(resultBean.getHeadPic(),holder.commlistImage);
        //昵称
        holder.commlistTitle.setText(resultBean.getNickName());
        //时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd  HH:mm");
        String format = simpleDateFormat.format(resultBean.getCommentTime());
        holder.commlistTime.setText(format);
        //内容
        holder.commlistContent.setText(resultBean.getContent());
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class FindViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.commlist_image)
        ImageView commlistImage;
        @BindView(R.id.commlist_title)
        TextView commlistTitle;
        @BindView(R.id.commlist_time)
        TextView commlistTime;
        @BindView(R.id.commlist_content)
        TextView commlistContent;
        public FindViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
