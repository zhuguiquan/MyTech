package com.wd.tech.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.AddCollectionBean;
import com.wd.tech.bean.CancelCollection;
import com.wd.tech.bean.ConsultCancelBean;
import com.wd.tech.bean.ConsultCommentBean;
import com.wd.tech.bean.ConsultCommentListBean;
import com.wd.tech.bean.ConsultDianzanBean;
import com.wd.tech.bean.ConsultParticularsBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.consult.FindCommentAdapter;
import com.wd.tech.view.adapter.consult.InformationAdapter;
import com.wd.tech.view.adapter.consult.PlateAdapter;
import com.wd.tech.weight.MyUrls;
import com.wd.tech.weight.OnRecyclerItemClickListener;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.details_titie)//资讯标题
            TextView detailsTitie;
    @BindView(R.id.details_time)//资讯时间
            TextView detailsTime;
    @BindView(R.id.details_source)//资讯来源
            TextView detailsSource;
    @BindView(R.id.details_pay_image)
    ImageView detailsPayImage;
    @BindView(R.id.details_pay_bt)
    Button detailsPayBt;
    @BindView(R.id.details_content)
    WebView detailsContent;
    @BindView(R.id.details_plate)
    RecyclerView detailsPlate;
    @BindView(R.id.details_information)
    RecyclerView detailsInformation;
    @BindView(R.id.details_commentlist)
    RecyclerView detailsCommentlist;
    @BindView(R.id.detaild_linear)
    LinearLayout detaildLinear;
    @BindView(R.id.details_scroll)
    ScrollView detailsScroll;
    @BindView(R.id.pay_one)
    TextView payOne;
    @BindView(R.id.pay_two)
    TextView payTwo;
    @BindView(R.id.pay_cancel)
    ImageView payCancel;
    @BindView(R.id.pay_jifen)
    ImageView payJifen;
    @BindView(R.id.pay_huiyuan)
    ImageView payHuiyuan;
    @BindView(R.id.details_pay_relalayout)
    RelativeLayout detailsPayRelalayout;
    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.details_text)
    TextView detailsText;
    @BindView(R.id.details_xiaoxi_image)
    ImageView detailsXiaoxiImage;
    @BindView(R.id.details_xiaoxi_text)
    TextView detailsXiaoxiText;
    @BindView(R.id.details_zan_image)
    ImageView detailsZanImage;
    @BindView(R.id.details_zan_text)
    TextView detailsZanText;
    @BindView(R.id.details_guan_image)
    ImageView detailsGuanImage;
    @BindView(R.id.details_fen_image)
    ImageView detailsFenImage;
    @BindView(R.id.details_fen_text)
    TextView detailsFenText;
    @BindView(R.id.details_lie)
    LinearLayout detailsLie;
    @BindView(R.id.details_edit)
    EditText detailsEdit;
    @BindView(R.id.details_fabiao)
    TextView detailsFabiao;
    @BindView(R.id.details_relat)
    RelativeLayout detailsRelat;
    private int id;
    private ConsultParticularsBean.ResultBean result;//资讯详情

    @Override
    protected void initData() {
        detailsEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s1 = editable.toString();
                if (TextUtils.isEmpty(s1)) {
                    detailsEdit.setHint("既然来了就写点什么吧");
                }
            }
        });


    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra("id", -1);
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", id);
            mPresenter.getDoParams(MyUrls.CONSULT_DETAILS, ConsultParticularsBean.class, map);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("infoId", id);
            hashMap.put("page", 1);
            hashMap.put("count", 5);
            mPresenter.getDoParams(MyUrls.CONSULT_COMMENTLIST, ConsultCommentListBean.class, hashMap);
        }
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof ConsultParticularsBean && TextUtils.equals("0000", ((ConsultParticularsBean) o).getStatus())) {
            result = ((ConsultParticularsBean) o).getResult();
            //标题
            detailsTitie.setText(result.getTitle());
            //日期
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String format = simpleDateFormat1.format(result.getReleaseTime());
            detailsTime.setText(format);
            Toast.makeText(this, ""+result.getReadPower(), Toast.LENGTH_SHORT).show();
            //来源
            detailsSource.setText(result.getSource());
            if (result.getReadPower() == 1) {//判断是否有阅读权限

                String content = result.getContent();
                //主题
                content = content.replaceAll("&", "");
                content = content.replaceAll("“", "\"");
                content = content.replaceAll("<", "<");
                content = content.replaceAll(">", ">");
                content = content.replaceAll("\\n", "<br>");
                content = content.replaceAll("<img", "<img width=\"100%\"");
                detailsContent.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
                //所属板块
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                PlateAdapter plateAdapter = new PlateAdapter(result.getPlate());
                detailsPlate.setLayoutManager(linearLayoutManager);
                detailsPlate.setAdapter(plateAdapter);
                //推荐阅读
                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
                linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);
                InformationAdapter informationAdapter = new InformationAdapter(result.getInformationList());
                detailsInformation.setLayoutManager(linearLayoutManager1);
                detailsInformation.setAdapter(informationAdapter);
                informationAdapter.setListener(new OnRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(String s) {
                        int i = Integer.parseInt(s);
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("id", i);
                        mPresenter.getDoParams(MyUrls.CONSULT_DETAILS, ConsultParticularsBean.class, map);
                    }

                    @Override
                    public void onLongItemClick(String s) {

                    }
                });
            } else {
                detailsPayBt.setVisibility(View.VISIBLE);
                detailsPayImage.setVisibility(View.VISIBLE);
                detailsContent.setVisibility(View.GONE);
                detailsPlate.setVisibility(View.GONE);
                detailsInformation.setVisibility(View.GONE);
            }

            //设置最下方数据
            if (result.getWhetherGreat() == 1) {
                detailsZanImage.setImageResource(R.drawable.zan_hong);
            } else {
                detailsZanImage.setImageResource(R.drawable.zan_hei);
            }
            if (result.getWhetherCollection() == 1) {
                detailsGuanImage.setImageResource(R.drawable.dianzan_hong);
            } else {
                detailsGuanImage.setImageResource(R.drawable.dianzan_hei);
            }
            detailsXiaoxiText.setText(result.getComment() + "");//当前评论人数
            detailsZanText.setText(result.getPraise() + "");//当前点赞人数
        }

        //评论           ConsultCommentBean
        if(o instanceof ConsultCommentListBean && TextUtils.equals("0000",((ConsultCommentListBean) o).getStatus())){
            List<ConsultCommentListBean.ResultBean> result = ((ConsultCommentListBean) o).getResult();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

            FindCommentAdapter findCommentAdapter = new FindCommentAdapter(((ConsultCommentListBean) o).getResult());
            detailsCommentlist.setLayoutManager(linearLayoutManager);
            detailsCommentlist.setAdapter(findCommentAdapter);
            findCommentAdapter.notifyDataSetChanged();
        }

        if (o instanceof ConsultCommentBean) {//评论
            if (TextUtils.equals("0000", ((ConsultCommentBean) o).getStatus())) {
                Toast.makeText(this, "" + ((ConsultCommentBean) o).getMessage(), Toast.LENGTH_SHORT).show();
                //重新请求评论数据
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("infoId", id);
                hashMap.put("page", 1);
                hashMap.put("count", 5);
                mPresenter.getDoParams(MyUrls.CONSULT_COMMENT, ConsultCommentBean.class, hashMap);
            } else {
                Toast.makeText(this, "" + ((ConsultCommentBean) o).getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

        if (o instanceof ConsultDianzanBean) {//点赞
            if (TextUtils.equals("0000", ((ConsultDianzanBean) o).getStatus())) {
                Toast.makeText(this, "" + ((ConsultDianzanBean) o).getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "" + ((ConsultDianzanBean) o).getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        if(o instanceof ConsultCommentBean&&TextUtils.equals("0000",((ConsultCommentBean) o).getStatus())){
            Toast.makeText(this, ""+((ConsultCommentBean) o).getMessage(), Toast.LENGTH_SHORT).show();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("infoId",id);
            hashMap.put("page",1);
            hashMap.put("count",5);
            mPresenter.getDoParams(MyUrls.CONSULT_COMMENTLIST, ConsultCommentListBean.class,hashMap);
        }

    }

    @Override
    public void onFailure(Throwable e) {

    }

    private int zan = 1;
    private int xin = 1;

    @OnClick({R.id.details_back, R.id.details_text, R.id.details_xiaoxi_image, R.id.details_zan_image, R.id.details_guan_image, R.id.details_fen_image, R.id.details_edit, R.id.details_fabiao,R.id.pay_cancel, R.id.pay_jifen, R.id.pay_huiyuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.details_back://返回
                finish();
                break;
            case R.id.details_text:
                detailsLie.setVisibility(View.GONE);
                detailsRelat.setVisibility(View.VISIBLE);
                break;
            case R.id.details_xiaoxi_image://滑动到页面的最下面
                int height = detailsScroll.getMeasuredHeight();
                detailsScroll.scrollTo(0, height - 20);
                break;
            case R.id.details_zan_image://点赞
                if (zan == 1) {
                    detailsZanImage.setImageResource(R.drawable.zan_hong);
                    zan = 2;
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("infoId", id);
                    mPresenter.postDoParams(MyUrls.CONSULT_DIANZAN, ConsultDianzanBean.class, hashMap);
                    result.setWhetherGreat(1);

                } else {
                    detailsZanImage.setImageResource(R.drawable.zan_hei);
                    zan = 1;
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("infoId", id);
                    mPresenter.dltDoParams(MyUrls.CONSULT_CANCEL, ConsultCancelBean.class, hashMap);
                    result.setWhetherGreat(2);
                    Toast.makeText(this, "取消点赞", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.details_guan_image://关注
                if (xin == 1) {
                    detailsGuanImage.setImageResource(R.drawable.dianzan_hong);
                    xin = 2;
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("infoId", id);
                    mPresenter.postDoParams(MyUrls.Base_Collecation, AddCollectionBean.class, hashMap);
                    result.setWhetherCollection(1);
                    Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
                } else {
                    detailsGuanImage.setImageResource(R.drawable.dianzan_hei);
                    xin = 1;
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("infoId", id);
                    mPresenter.dltDoParams(MyUrls.Base_Cancal_Collecation, CancelCollection.class, hashMap);
                    result.setWhetherCollection(2);

                }
                break;
            case R.id.details_fen_image://分享


                break;

            case R.id.details_fabiao:
                String trim = detailsEdit.getText().toString().trim();
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("content", trim);
                hashMap.put("infoId", id);
                mPresenter.postDoParams(MyUrls.CONSULT_COMMENT, ConsultCommentBean.class, hashMap);
                detailsLie.setVisibility(View.VISIBLE);
                detailsRelat.setVisibility(View.GONE);
                Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();

                break;
            case R.id.pay_cancel:
                detailsLie.setVisibility(View.VISIBLE);
                detailsPayRelalayout.setVisibility(View.GONE);
                break;
            case R.id.pay_jifen:
                Intent intent = new Intent(this,PayIntegralActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                break;
            case R.id.pay_huiyuan:
                Intent intent1 = new Intent(this,PayVipActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @OnClick(R.id.details_pay_bt)
    public void onViewClicked() {//点击弹出 付费框
        detailsLie.setVisibility(View.GONE);
        detailsPayRelalayout.setVisibility(View.VISIBLE);
    }

}
