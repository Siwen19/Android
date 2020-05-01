package com.lsw.exercise4_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class HeadActivity extends Activity {
    //定义并初始化保存头像id的数组
    public int[] imageId = new int[]{R.drawable.boy, R.drawable.dog,
            R.drawable.frog, R.drawable.girl, R.drawable.peg, R.drawable.rab};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.head);
        //获取GridView组件
        GridView gridView = (GridView)findViewById(R.id.gridView1);
        BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return imageId.length;//获取图片总数
            }

            @Override
            public Object getItem(int position) {
                return position;//获取当前选项
            }

            @Override
            public long getItemId(int position) {
                return position;//获取当前选项的ID
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                //声明ImageView的对象
                ImageView imageView;

                if (convertView == null){
                    imageView=new ImageView(HeadActivity.this);//实例化ImageView的对象
                    imageView.setAdjustViewBounds(true);
                    imageView.setMaxWidth(158);
                    imageView.setMaxHeight(150);
                    imageView.setPadding(5, 5, 5, 5);
                }else {
                    imageView=(ImageView)convertView;
                }
                imageView.setImageResource(imageId[position]);//为ImageView设置要显示的图片
                return imageView;
            }
        };

        gridView.setAdapter(baseAdapter);//将适配器与GridView关联
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = getIntent();//获取Intent对象
                Bundle bundle = new Bundle();//实例化要传递的数据包
                bundle.putInt("imageId", imageId[position]);//显示选中的图片
                intent.putExtras(bundle);//将数据包保存到intent中
                setResult(0x11, intent);//设置返回的结果码，并返回调用该Activity的Activity
                finish();//关闭当前Activity
            }
        });
    }
}
