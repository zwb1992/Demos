package com.zwb.thirdpartydemo.activity;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zwb.thirdpartydemo.C;
import com.zwb.thirdpartydemo.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 介绍OkHttp的使用方法
 */
public class OkHttpActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = OkHttpActivity.class.getName();
    private static final int GET = 1;
    private static final int POST = 2;

    private ImageView imgBack;
    private Button btGet,btPost,btPostFile,btPostForm;
    private TextView tvResult,tvTitle;
    private OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        initView();
    }
    private void initView(){
        imgBack = (ImageView)findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        tvTitle = (TextView)findViewById(R.id.tv_title);
        tvTitle.setText("OkHttp");
        tvResult = (TextView)findViewById(R.id.tv_result);
        btGet = (Button)findViewById(R.id.bt_get);
        btGet.setOnClickListener(this);
        btPost = (Button)findViewById(R.id.bt_post);
        btPost.setOnClickListener(this);
        btPostFile  =(Button)findViewById(R.id.bt_post_file);
        btPostFile.setOnClickListener(this);
        btPostForm  =(Button)findViewById(R.id.bt_post_form);
        btPostForm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_back:
                break;
            case R.id.bt_get:
//                threadGet(C.TEST);
                //异步的方式
                getByAsy(C.HOST+C.LOGIN_GET);
                break;
            case R.id.bt_post:
//                threadPost(C.TEST,"");
                postByAsy(C.HOST+C.LOGIN_POST);
//                  postByAsyJson(C.HOST+C.LOGIN_POST);
                break;
            case R.id.bt_post_file:
                postByAsyFile(C.HOST+C.UPLOAD_FILE);
                break;
            case R.id.bt_post_form:
                postByAsyForm(C.HOST+C.UPLOAD_FORM);
                break;
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GET:
                    //同步的方式
                    String getResult = (String) msg.obj;
                    tvResult.setText("GET:"+getResult);
                    break;
                case POST:
                    String postResult = (String) msg.obj;
                    tvResult.setText("POST:"+postResult);
                    break;
            }
        }
    };

    /**
     * 放在线程里面执行get请求
     * @param url
     */
    private void threadGet(final String url){
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    String result = get(url);
                    Message msg = Message.obtain();
                    msg.what = GET;
                    msg.obj = result;
                    handler.sendMessage(msg);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();

    }

    /**
     * 这种方式要放在子线程里面去执行，不能直接放在主线程
     * @param url
     * @return
     * @throws IOException
     */
    private String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 以异步的方式去请求数据
     * @param url
     */
    private void getByAsy(String url){
        Request request = new Request.Builder().get().url(url).build();
        executeRequest(request);
    }

    private void executeRequest(Request request) {
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override // 这个方法在子线程
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvResult.setText(result);
                    }
                });
            }
        });
    }


    /**
     * 放在线程里面执行post请求
     * @param url
     */
    private void threadPost(final String url,final String json){
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    String result = post(url,json);
                    Message msg = Message.obtain();
                    msg.what = POST;
                    msg.obj = result;
                    handler.sendMessage(msg);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();

    }

    /**
     * post请求 同步的方式
     * @param url 地址
     * @param json 需要上传的数据
     * @return
     * @throws IOException
     */
    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    /**
     * 以post异步的方式去请求数据
     * @param url
     */
    private void postByAsy(String url){
        FormBody formBody = new FormBody.Builder().add("name","zwb").add("password","1234567").build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        executeRequest(request);
    }

    /**
     * 以post异步的方式去请求数据,传递一个json给服务器
     * @param url
     */
    private void postByAsyJson(String url){
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), C.JSON);
        Request request = new Request.Builder().url(url).post(body).build();
        executeRequest(request);
    }

    /**
     * 以post异步的方式去请求数据,传递一个文件，单纯地一个文件
     * @param url
     */
    private void postByAsyFile(String url){
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"PetCircleImages"+File.separator+"142.png";
        File file = new File(path);
        if(!file.exists()){
            Log.e(TAG,"path is not exists...");
            return;
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        Request request = new Request.Builder().url(url).post(body).build();
        executeRequest(request);
    }

    /**
     * 以post异步的方式去请求数据,传递一个文件，以及其他字段，即表单上传
     * @param url
     */
    private void postByAsyForm(String url){
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"PetCircleImages"+File.separator+"142.png";
        File file = new File(path);
        if(!file.exists()){
            Log.e(TAG,"path is not exists...");
            return;
        }
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("mPhoto","zwb.jpg",RequestBody.create(MediaType.parse("application/octet-stream"),file))
                .addFormDataPart("name","zwb")
                .addFormDataPart("password","12345")
                .setType(MultipartBody.FORM);

        RequestBody body = builder.build();
        Request request = new Request.Builder().url(url).post(body).build();
        executeRequest(request);
    }


    /**
     * 下载文件
     * @param url
     */
    private void doDownload(String url){
        Request request = new Request.Builder().get().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG,e.getMessage());
            }

            @Override // 这个方法在子线程
            public void onResponse(Call call, Response response) throws IOException {
               InputStream inputStream =  response.body().byteStream();
            }
        });
    }
}
