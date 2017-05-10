package com.zwb.thirdpartydemo.activity;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.zwb.thirdpartydemo.R;

import java.io.File;

/**
 * glide,picasso基本使用方式
 */
public class GlideBaseActivity extends AppCompatActivity {

    private TextView tvTitle;
    private ImageView back;
    private ImageView img_1,img_2,img_3,img_4,img_5,img_6,img_7,img_8,img_9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_base);
        initView();
        initData();
    }


    private void initView() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("Glide");
        back = (ImageView) findViewById(R.id.img_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        img_1 = (ImageView) findViewById(R.id.img_1);
        img_2 = (ImageView) findViewById(R.id.img_2);
        img_3 = (ImageView) findViewById(R.id.img_3);
        img_4 = (ImageView) findViewById(R.id.img_4);
        img_5 = (ImageView) findViewById(R.id.img_5);
        img_6 = (ImageView) findViewById(R.id.img_6);
        img_7 = (ImageView) findViewById(R.id.img_7);
        img_8 = (ImageView) findViewById(R.id.img_8);
        img_9 = (ImageView) findViewById(R.id.img_9);
    }

   private void initData(){
       //1，加载网络图片
       Glide.with(this).load("http://img1.imgtn.bdimg.com/it/u=2615772929,948758168&fm=21&gp=0.jpg").into(img_1);

       //2，加载资源图片
       Glide.with(this).load(R.mipmap.ic_launcher).into(img_2);

       //3，加载本地图片
       String path = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"PetCircleImages"+
               File.separator+"app1484118312650.jpg";
       File file = new File(path);
       Uri uri = Uri.fromFile(file);
       Glide.with(this).load(uri).into(img_3);

       //4,加载gif图片
       Glide.with(this)
               .load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=4274283541,1926494440&fm=23&gp=0.jpg")
               .placeholder(R.mipmap.ic_launcher)
               .into(img_4);
//       Picasso.with(this)
//               .load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=4274283541,1926494440&fm=23&gp=0.jpg")
//               .placeholder(R.mipmap.ic_launcher)
//               .into(img_4);

       //5,加载资源gif
       Glide.with(this).load(R.mipmap.run).asGif().placeholder(R.mipmap.ic_launcher).into(img_5);

       //6,加载本地gif

       String path1 = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"PetCircleImages"+
               File.separator+"papapa.gif";
       File file1 = new File(path1);
       Glide.with(this).load(file1).placeholder(R.mipmap.ic_launcher).into(img_6);

       //7,加载本地小视频快照

       String path2 = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"PetCircleImages"+
               File.separator+"v4.3gp";
       File file2 = new File(path2);
       Glide.with(this).load(Uri.fromFile(file2)).placeholder(R.mipmap.ic_launcher).into(img_7);

       //8,先加载缩略图，再加载原图
       String path3 = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"PetCircleImages"+
               File.separator+"app1484188559796.jpg";
       File file3 = new File(path3);
       Glide.with(this).load(Uri.fromFile(file3)).thumbnail(0.1f).centerCrop().into(img_8);

       //9，先建立一个缩略图，然后先加载缩略图，再加载原图
       DrawableRequestBuilder builder = Glide.with(this).load(file2);
       Glide.with(this).load(Uri.fromFile(file3)).thumbnail(builder).centerCrop().into(img_9);
   }

}
