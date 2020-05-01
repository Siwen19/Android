package com.lsw.midexam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private DialogInterface.OnClickListener click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Button btn = (Button)findViewById(R.id.register);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("本次考试只有两分钟，两分钟后自动交卷，请把握好时间，祝考试顺利！");
                builder.setPositiveButton("确定", click).show();
            }
        });

        click=new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(MainActivity.this, RegisterActivity.class);
                String name=((EditText)findViewById(R.id.name)).getText().toString();
                Bundle bundle=new Bundle();
                bundle.putCharSequence("name", name);
                intent.putExtras(bundle);
                startActivity(intent);
                }
            };
        }
    }
