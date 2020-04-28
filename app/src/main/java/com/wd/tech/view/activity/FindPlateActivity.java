package com.wd.tech.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BaseHuaActivity;
import com.wd.tech.bean.ConsultModuleBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.FindPlateAdapter;
import com.wd.tech.weight.MyUrls;
import com.wd.tech.weight.OnClickItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindPlateActivity extends BaseHuaActivity<TechPresenter> {

    @BindView(R.id.plate_recycler)
    RecyclerView plateRecycler;

    @Override
    protected void initData() {
        getSupportActionBar().hide();
        mPresenter.getNoParams(MyUrls.CONSULT_MODULE, ConsultModuleBean.class);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_find_plate;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof ConsultModuleBean && TextUtils.equals("0000", ((ConsultModuleBean) o).getStatus())) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            FindPlateAdapter findPlateAdapter = new FindPlateAdapter(((ConsultModuleBean) o).getResult());
            plateRecycler.setLayoutManager(gridLayoutManager);
            plateRecycler.setAdapter(findPlateAdapter);
            findPlateAdapter.setOnClickItem(new OnClickItem() {
                @Override
                public void onClickInt(int id) {
                    Intent intent = new Intent(FindPlateActivity.this,ChannelActivity.class);
                    intent.putExtra("name",((ConsultModuleBean) o).getResult().get(id).getName());
                    intent.putExtra("id",((ConsultModuleBean) o).getResult().get(id).getId());
                    startActivity(intent);
                }

                @Override
                public void onClickString(String string) {

                }
            });
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }


}
