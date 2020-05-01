package com.lsw.exercise4_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterActivity extends Activity {
    public int[] imageId = new int[]{R.drawable.boy, R.drawable.dog,
            R.drawable.frog, R.drawable.girl, R.drawable.peg, R.drawable.rab};

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Intent intent=getIntent();

        Bundle bundle=intent.getExtras();

        ImageView iv=(ImageView)findViewById(R.id.headImage);
        iv.setImageResource(bundle.getInt("headId"));

        TextView user=(TextView)findViewById(R.id.user);
        user.setText("用户名:" + bundle.getString("user"));

        TextView pwd=(TextView)findViewById(R.id.pwd);
        pwd.setText("密码:" + bundle.getString("pwd"));

        TextView email=(TextView)findViewById(R.id.email);
        email.setText("E-mail:" + bundle.getString("email"));
    }


}
