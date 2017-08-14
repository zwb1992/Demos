package com.zwb.customlauncher;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.zwb.customlauncher.bean.NewDetailsBean;
import com.zwb.customlauncher.bean.WeatherBean;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    private WebView webView;
    private TextView tvTitle, tvTime;
    private ImageView img;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年M月dd日");
    private SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String body, url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        webView = (WebView) view.findViewById(R.id.webView);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvTime = (TextView) view.findViewById(R.id.tvTime);
        img = (ImageView) view.findViewById(R.id.img);
        Bundle bundle = getArguments();
        String title = bundle.getString("title");
        String time = bundle.getString("time");//2017-08-13 14:42:47
        String imgUrl = bundle.getString("imgUrl");
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
        if (!TextUtils.isEmpty(time)) {
            try {
                Date d = dFormat.parse(time);
                String s = dateFormat.format(d);
                tvTime.setText(s);
            } catch (Exception e) {
                tvTime.setText(time);
            }

        }
        if (!TextUtils.isEmpty(imgUrl)) {
            img.setVisibility(View.VISIBLE);
            Glide.with(this)
                    .load(imgUrl)
                    .error(R.mipmap.photo_no)
                    .into(img);
        }
        url = bundle.getString("url");
        Log.e("info", "====url=====" + url);

        String body = "<p>　　“中国追求的是共同发展。我们既要让自己过得好,也要让别人过得好。”习近平用这句话生动概括了当前国际形势下，中国对世界秩序的构想与主张。</p><p>　　这五年，中国这个世界第二大经济体，在他的倡议和理念引领下，走到了世界舞台中央，办了不少大事，引发国际社会广泛关注和积极评价，获得许多国家积极响应和支持。</p><p>　　<strong>一、为全球治理贡献中国智慧，用大国担当“实力圈粉”</strong></p><p>　　在西方传统的国际治理话语中，充斥着各种“陷阱”：“修昔底德陷阱”鼓吹零和博弈，“金德尔伯格陷阱”渲染全球秩序真空，“中等收入陷阱”被用作唱衰新兴经济体&hellip;&hellip;</p><p>　　这些“陷阱”虚实如何，能否避开？习近平提出一系列新理念应对：以合作共赢为核心的新型国际关系；共同、综合、合作、可持续的新安全观；人类命运共同体理念&hellip;&hellip;</p><p>　　行胜于言。习近平曾多次强调：“一分部署，九分落实，中国说过的话一定算数。”较之以前，中国这五年提供了越来越多带有“中国烙印”的公共产品，在国际舞台上“实力圈粉”。</p><p>　　中国宣布建立200亿元人民币的“中国气候变化南南合作基金”，设立总额为10亿美元的中国—联合国和平与发展基金。</p><p>　　在美国宣布要退出气候变化《巴黎协定》时，中国郑重承诺将“坚定不移地做全球气候治理进程的维护者和推动者”。</p><p>　　积极参与联合国维和、开展国际人道主义救援。索马里护航、尼泊尔地震救援、也门撤侨等一系列重大事件，中国冲在一线、表现优异，赢得世界尊敬。</p><!--IMG#0--><p>　　倡导创立亚洲基础设施投资银行和金砖国家新开发银行，开创发展中国家组建多边金融机构的先河。</p><p>　　“世界那么大，问题那么多”，中国声音、中国方案正不断被国际社会重视和接受。</p><p>　　<strong>二、打造“一带一路”，与沿线国家分享发展红利</strong></p><p>　　当前世界，逆全球化暗流涌动，个别国家正转向奉行保护主义政策，给经济全球化带来新的不确定性因素。</p><p>　　“世界怎么了，我们怎么办”？习近平明确提出中国方案：构建人类命运共同体，实现共赢共享，呼吁各国通过创新驱动、合作共赢、公平包容来解决问题。“中国开放的大门永远不会关上！就像阿里巴巴芝麻开门，开开了就关不上了”。</p><p>　　他在今年1月郑重承诺，未来5年，中国将进口8万亿美元的商品，吸收6000亿美元的外来投资，对外投资总额将达到7500亿美元，出境旅游将达到7亿人次，将为世界各国发展带来更多机遇。</p><p>　　其实，这种共赢共享理念，中国早已付诸实践。自2013年秋西赴哈萨克斯坦、南下印度尼西亚以来，习近平提出的丝绸之路经济带和21世纪海上丝绸之路已成为沿线国家纷纷对接的发展蓝图。2014年至2016年，中国同“一带一路”沿线国家贸易总额超过3万亿美元。中国企业已在20多个国家建设56个经贸合作区，为有关国家创造18万个就业岗位。</p><p>　　与之相呼应，4年来，全球100多个国家和国际组织积极支持和参与“一带一路”建设，联合国大会等重要决议也纳入“一带一路”建设内容。</p><!--IMG#1--><p>　　正是因为这份坚持和成绩，今年5月的“一带一路”国际合作高峰论坛迎来了来自130多个国家、70多个国际组织的嘉宾，他们对习近平的主旨演讲报以27次热烈掌声。“一带一路”倡议，从中国出发，正成为世界共享的梦想。</p><p>　　<strong>三、中国的“朋友圈”越来越大，外交成绩单亮眼</strong></p><p>　　做大中国的“朋友圈”是对这五年中国外交的生动总结。</p><p>　　近五年来，习近平已出访30余次，平均每年都有1个月以上的时间用于出访，足迹几乎遍及全球。在国内会晤外国元首、政府首脑270多人次，杭州G20峰会期间平均每天会见6位领导人，休息时间仅有4小时。他曾笑谈《时间都去哪儿了》，幽默地说：“每次用这么多时间出访很&lsquo;奢侈&rsquo;，但很有必要。”</p><p>　　大国是关键，周边是首要，发展中国家是基础，多边是重要舞台。中国外交延续着基本的布局框架，却又不断开拓创新。</p><!--IMG#2--><p>　　4年多时间，俄罗斯总统普京先后6次到访中国，习近平也6次访问俄罗斯；</p><p>　　习奥庄园会晤、瀛台夜话、白宫秋叙，不拘一格的会晤方式让中美两国关系迈上新征程；</p><p>　　“高空外交”别开生面，哈萨克斯坦总统纳扎尔巴耶夫、古巴国务委员会主席劳尔·卡斯特罗都曾登上习近平的专机&hellip;&hellip;</p><p>　　其中，不少到访国为中国准备了最高规格乃至超规格礼遇。2014年3月，习近平访问荷兰、法国等国家，护航战机、绶带勋章&hellip;&hellip;2015年10月，习近平访英，英国王室用皇家马车将习近平主席夫妇请入白金汉宫。</p><p>　　朋友多了，就能在一起办不少大事、实事。截至2016年底，中国已同97个国家和国际组织建立了不同形式的伙伴关系；中方发起的亚投行开业运营，成员总数目前已达80个。</p><p>　　“朋友圈”越来越大，让中国的国际地位和话语权得到空前提升。</p><p>　　<strong>四、不惹事也不怕事，宣示底线赢得世界尊敬</strong></p><p>　　有实力，才有和平。过去几年里，中国虽面临复杂的国际和地区形势，但在维护国家主权、安全和发展利益方面更加积极有所作为。</p><p>　　在钓鱼岛问题上针锋相对，宣布划设东海防空识别区，实行常态化巡航；</p><p>　　在南海造岛固礁，用“三不原则”回击南海仲裁、常态化战斗巡航&hellip;&hellip;对此，在2017年新年贺词中，习近平强调，中国坚持走和平发展道路，同时坚决捍卫领土主权和海洋权益。“谁要在这个问题上做文章，中国人民决不答应!”</p><!--IMG#3--><p>　　在更加有力地维护国家核心利益的同时，中国也注重相关举措的有利和有节。当菲律宾总统杜特尔特表达对华友好姿态后，中国及时出手促成中菲关系转变，挫败了有关国家进一步搅乱南海局势的图谋。</p><p>　　可以说，近五年来，中国在继续坚持走和平发展道路的同时，更加强调“底线思维”和“底线原则”。正如习近平所说，中国不惹事也不怕事，“任何外国不要指望我们会拿自己的核心利益做交易，不要指望我们会吞下损害我国主权、安全、发展利益的苦果”。这话很明白，中国的善意不是没有原则，克制不是没有底线，有些国家的幻想要尽快打消！</p><p>原标题：这五年，习近平引领中国在国际舞台办了这些大事</p>";

        if (TextUtils.isEmpty(url)) {
            webView.setVisibility(View.GONE);
        } else {
            webView.setVisibility(View.VISIBLE);
            WebSettings mWebSettings = webView.getSettings();
            //自适应屏幕
            mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            mWebSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
            // 打开页面时， 自适应屏幕
            mWebSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
            mWebSettings.setSupportZoom(true); //支持缩放
            mWebSettings.setJavaScriptEnabled(true);  //开启javascript
            mWebSettings.setDomStorageEnabled(true);  //开启DOM
            mWebSettings.setDefaultTextEncodingName("utf-8"); //设置编码
            // // web页面处理
            mWebSettings.setAllowFileAccess(true);// 支持文件流

            //提高网页加载速度，暂时阻塞图片加载，然后网页加载好了，再进行加载图片
            mWebSettings.setBlockNetworkImage(true);
            //开启缓存机制
            mWebSettings.setAppCacheEnabled(true);
            mWebSettings.setTextSize(WebSettings.TextSize.SMALLEST);


            getDetails(url);
        }

        return view;
    }

    private Call detailsCall;

    /**
     * 获取天气
     */
    private void getDetails(final String url) {
        String realUrl = String.format(C.NEWS_DETAILS_API, url);
        Request request = new Request.Builder().get().url(realUrl).build();
        detailsCall = new OkHttpClient().newCall(request);
        detailsCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                if (handler != null) {
                    handler.postDelayed(runnable, 1000 * 60);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                try {
                    org.json.JSONObject object = new org.json.JSONObject(result);
                    object = object.getJSONObject(url);
                    NewDetailsBean newDetailsBean = JSON.parseObject(object.toString(), NewDetailsBean.class);
                    if (newDetailsBean != null) {
                        body = newDetailsBean.getBody();
                        if (handler != null) {
                            handler.sendEmptyMessage(2);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (handler != null) {
                        handler.postDelayed(runnable, 1000 * 60);
                    }
                }
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("info", "====body=====" + body);
            // 使用css样式的方式设置图片大小
            String css = "<style type=\"text/css\"> img {" +
                    "width:100%;" +
                    "height:auto;" +
                    "}" +
                    "body {" +
                    "background-color: black;" +
                    "margin-right:15px;" +
                    "margin-left:15px;" +
                    "margin-top:15px;" +
                    "font-size:48px;" +
                    "}" +
                    "p {color:#FFFFFF;}" +
                    "</style>";
            String html = "<html><header>" + css + "</header><body>" + body + "</body></html>";
            webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", "");
        }
    };

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            getDetails(url);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (detailsCall != null) {
            detailsCall.cancel();
        }
        handler.removeCallbacks(runnable);
        handler = null;
    }
}
