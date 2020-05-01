package com.lsw.exercise2_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioGroup group1 = (RadioGroup)findViewById(R.id.radioGroup1);
        final RadioGroup group2 = (RadioGroup)findViewById(R.id.radioGroup2);
        final RadioGroup group3 = (RadioGroup)findViewById(R.id.radioGroup3);
        final RadioGroup group4 = (RadioGroup)findViewById(R.id.radioGroup4);
        final RadioGroup group5 = (RadioGroup)findViewById(R.id.radioGroup5);
        Button bt1 = (Button)findViewById(R.id.button1);//提交按钮

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                String text = "";
                int score = 0;

                for (int i = 0; i < group1.getChildCount(); i++) {
                    RadioButton rtn = (RadioButton) group1.getChildAt(i);
                    if (rtn.isChecked()) {
                        if (rtn.getText().equals("15")) {
                            text += "第1题正确";
                            score += 15;
                        } else {
                            text += "第1题错误";
                            score += 0;
                        }
                        break;
                    }
                }

                for (int j=0; j<group2.getChildCount(); j++){
                    RadioButton rtn = (RadioButton) group2.getChildAt(j);
                    if (rtn.isChecked()) {
                        if (rtn.getText().equals("45")) {
                            text += "\n第2题正确";
                            score += 15;
                        } else{
                            text += "\n第2题错误";
                            score += 0;
                        }
                        break;
                    }
                }

                for (int m=0; m<group3.getChildCount(); m++){
                RadioButton rtn = (RadioButton) group3.getChildAt(m);
                if (rtn.isChecked()) {
                    if (rtn.getText().equals("140")) {
                        text += "\n第3题正确";
                        score += 15;
                    } else{
                        text += "\n第3题错误";
                        score += 0;
                }
                break;
            }
        }

                for (int n=0; n<group4.getChildCount(); n++){
                    RadioButton rtn = (RadioButton) group4.getChildAt(n);
                    if (rtn.isChecked()) {
                        if (rtn.getText().equals("x=6, y=2")) {
                            text += "\n第4题正确";
                            score += 25;
                        } else{
                            text += "\n第4题错误";
                            score += 0;
                        }
                        break;
                    }
                }

                for (int k=0; k<group5.getChildCount(); k++){
                    RadioButton rtn = (RadioButton) group5.getChildAt(k);
                    if (rtn.isChecked()) {
                        if (rtn.getText().equals("x=2, y=2.5,\n z=7")) {
                            text += "\n第5题正确";
                            score += 30;
                        } else{
                            text += "\n第5题错误";
                            score += 0;
                        }
                        break;
                    }
                }
                builder.setMessage("你的总分是: " + score + "\n" + text);
                builder.setPositiveButton("确定", null).show();
            }
        });

    }
}
