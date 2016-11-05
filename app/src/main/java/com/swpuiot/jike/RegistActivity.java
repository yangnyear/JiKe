package com.swpuiot.jike;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.IOException;

public class RegistActivity extends ActionBarActivity {
    private EditText newname;
    private EditText newpassword;
    private EditText repassword;
    private EditText newage;
    private EditText newmotto;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_regist);
        newname= (EditText) findViewById(R.id.et_name);
        newpassword= (EditText) findViewById(R.id.et_password);
        newage= (EditText) findViewById(R.id.et_age);
        repassword= (EditText) findViewById(R.id.et_repassword);
        newmotto= (EditText) findViewById(R.id.et_motto);
        register= (Button) findViewById(R.id.bt_regist);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String get_name=newname.getText().toString().trim();
                String get_password=newpassword.getText().toString().trim();
                String get_repassword=repassword.getText().toString().trim();
                String get_age=newage.getText().toString().trim();
                String get_motto=newmotto.getText().toString().trim();
                if ("".equals(get_name)||"".equals(get_password)||"".equals(get_repassword)||
                        "".equals(get_age)||"".equals(get_motto)){
                    Toast.makeText(RegistActivity.this, "请补全信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!get_password.equals(get_repassword)){
                    Toast.makeText(RegistActivity.this, "密码不匹配，请重新输入", Toast.LENGTH_SHORT).show();
                    newpassword.setText("");
                    repassword.setText("");
                    return;
                }
                if (!get_age.matches("^\\+?[1-9][0-9]*$")) {
                    Toast.makeText(RegistActivity.this, "您输入的年龄不合法，请重新输入", Toast.LENGTH_SHORT).show();
                    newage.setText("");
                    return;
                }

                RequestParams bildimformation = new RequestParams();
                AsyncHttpClient builtext = new AsyncHttpClient();
                bildimformation.add("name", get_name);
                bildimformation.add("password", get_password);
                bildimformation.add("age",get_repassword);
                bildimformation.add("text", get_motto);
                builtext.post("http://114.215.144.204:9090/register", bildimformation, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        ResponseEntity responseEntity = null;

                        try {
                            responseEntity = objectMapper.readValue(bytes, ResponseEntity.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        int code = responseEntity.getCode();
                        if (code != 1) {
                            Toast.makeText(RegistActivity.this, "z注册失败，请重试", Toast.LENGTH_SHORT).show();
                            newname.setText("");
                            newpassword.setText("");
                            repassword.setText("");
                            newage.setText("");
                            newmotto.setText("");
                            return;
                        }
                        Toast.makeText(RegistActivity.this, "z注册成功，快去登录吧", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(RegistActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Toast.makeText(RegistActivity.this, "网络异常，请检查网络", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }
}
