package com.lsw.exercise3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int[] imageIds = new int[] {R.drawable.shoe_ok, R.drawable.shoe_sorry,
            R.drawable.shoe_sorry};

    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image1 = (ImageView)findViewById(R.id.imageView1);
        image2 = (ImageView)findViewById(R.id.imageView2);
        image3 = (ImageView)findViewById(R.id.imageView3);
        result = (TextView)findViewById(R.id.textView1);
        reset();

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRight(v, 0);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRight(v, 1);
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRight(v, 2);
            }
        });

        Button button = (Button)findViewById(R.id.button1);//获取'再玩一次'按钮

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                result.setText(R.string.title);//将标题恢复为默认值
                image1.setAlpha(255);
                image2.setAlpha(255);
                image3.setAlpha(255);
                image1.setImageDrawable(getResources().getDrawable(R.drawable.shoe_default));
                image2.setImageDrawable(getResources().getDrawable(R.drawable.shoe_default));
                image3.setImageDrawable(getResources().getDrawable(R.drawable.shoe_default));
            }
        });
    }

    //随机指定鸡蛋所在的鞋子
    private void reset() {
        for (int i=0; i<3; i++){
            int temp = imageIds[i];
            int index = (int)(Math.random()*2);
            imageIds[i] = imageIds[index];
            imageIds[index] = temp;
        }
    }

    private void isRight(View v, int index) {
        //随机匹配一个图片资源从数组中
        image1.setImageDrawable(getResources().getDrawable(imageIds[0]));
        image2.setImageDrawable(getResources().getDrawable(imageIds[1]));
        image3.setImageDrawable(getResources().getDrawable(imageIds[2]));
        //设置每张图片为半透明效果
        image1.setAlpha(100);
        image2.setAlpha(100);
        image3.setAlpha(100);

        ImageView v1 = (ImageView)v;//获取被单击的图片视图
        v1.setAlpha(255);//设置图像视图的透明度

        if (imageIds[index] == R.drawable.shoe_ok){
            result.setText("恭喜你，猜对了，祝你幸福！");
        } else {
            result.setText("很抱歉，猜错了，要不要再试一次?");
        }
    }
}
