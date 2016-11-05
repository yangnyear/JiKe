package com.swpuiot.jike;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends Activity {
    private View to_logoin;
    private View logoin;
    private TextView username;
    private ImageView image_userlogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        to_logoin =  findViewById(R.id.ll_login);
        username= (TextView) findViewById(R.id.text_username);
        image_userlogo= (ImageView) findViewById(R.id.image_mylogo);

        ListView listView = (ListView) findViewById(R.id.my_list);
        listView.setAdapter(new MyAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
        to_logoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!username.getText().toString().equals("点击登录账号")) {
                    // 到個人中心
                    Toast.makeText(MainActivity.this, "您已經登錄，無需再次登錄", Toast.LENGTH_SHORT).show();
                    return;
                }


                //跳转到登录窗口

                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                View view = inflater.inflate(R.layout.logoin_item, null);


                final AlertDialog dialog = new AlertDialog
                        .Builder(MainActivity.this)
                        .setTitle("登录")
                        .setView(view)
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
                logoin = view.findViewById(R.id.logoin_new);
                logoin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //登录界面
                        dialog.dismiss();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

            }
        });
        if(getIntent().hasExtra("DataBean_data")) {
            ResponseEntity.DataBean user = (ResponseEntity.DataBean) getIntent().getSerializableExtra("DataBean_data");
            username.setText(user.getName());
            com.facebook.drawee.view.SimpleDraweeView sdv = (SimpleDraweeView) findViewById(R.id.image_mylogo);
            Uri uri = Uri.parse("http://114.215.144.204:9090/avatar/"+user.getName());
            sdv.setImageURI(uri);
        }

    }
}
