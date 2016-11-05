package com.swpuiot.jike;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.IOException;

public class LoginActivity extends ActionBarActivity {
    private EditText name;
    private EditText password;
    private CheckBox load;
    private TextView newuser;
    private Button login;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_logoin);
        name= (EditText) findViewById(R.id.in_name);
        password= (EditText) findViewById(R.id.in_password);
        login= (Button) findViewById(R.id.bt_login);
        load= (CheckBox) findViewById(R.id.box_remenber);
        newuser= (TextView) findViewById(R.id.text_build);

        pref = getSharedPreferences("setting", MODE_PRIVATE);
        boolean isremenbered=pref.getBoolean("remember_password", false);

        //记住密码
        if (isremenbered){
            String load_name=pref.getString("name","");
            String load_password=pref.getString("password","");
            name.setText(load_name);
            password.setText(load_password);
            load.setChecked(true);
        }
        //登录
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String get_name = name.getText().toString();
                String get_password = password.getText().toString();
                RequestParams logimformation = new RequestParams();
                AsyncHttpClient test = new AsyncHttpClient();
                editor = pref.edit();
                //首先检查是否记住了密码
                if (load.isChecked()) {
                    editor.putBoolean("remember_password", true);
                    editor.putString("name", get_name);
                    editor.putString("password", get_password);
                } else {
                    editor.clear();
                }
                editor.commit();
                logimformation.add("name", get_name);
                logimformation.add("password", get_password);

                RequestHandle post = test.post("http://114.215.144.204:9090/login", logimformation, new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        ResponseEntity responseEntity = null;

                        try {
                            responseEntity = objectMapper.readValue(bytes, ResponseEntity.class);
                        } catch (IOException e) {
//                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "未知錯誤，請重試", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        int code = responseEntity.getCode();
                        if (code != 1) {
                            Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        //使用intend传递对象并跳转
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        ResponseEntity.DataBean user=responseEntity.getData();
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("DataBean_data", user);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Toast.makeText(LoginActivity  .this, "网络异常，请检查网络", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });
        //注册
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //舔砖并解析json
                Intent intent=new Intent(LoginActivity.this,RegistActivity.class);
                startActivity(intent);
//                finish();
        }
        });

    }
}
