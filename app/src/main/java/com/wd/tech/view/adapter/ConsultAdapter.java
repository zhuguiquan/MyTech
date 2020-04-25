package com.wd.tech.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.androidx.XBanner;
import com.stx.xhb.androidx.entity.SimpleBannerInfo;
import com.wd.tech.R;
import com.wd.tech.bean.ConsultAdverBean;
import com.wd.tech.bean.ConsultListBean;
import com.wd.tech.bean.XBannerBean;
import com.wd.tech.view.activity.DetailActivity;
import com.wd.tech.weight.OnClickItem;

import java.util.AbstractList;
import java.util.List;

/*
 *@auther:王彦敏
 *@Date: 2020/4/24
 *@Time:0:59
 *@Description:
 * */
public class ConsultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<XBannerBean.ResultBean> banner;
    private List<ConsultListBean.ResultBean> consultlist;
    private Context context;

    public ConsultAdapter(List<XBannerBean.ResultBean> banner,  List<ConsultListBean.ResultBean> consultlist, Context context) {
        this.banner = banner;
        this.consultlist = consultlist;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.consult_xbanner, parent, false);
            return new homeOneViewHolder(inflate);
        } else if (viewType == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.consult_list_adver, parent, false);
            return new homeTwoViewHolder(inflate);
        }

        return null;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType){
            case 0:
                if (holder instanceof homeOneViewHolder){
                    if (banner!=null){
                        ((homeOneViewHolder) holder).tab.setBannerData(R.layout.simpledraweeview, new AbstractList<SimpleBannerInfo>() {
                            @Override
                            public SimpleBannerInfo get(int i) {
                                return null;
                            }

                            @Override
                            public int size() {
                                return banner.size();
                            }
                        });

                        ((homeOneViewHolder) holder).tab.loadImage(new XBanner.XBannerAdapter() {
                            @Override
                            public void loadBanner(XBanner xBanner, Object model, View view, int position) {
                                String jumpUrl = banner.get(position).getImageUrl();
                                Log.d("jumpUrl","loadBanner"+jumpUrl);
                                String[] split = jumpUrl.split("=");
                                view.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(context, DetailActivity.class);
                                        Integer integer = Integer.valueOf(split[1]);
                                        intent.putExtra("id",integer);
                                        context.startActivity(intent);
                                    }
                                });
                                String imageUrl = banner.get(position).getImageUrl();
                                SimpleDraweeView simpleDraweeView = view.findViewById(R.id.xbanner_simview);
                                DraweeController controller= Fresco.newDraweeControllerBuilder()
                                        .setUri(imageUrl)
                                        .setAutoPlayAnimations(true)
                                        .build();
                                simpleDraweeView.setController(controller);
                                TextView textView = view.findViewById(R.id.tv);
                                textView.setText(banner.get(position).getRank()+"/5");
                            }
                        });

                    }

                }

                break;
            case 1:
                if (consultlist!=null){
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
                    linearLayoutManager.setOrientation(RecyclerView.VISIBLE);
                    ((homeTwoViewHolder)holder).twocy.setLayoutManager(linearLayoutManager);
                    RecommendListAdapter listadapter = new RecommendListAdapter(consultlist);
                    ((homeTwoViewHolder) holder).twocy.setAdapter(listadapter);
                    listadapter.setOnClickItem(new OnClickItem() {
                        @Override
                        public void onClickInt(int id) {
                            Intent intent = new Intent(context,DetailActivity.class);
                            intent.putExtra("id",id);
                            context.startActivity(intent);
                        }

                        @Override
                        public void onClickString(String string) {

                        }
                    });

                }

                break;
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (position == 1) {
            return 1;
        }

        return 0;
    }

    class homeOneViewHolder extends RecyclerView.ViewHolder {
        XBanner tab;
        public homeOneViewHolder(@NonNull View itemView) {

            super(itemView);
            tab = itemView.findViewById(R.id.xbanner);
        }
    }

    class homeTwoViewHolder extends RecyclerView.ViewHolder {
        RecyclerView twocy;
        public homeTwoViewHolder(@NonNull View itemView) {
            super(itemView);
            twocy= itemView.findViewById(R.id.rlv1);
        }
    }



}
