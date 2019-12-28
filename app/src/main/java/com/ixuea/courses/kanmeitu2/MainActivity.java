package com.ixuea.courses.kanmeitu2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ixuea.courses.kanmeitu2.Activity.BaseActivity;
import com.ixuea.courses.kanmeitu2.Activity.LoginActivity;
import com.ixuea.courses.kanmeitu2.adapter.ImageAdapter;
import com.ixuea.courses.kanmeitu2.domain.Image;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends BaseActivity {



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
        ImageAdapter adapter = new ImageAdapter(this);
        rv.setAdapter(adapter);
        adapter.setDatas(datas);

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
