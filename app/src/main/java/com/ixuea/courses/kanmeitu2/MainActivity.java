package com.ixuea.courses.kanmeitu2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ixuea.courses.kanmeitu2.Activity.BaseActivity;
import com.ixuea.courses.kanmeitu2.Activity.LoginActivity;

public class MainActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
