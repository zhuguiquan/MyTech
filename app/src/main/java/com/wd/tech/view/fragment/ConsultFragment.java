package com.wd.tech.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stx.xhb.androidx.XBanner;
import com.stx.xhb.androidx.entity.SimpleBannerInfo;
import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.ConsultListBean;
import com.wd.tech.bean.XBannerBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.activity.DetailActivity;
import com.wd.tech.view.activity.FindPlateActivity;
import com.wd.tech.view.activity.SearchActivity;
import com.wd.tech.view.adapter.RecommendListAdapter;
import com.wd.tech.weight.MyUrls;
import com.wd.tech.weight.OnClickItem;

import java.util.AbstractList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultFragment extends BaseFragment<TechPresenter> {

    @BindView(R.id.img_mokuan)
    SimpleDraweeView imgMokuan;
    @BindView(R.id.beijingeses)
    TextView beijingeses;
    @BindView(R.id.home_sousuo)
    SimpleDraweeView homeSousuo;
    @BindView(R.id.home_xrecyclerview)
    RecyclerView homeXrecyclerview;
    @BindView(R.id.shouye_smart)
    SmartRefreshLayout shouyeSmart;
    @BindView(R.id.consult_tab)
    XBanner consultTab;
    private List<XBannerBean.ResultBean> banner;
    int page = 1;
    private List<ConsultListBean.ResultBean> consultlist;

    @Override
    protected void initView(View view) {

    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_consult;
    }

    @Override
    protected void initData() {
        SharedPreferences sp = getActivity().getSharedPreferences("login.dp", Context.MODE_PRIVATE);
        int uid = sp.getInt("uid", -1);
        String sid = sp.getString("sid", "");
        mPresenter.getNoParams(MyUrls.CONSULT_BANNER, XBannerBean.class);
        HashMap<String, Object> map = new HashMap<>();
        map.put("plateId", 0);
        map.put("page", page);
        map.put("count", 10);
        mPresenter.getDoParams(MyUrls.CONSULT_CONSULTLIST, ConsultListBean.class, map);
        //下拉刷新，上啦加载
        shouyeSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                mPresenter.getNoParams(MyUrls.CONSULT_BANNER, XBannerBean.class);
                mPresenter.getDoParams(MyUrls.CONSULT_CONSULTLIST, ConsultListBean.class, map);
                shouyeSmart.finishRefresh(2000/*,false*/);
            }
        });

        shouyeSmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mPresenter.getNoParams(MyUrls.CONSULT_BANNER, XBannerBean.class);
                mPresenter.getDoParams(MyUrls.CONSULT_CONSULTLIST, ConsultListBean.class, map);
                shouyeSmart.finishLoadMore(2000);
            }
        });

    }

    @Override
    protected void DestroyFragment() {

    }

    @Override
    public void onSuccess(Object o) {

        if (o instanceof XBannerBean) {
            if (((XBannerBean) o).getStatus().equals("0000")) {
                banner = ((XBannerBean) o).getResult();
                consultTab.setBannerData(R.layout.simpledraweeview, new AbstractList<SimpleBannerInfo>() {
                    @Override
                    public SimpleBannerInfo get(int i) {
                        return null;
                    }

                    @Override
                    public int size() {
                        return banner.size();
                    }
                });

                consultTab.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner xbanner, Object model, View view, int position) {
                        String jumpUrl = banner.get(position).getJumpUrl();
                        Log.d("jumpUrl","loadBanner"+jumpUrl);
                        String[] split = jumpUrl.split("=");
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getContext(), DetailActivity.class);
                                Integer integer = Integer.valueOf(split[1]);
                                intent.putExtra("id",integer);
                                getContext().startActivity(intent);
                            }
                        });
                        String imageUrl = banner.get(position).getImageUrl();
                        SimpleDraweeView simpleDraweeView=view.findViewById(R.id.xbanner_simview);
                        DraweeController controller= Fresco.newDraweeControllerBuilder()
                                .setUri(imageUrl)
                                .setAutoPlayAnimations(true)
                                .build();
                        simpleDraweeView.setController(controller);
                        TextView textView = view.findViewById(R.id.tv);
                        textView.setText(banner.get(position).getRank()+"/5");
                    }
                });
                Log.e("xxx", "轮播图" + banner);
            }
        }


        if (o instanceof ConsultListBean) {
            if (((ConsultListBean) o).getStatus().equals("0000")) {
                consultlist = ((ConsultListBean) o).getResult();
                //使用多列表展示
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                homeXrecyclerview.setLayoutManager(linearLayoutManager);
                RecommendListAdapter recommendListAdapter = new RecommendListAdapter(consultlist);
                homeXrecyclerview.setAdapter(recommendListAdapter);
                recommendListAdapter.setOnClickItem(new OnClickItem() {
                    @Override
                    public void onClickInt(int id) {
                        Intent intent = new Intent(getActivity(), DetailActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }

                    @Override
                    public void onClickString(String string) {

                    }
                });

                Log.e("aaa", "资讯列表" + ((ConsultListBean) o).getStatus());
            }
        }


    }

    @Override
    public void onFailure(Throwable e) {


    }

    @OnClick({R.id.img_mokuan, R.id.home_sousuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_mokuan:
                startActivity(new Intent(getContext(), FindPlateActivity.class));
                break;
            case R.id.home_sousuo:
                startActivity(new Intent(getContext(), SearchActivity.class));
                break;
        }
    }


}
