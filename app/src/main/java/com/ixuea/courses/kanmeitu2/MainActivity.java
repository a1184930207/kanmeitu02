package com.ixuea.courses.kanmeitu2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ixuea.courses.kanmeitu2.Activity.BaseActivity;
import com.ixuea.courses.kanmeitu2.Activity.LoginActivity;
import com.ixuea.courses.kanmeitu2.adapter.ImageAdapter;
import com.ixuea.courses.kanmeitu2.api.Api;
import com.ixuea.courses.kanmeitu2.domain.Image;
import com.ixuea.courses.kanmeitu2.domain.response.ListResponse;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {


    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true);

//显示2列，我们这里实现的是每个图片的宽高都是一样
//最好的效果其实是根据图片高度和宽度来缩放
//因为每一张图片大小不一样
//但这样实现涉及到的知识点很多
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(layoutManager);

        //设置数据
        ArrayList<Image> datas = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            //图片来自http://image.baidu.com
            datas.add(new Image(String.format("http://dev-courses-quick.oss-cn-beijing.aliyuncs.com/%d.jpg", i)));
        }
        adapter = new ImageAdapter(this);
        rv.setAdapter(adapter);
        adapter.setDatas(datas);
        fetchData();

    }

    private void fetchData() {
        //这里涉及到很多知识，可以够讲几门课程了
        //在这里大家只需简单理解，这样写就能请求到数据就行了
        Api
                .getInstance()
                .images()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListResponse<Image>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListResponse<Image> imageListResponse) {
                        //当数据请求完毕后，他会解析成对象，并返回给我们
                        //真实项目中还会进行一系列的错误处理
                        adapter.setDatas(imageListResponse.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        //真实项目中会将错误，提示给用户
                        //同时写到日志
                        e.printStackTrace();

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 退出
     *
     * @param view
     */
    public void onLogoutClick(View view) {
        sp.setLogin(false);

        //退出后，跳转到登录界面
        //当然大家可以根据业务逻辑调整
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        finish();
    }

}
