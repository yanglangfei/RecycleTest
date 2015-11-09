package com.ylf.jucaipen.recycletest.com.ylf.jucaipen.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ylf.jucaipen.recycletest.R;

/**
 * Created by Administrator on 2015/11/9.
 */
public class TestActivity extends Activity{


    @ViewInject(R.id.inputlay)
    private TextInputLayout inputlay;

    @ViewInject(R.id.into)
    private Button into;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_test);
        ViewUtils.inject(this);
        initView();
    }

    private void initView() {
        inputlay.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (inputlay.getEditText().getText().toString().length() < 6) {
                    inputlay.setErrorEnabled(true);//是否设置错误提示信息
                    inputlay.setError("密码长度不足");//设置错误提示信息
                } else {
                    inputlay.setErrorEnabled(false);//不设置错误提示信息
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        into.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent into=new Intent();
                into.setClass(TestActivity.this,ListsActivity.class);
                TestActivity.this.startActivity(into);
            }
        });
    }
}
