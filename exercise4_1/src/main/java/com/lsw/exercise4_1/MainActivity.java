package com.lsw.exercise4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    VerifyActivity verifyView;
    SeekBar seekBar;
    Toast toast;
    int head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button1);//获取选择头像按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HeadActivity.class);
                startActivityForResult(intent, 0x11);//启动intent对应的Activity
            }
        });

        verifyView=(VerifyActivity)findViewById(R.id.verifyView);
        verifyView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sea));
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                verifyView.setMove(progress*0.01);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:
                        boolean isTrue=verifyView.isTrue(0.01);     //允许有2%的误差
                        if (isTrue){
                            showToast("验证成功");
                            TextView veriText=(TextView)findViewById(R.id.verifyText);
                            veriText.setText("验证成功");
                        } else {
                            reInit();
                            TextView veriText=(TextView)findViewById(R.id.verifyText);
                            veriText.setText("");
                        }
                        break;
                }
                return false;
            }
        });

        Button submit=(Button)findViewById(R.id.submit);        //获取提交按钮
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=((EditText)findViewById(R.id.user)).getText().toString();
                String pwd=((EditText)findViewById(R.id.pwd)).getText().toString();
                String repwd=((EditText)findViewById(R.id.repwd)).getText().toString();
                String email=((EditText)findViewById(R.id.email)).getText().toString();
                String verify=((TextView)findViewById(R.id.verifyText)).getText().toString();

                if (!"".equals(user) && !"".equals(pwd) && !"".equals(email) && !"".equals(verify)){
                    if (!pwd.equals(repwd)){
                        Toast.makeText(MainActivity.this,
                                "两次输入的密码不一致，请重新输入！", Toast.LENGTH_LONG).show();
                        ((EditText)findViewById(R.id.pwd)).setText("");
                        ((EditText)findViewById(R.id.repwd)).setText("");
                        ((EditText)findViewById(R.id.pwd)).requestFocus();
                    } else {
                        Intent intent=new Intent(MainActivity.this, RegisterActivity.class);
                        Bundle bundle=new Bundle();

                        bundle.putInt("headId", getImageId());      //保存头像
                        bundle.putCharSequence("user", user);       //保存用户名
                        bundle.putCharSequence("pwd", pwd);         //保存密码
                        bundle.putCharSequence("repwd", repwd);
                        bundle.putCharSequence("email", email);     //保存邮箱

                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                    } else {
                    Toast.makeText(MainActivity.this, "请将注册信息输入完整",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void showToast(String msg) {
        toast=Toast.makeText(this, msg,Toast.LENGTH_SHORT);
        toast.show();
    }

    public void reInit() {
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        seekBar.setProgress(0);
        verifyView.setReDraw();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0x11 && resultCode==0x11){
            Bundle bundle = data.getExtras();//获取传递的数据包
            int imageId = bundle.getInt("imageId");//获取选择的头像的ID
            ImageView iv = (ImageView)findViewById(R.id.imageView);
            iv.setImageResource(imageId);
            setImageId(imageId);
        }
    }

    public void setImageId(int id){
        this.head=id;
    }

    public int getImageId(){
        return this.head;
    }
}
