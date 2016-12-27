package com.example.glidedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> list;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recycleView);
        initData();
        recyclerView.setHasFixedSize(true);//设置固定大小
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        button = (Button)findViewById(R.id.bt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setAdapter(new MyAdapter(MainActivity.this,list));
            }
        });
    }
    private void initData(){
        list = new ArrayList<>();
        list.add("https://img04.sogoucdn.com/app/a/10010016/f15f1d57418a14db105728a94ce18d26");
        list.add("https://img01.sogoucdn.com/net/a/04/link?url=http%3A%2F%2Fi02.pic.sogou.com%2Fcc6b5ecb49d9f3c5&appid=122");
        list.add("https://img04.sogoucdn.com/net/a/04/link?url=http%3A%2F%2Fi04.pic.sogou.com%2F54473519fece5e39&appid=122");
        list.add("https://img01.sogoucdn.com/app/a/10010016/cbc16c3364f69e6e6dbc51149aec4108");
        list.add("https://img02.sogoucdn.com/net/a/04/link?url=http%3A%2F%2Fi02.pic.sogou.com%2F6ae9b4b68ef8ab1d&appid=122");
        list.add("https://img03.sogoucdn.com/app/a/10010016/7ea6c52b9b43fc9091dfcf55c2eb6ad5");
        list.add("https://img02.sogoucdn.com/net/a/04/link?url=http%3A%2F%2Fi01.pic.sogou.com%2Fae069a5b8cd612ba&appid=122");
        list.add("https://img04.sogoucdn.com/app/a/10010016/337c7214d838d26c9576062214d95f52");
        list.add("https://img03.sogoucdn.com/net/a/04/link?&url=http%3A%2F%2Ftva4.sinaimg.cn%2Fcrop.0.0.474.474.180%2F04f2c14egw1emsvenwld6j20d70d741l.jpg&appid=100520117&referer=http://weibo.com/uc83018062");
        list.add("https://img03.sogoucdn.com/app/a/10010016/5d15edb7b7a2fedca00628e71913756d");
        list.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3499823653,4060926856&fm=116&gp=0.jpg");
        list.add("https://img03.sogoucdn.com/app/a/10010016/0dcf66976ede5a7befc8a1bc4bc3c397");
        list.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2489051743,1646771720&fm=116&gp=0.jpg");
        list.add("https://img01.sogoucdn.com/app/a/10010016/802deff65a3d165bef648032d7a8c0d3");
        list.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3407852600,1845840160&fm=116&gp=0.jpg");
        list.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=4057565284,2847218841&fm=116&gp=0.jpg");
        list.add("https://img04.sogoucdn.com/app/a/10010016/89e8020f7ff95c0868be1abfcd2095f8");
        list.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=214931719,1608091472&fm=116&gp=0.jpg");
        list.add("https://img03.sogoucdn.com/app/a/10010016/d80b992c924622f4c404679eca6ae4f1");
        list.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1120065146,4182425066&fm=116&gp=0.jpg");
        list.add("https://img01.sogoucdn.com/app/a/10010016/3d4a2ebffe2352c4d3d1cfef8aa4a490");
        list.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2965812645,1180239355&fm=116&gp=0.jpg");
        list.add("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=3706555796,1550133346&fm=58");
        list.add("https://ss0.baidu.com/73x1bjeh1BF3odCf/it/u=3923069785,3736427645&fm=85&s=AF501CC7146297574249393C03001002");
        list.add("https://img01.sogoucdn.com/app/a/10010016/9a32ef410a565e0bd56ded3558e06750");
        list.add("https://ss0.baidu.com/73F1bjeh1BF3odCf/it/u=3094324923,617905685&fm=96&s=D23E3CC4D6D9912E31101C790300C050");
        list.add("https://img01.sogoucdn.com/app/a/10010016/c268b79ef35417683b1d7e99ab6d9c1c");
        list.add("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=3587085728,2856235770&fm=58&s=0112C432D6D3F99248CC4BC40300A0A6");
        list.add("https://ss0.baidu.com/73t1bjeh1BF3odCf/it/u=4037127623,423097930&fm=85&s=5696A464AEEB132C3888AD900300C09B");
        list.add("https://ss2.bdstatic.com/8_V1bjqh_Q23odCf/pacific/upload_21700613_1471489815321.jpg");
        list.add("http://bpic.588ku.com/element_banner/20/16/12/b59a8df2718aa3a8ef411e9850e5a2f0.jpg");
    }
}
