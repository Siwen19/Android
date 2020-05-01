package com.lsw.exercise2_3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CheckBox chb1 = (CheckBox)findViewById(R.id.answer1_1);
        final CheckBox chb2 = (CheckBox)findViewById(R.id.answer1_2);
        final CheckBox chb3 = (CheckBox)findViewById(R.id.answer1_3);
        final CheckBox chb4 = (CheckBox)findViewById(R.id.answer1_4);

        final CheckBox chb5 = (CheckBox)findViewById(R.id.answer2_1);
        final CheckBox chb6 = (CheckBox)findViewById(R.id.answer2_2);
        final CheckBox chb7 = (CheckBox)findViewById(R.id.answer2_3);
        final CheckBox chb8 = (CheckBox)findViewById(R.id.answer2_4);

        final CheckBox chb9 = (CheckBox)findViewById(R.id.answer3_1);
        final CheckBox chb10 = (CheckBox)findViewById(R.id.answer3_2);
        final CheckBox chb11 = (CheckBox)findViewById(R.id.answer3_3);
        final CheckBox chb12 = (CheckBox)findViewById(R.id.answer3_4);

        final CheckBox chb13 = (CheckBox)findViewById(R.id.answer4_1);
        final CheckBox chb14 = (CheckBox)findViewById(R.id.answer4_2);
        final CheckBox chb15 = (CheckBox)findViewById(R.id.answer4_3);
        final CheckBox chb16 = (CheckBox)findViewById(R.id.answer4_4);

        Button btn1 = (Button)findViewById(R.id.button1);
        Button btn2 = (Button)findViewById(R.id.button2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                int score1 = 0;
                int score2 = 0;
                int score3 = 0;
                int score4 = 0;
                int totalScore = 0;
                String rAns = "正确答案是: \n1.";
                rAns += chb1.getText().toString() + "; ";
                rAns += chb2.getText().toString() + "; \n2.";
                rAns += chb5.getText().toString() + "; ";
                rAns += chb8.getText().toString() + "; \n3.";
                rAns += chb10.getText().toString() + ";";
                rAns += chb11.getText().toString() + "; \n4.";
                rAns += chb13.getText().toString() + "; ";
                rAns += chb14.getText().toString() + "; ";
                rAns += chb15.getText().toString() + "; ";
                rAns += chb16.getText().toString() + "; ";

                if (chb3.isChecked() || chb4.isChecked()){
                    score1 = 0;
                } else if (chb1.isChecked() && chb2.isChecked()){
                    score1 = 20;
                } else {
                    score1 = 10;
                }

                if (chb6.isChecked() || chb7.isChecked()) {
                    score2 = 0;
                } else if (chb5.isChecked() && chb8.isChecked()){
                    score2 = 20;
                } else {
                    score2 = 10;
                }

                if (chb9.isChecked() || chb12.isChecked()){
                    score3 = 0;
                } else if (chb10.isChecked() && chb11.isChecked()){
                    score3 = 20;
                } else {
                    score3 = 10;
                }

                if (chb13.isChecked() && chb14.isChecked() && chb15.isChecked()
                && chb16.isChecked()){
                    score4 = 40;
                } else {
                    score4 = 0;
                }

                totalScore = score1 + score2 + score3 + score4;
                builder.setMessage("你的得分是: " + totalScore + "\n正确答案是: " + rAns);
                builder.setPositiveButton("确定", null).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chb1.setChecked(false);
                chb2.setChecked(false);
                chb3.setChecked(false);
                chb4.setChecked(false);

                chb5.setChecked(false);
                chb6.setChecked(false);
                chb7.setChecked(false);
                chb8.setChecked(false);

                chb9.setChecked(false);
                chb10.setChecked(false);
                chb11.setChecked(false);
                chb12.setChecked(false);

                chb13.setChecked(false);
                chb14.setChecked(false);
                chb15.setChecked(false);
                chb16.setChecked(false);
            }
        });




    }
}
