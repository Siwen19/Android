package com.lsw.midexam;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class RegisterActivity extends Activity {
    private DialogInterface.OnClickListener click1;
    private MediaPlayer mediaPlayer;
    private SeekBar mySeekBar;
    private Chronometer ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer=MediaPlayer.create(this, R.raw.source);
        mySeekBar=(SeekBar)findViewById(R.id.seekBar);
        mySeekBar.setEnabled(false);
        mySeekBar.setMax(mediaPlayer.getDuration());
        mediaPlayer.start();
        new myThread().start();

        mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(mySeekBar.getProgress());
            }
        });

        Button submit=(Button)findViewById(R.id.submit);
        final RadioGroup group1=(RadioGroup)findViewById(R.id.radioGroup1);
        final RadioGroup group2=(RadioGroup)findViewById(R.id.radioGroup2);
        final RadioGroup group3=(RadioGroup)findViewById(R.id.radioGroup3);

        ch=(Chronometer)findViewById(R.id.ch);
        ch.setBase(SystemClock.elapsedRealtime());
        ch.setFormat("%s");
        ch.start();

        // 计时2分钟，2分钟后自动交卷
        ch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (SystemClock.elapsedRealtime()-ch.getBase() >= 120000){
                    ch.stop();
                    AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                    String text = "";
                    int score = 0;

                    for (int i = 0; i < group1.getChildCount(); i++) {
                        RadioButton rtn = (RadioButton) group1.getChildAt(i);
                        if (rtn.isChecked()) {
                            if (rtn.getText().equals("A、Chess club")) {
                                text += "第1题正确";
                                score += 30;
                            } else {
                                text += "第1题错误";
                                score += 0;
                            }
                            break;
                        }
                    }

                    for (int j=0; j < group2.getChildCount(); j++){
                        RadioButton rtn = (RadioButton) group2.getChildAt(j);
                        if (rtn.isChecked()) {
                            if (rtn.getText().equals("D、Music club")) {
                                text += "\n第2题正确";
                                score += 40;
                            } else{
                                text += "\n第2题错误";
                                score += 0;
                            }
                            break;
                        }
                    }

                    for (int j=0; j < group3.getChildCount(); j++){
                        RadioButton rtn = (RadioButton) group3.getChildAt(j);
                        if (rtn.isChecked()) {
                            if (rtn.getText().equals("A、No, he can’t.")) {
                                text += "\n第3题正确";
                                score += 30;
                            } else{
                                text += "\n第3题错误";
                                score += 0;
                            }
                            break;
                        }
                    }

                    builder.setMessage("本次考试，你的总分是: " + score + "\n" + text + "\n" + "正确答案是A、D、A");
                    builder.setPositiveButton("确定", click1).show();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                ch.stop();
                AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                String text = "";
                int score = 0;
                // 第一题
                for (int i = 0; i < group1.getChildCount(); i++) {
                    RadioButton rtn = (RadioButton) group1.getChildAt(i);
                    if (rtn.isChecked()) {
                        if (rtn.getText().equals("A、Chess club")) {
                            text += "第1题正确";
                            score += 30;
                        } else {
                            text += "第1题错误";
                            score += 0;
                        }
                        break;
                    }
                }
                // 第二题
                for (int j=0; j < group2.getChildCount(); j++){
                    RadioButton rtn = (RadioButton) group2.getChildAt(j);
                    if (rtn.isChecked()) {
                        if (rtn.getText().equals("D、Music club")) {
                            text += "\n第2题正确";
                            score += 40;
                        } else{
                            text += "\n第2题错误";
                            score += 0;
                        }
                        break;
                    }
                }
                // 第三题
                for (int j=0; j < group3.getChildCount(); j++){
                    RadioButton rtn = (RadioButton) group3.getChildAt(j);
                    if (rtn.isChecked()) {
                        if (rtn.getText().equals("A、No, he can’t.")) {
                            text += "\n第3题正确";
                            score += 30;
                        } else{
                            text += "\n第3题错误";
                            score += 0;
                        }
                        break;
                    }
                }
                Intent intent=getIntent();
                Bundle bundle=intent.getExtras();
                builder.setMessage(bundle.getString("name") + "同学，本次考试你的总分是: "
                        + score + "分" +  "\n" + text + "\n" + "正确答案是A、D、A");
                builder.setPositiveButton("确定", click1).show();
            }
        });
        // 确定后跳出程序
        click1=new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        };
    }

    class myThread extends Thread {
        public void run() {
            super.run();
            while (mySeekBar.getProgress() <= mySeekBar.getMax()) {
                mySeekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }
    }
}
