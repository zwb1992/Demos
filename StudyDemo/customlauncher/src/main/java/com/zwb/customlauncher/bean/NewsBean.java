package com.zwb.customlauncher.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zwb
 * Description
 * Date 2017/8/11.
 */

public class NewsBean {

    /**
     * reason : 成功的返回
     * result : {"stat":"1","data":[{"uniquekey":"a437e64c91c68a45a256cb6e92d6dc52","title":"《侠盗联盟》发\u201c漫画\u201d预告片林肯公园新歌作片尾","date":"2017-08-11 09:26","category":"头条","author_name":"新华网","url":"http://mini.eastday.com/mobile/170811092601245.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170811/20170811092601_1e560cd750a1983d8bdddbb7b8d143e3_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20170811/20170811092601_1e560cd750a1983d8bdddbb7b8d143e3_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04.imgmini.eastday.com/mobile/20170811/20170811092601_1e560cd750a1983d8bdddbb7b8d143e3_1_mwpm_03200403.jpg"},{"uniquekey":"d9a10eba51b1fe099cff0ef48eacbe48","title":"奚国华任新兴际华集团有限公司董事长、党委书记","date":"2017-08-11 09:19","category":"头条","author_name":"人民网","url":"http://mini.eastday.com/mobile/170811091901269.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170811/20170811091901_38386e25c2ef4fa3009be15cc105d648_1_mwpm_03200403.jpg"},{"uniquekey":"606048bbea92bcca96f62a9cec7aebff","title":"注意了！你在韩国穿到的可能是四不像的\u201c假韩服\u201d","date":"2017-08-11 09:17","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170811091712720.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170811/20170811091712_e0ba93937974d6088f69a506a6b1158e_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20170811/20170811091712_e0ba93937974d6088f69a506a6b1158e_1_mwpm_03200403.jpg"},{"uniquekey":"51802445ff64dd9d25304eb461733405","title":"巴黎车撞哨兵嫌犯无任何前科 法国反恐再遇难题","date":"2017-08-11 09:07","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170811090708103.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170811/20170811090708_4f672de0ed72723ba43aa92feeb293d9_1_mwpm_03200403.jpg"},{"uniquekey":"f4ec9e7a6e44c06449271f5a2d793d05","title":"秦岭隧道发生特大交通事故致36死 现场图曝光","date":"2017-08-11 08:58","category":"头条","author_name":"陕视新闻","url":"http://mini.eastday.com/mobile/170811085810136.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20170811/20170811085810_95e20ce652b7cb881b42362fac53f846_1_mwpm_03200403.jpg"},{"uniquekey":"3d548fe308fdb9f9636edc42f430e090","title":"陕西发生交通事故致36人死 涉事车辆撞向隧道口","date":"2017-08-11 08:54","category":"头条","author_name":"中国新闻网","url":"http://mini.eastday.com/mobile/170811085435706.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20170811/20170811085435_2ffe8263b36016bdf39b31f735dad074_1_mwpm_03200403.jpg"},{"uniquekey":"0e7a8ca1b141416a4ccbdda13c863302","title":"东北小伙遇\"北派\"传销 凑不齐入会费被打得肾衰竭","date":"2017-08-11 08:40","category":"头条","author_name":"浙江在线","url":"http://mini.eastday.com/mobile/170811084043373.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20170811/20170811084043_955b517cc06912a52ea96d1e78444c86_1_mwpm_03200403.jpg"},{"uniquekey":"2bae4c86b8105767a12ac7d22f0609e2","title":"美驱逐舰擅闯南沙美济礁 中国派出军舰警告驱离","date":"2017-08-11 08:36","category":"头条","author_name":"环球时报","url":"http://mini.eastday.com/mobile/170811083653832.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170811/20170811083653_819f0fb9569e44a1d454078f70b9e371_1_mwpm_03200403.jpg"},{"uniquekey":"184e83ab45cf3c40dd1dee756baaa7e5","title":"日本称中国海警船又进其领海 一点情况罕见","date":"2017-08-11 08:36","category":"头条","author_name":"环球时报","url":"http://mini.eastday.com/mobile/170811083653239.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170811/20170811083653_5abee779296f2240307fbc1a81ee00fc_1_mwpm_03200403.jpg"},{"uniquekey":"d703025cbb595e05be4088b5dd36aeee","title":"九寨沟地震｜护士连轴转40小时：要把伤员从死亡边缘拉回来","date":"2017-08-11 08:32","category":"头条","author_name":"澎湃新闻网","url":"http://mini.eastday.com/mobile/170811083220568.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20170811/20170811083220_3b05ea65c0166b13103b64db7c60ce94_1_mwpm_03200403.jpg"},{"uniquekey":"eb1fde0ae5429e10d2dbf4a4444bd544","title":"个人定制游需求多元 物有所值怎么体现","date":"2017-08-11 08:25","category":"头条","author_name":"解放日报","url":"http://mini.eastday.com/mobile/170811082535244.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170811/20170811082535_27f2b130c96d6ce796cfe303c5d1a279_1_mwpm_03200403.jpg"},{"uniquekey":"9de73315fa167a9c4611f2e14c5aae57","title":"等解放军打进印度领土后，欢迎达赖发表和平宣言！","date":"2017-08-11 08:21","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170811082127590.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170811/20170811082127_0594bbd1df158c98503541252f6d2074_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20170811/20170811082127_0594bbd1df158c98503541252f6d2074_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20170811/20170811082127_0594bbd1df158c98503541252f6d2074_2_mwpm_03200403.jpg"},{"uniquekey":"811819586c00da8906da8fb49b5955e7","title":"八月份桃花运财运双喜临门的生肖","date":"2017-08-11 08:16","category":"头条","author_name":"美国神婆星座网","url":"http://mini.eastday.com/mobile/170811081650148.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170811/20170811081650_88df3b82c5a44bbfeee2f657e4d78424_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20170811/20170811081650_88df3b82c5a44bbfeee2f657e4d78424_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03.imgmini.eastday.com/mobile/20170811/20170811081650_88df3b82c5a44bbfeee2f657e4d78424_2_mwpm_03200403.jpg"},{"uniquekey":"d4f090a1377a7f13cd168a3ab672fcc5","title":"原北京军区司令员周衣冰中将逝世，享年95岁","date":"2017-08-11 08:14","category":"头条","author_name":"澎湃新闻网","url":"http://mini.eastday.com/mobile/170811081403815.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20170811/20170811081403_25bbd0ef027144e400d0d281ed2e61c3_1_mwpm_03200403.jpg"},{"uniquekey":"e57528812ee1906e1884b0875d2b7a79","title":"中国对马来西亚出售AR3远火传闻令新加坡紧张","date":"2017-08-11 08:06","category":"头条","author_name":"环球时报","url":"http://mini.eastday.com/mobile/170811080640425.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170811/20170811080640_7a8869eac03228b5d8d4e5a0a43ba532_1_mwpm_03200403.jpg"},{"uniquekey":"a0372f31a8e027a84b2e06168de7204e","title":"印防长称已吸取1962年教训 放话印军已足够强大","date":"2017-08-11 08:06","category":"头条","author_name":"环球时报","url":"http://mini.eastday.com/mobile/170811080636728.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170811/20170811080636_6e1a1d1e8ccadb448c8308f9686aa50e_1_mwpm_03200403.jpg"},{"uniquekey":"0dea4a05d20e7cffb8f0f9027771adeb","title":"日称我电子战飞机绕飞台湾 专家：合法、合理","date":"2017-08-11 07:56","category":"头条","author_name":"环球时报","url":"http://mini.eastday.com/mobile/170811075630709.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170811/20170811075630_611c1baabb065ecbd6f2a3dc9792682b_1_mwpm_03200403.jpg"},{"uniquekey":"ae4fe3f3fc3cef302dda29068c56ebeb","title":"印度外长访尼泊尔参加区域外长会，将与不丹外长举行双边会谈","date":"2017-08-11 07:53","category":"头条","author_name":"澎湃新闻网","url":"http://mini.eastday.com/mobile/170811075314395.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170811/20170811075314_c2af0e5e36baa35ca4456539ff1f191e_1_mwpm_03200403.jpg"},{"uniquekey":"b0d2371bb283bf6661df708c611a6cc2","title":"九寨沟地震：14名遇难人员名单身份确认","date":"2017-08-11 07:53","category":"头条","author_name":"四川新闻网","url":"http://mini.eastday.com/mobile/170811075305636.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170811/20170811075305_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03200403.jpg"},{"uniquekey":"a9a55a5774bb38aea9dec943ea8f93d3","title":"这个国家最讨厌大胸的女人：残忍割掉自己乳房 原因让人震惊不已","date":"2017-08-11 07:52","category":"头条","author_name":"映图影志","url":"http://mini.eastday.com/mobile/170811075212168.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170811/20170811_fc52c8c42bbcf087f172aae92965a894_cover_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20170811/20170811_98703c06da64d97488fe93b5effb8e20_cover_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20170811/20170811_86c3a4348bc2e60f3953c5065947624b_cover_mwpm_03200403.jpeg"},{"uniquekey":"3627fd7240c1349221b15339f1c44a7e","title":"\u201c怪物\u201d突然现身居民家中，趴在床上吓得屋主尖叫报警","date":"2017-08-11 07:50","category":"头条","author_name":"图看世界","url":"http://mini.eastday.com/mobile/170811075039782.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170811/20170811_ded1560e564031c2cb14a2e6fa426641_cover_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20170811/20170811_dc543e0068d120a0af75f47913e3eb83_cover_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20170811/20170811_77cb7faf39407a44a16f61043fd6762b_cover_mwpm_03200403.jpeg"},{"uniquekey":"d97a6686aee4b5f76154a731d593494f","title":"日本怎么了？疯狂采购美国进攻武器大肆扩军 对抗中国是一枕黄粱","date":"2017-08-11 07:49","category":"头条","author_name":"军评陈光文","url":"http://mini.eastday.com/mobile/170811074923021.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170811/20170811_1e86359e7a8582067e73ea6f17935127_cover_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20170811/20170811_d22e31c6c6880210000b70da5286a113_cover_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://03.imgmini.eastday.com/mobile/20170811/20170811_b7c1c71078c2d7989044daa8d96dad2d_cover_mwpm_03200403.jpeg"},{"uniquekey":"d44de91666efe99e8500528295e4807e","title":"亨通富贵！喜讯常在的3大生肖属相！","date":"2017-08-11 07:49","category":"头条","author_name":"吉名轩八字星座","url":"http://mini.eastday.com/mobile/170811074915976.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170811/20170811_a680f1ea7d312b47820747df9d6608ee_cover_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20170811/20170811_8bae77d07f30c07be9e0a8fa0c27dc2a_cover_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://04.imgmini.eastday.com/mobile/20170811/20170811_a50009db9dc13dce4f5b78e7e6891537_cover_mwpm_03200403.jpeg"},{"uniquekey":"d7ad8049f6dd16fb51a421d366435545","title":"俄罗斯漂亮美女街头随处可见 为什么结婚后\u201c速成大妈\u201d?","date":"2017-08-11 07:48","category":"头条","author_name":"三国迷醉","url":"http://mini.eastday.com/mobile/170811074817529.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20170811/20170811_5a6231a914e67652f1b2284557398be3_cover_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://02.imgmini.eastday.com/mobile/20170811/20170811_d0b2931946023fb1fa9b059dff9fb969_cover_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://02.imgmini.eastday.com/mobile/20170811/20170811_d91ee0095da753d55d4ab993466fab42_cover_mwpm_03200403.jpeg"},{"uniquekey":"39a31a3f4baecebb3fc17103a1ae471a","title":"新疆精河确保受灾群众入冬前全部住进富民安居房","date":"2017-08-11 07:42","category":"头条","author_name":"人民网","url":"http://mini.eastday.com/mobile/170811074240078.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170811/20170811074240_4d5cf62e009582f44eda2f4bfd662ea7_1_mwpm_03200403.jpg"},{"uniquekey":"ab46c4d86a20b674bed3759ccf17cf03","title":"成都90后女生拾金不昧 上海失主\u201c送\u201d她房被婉拒","date":"2017-08-11 07:41","category":"头条","author_name":"成都晚报","url":"http://mini.eastday.com/mobile/170811074109668.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170811/20170811074109_d245237ebe097a1a98603ec13cff319d_1_mwpm_03200403.jpg"},{"uniquekey":"7058ea2e280811de984f1ce80a436f91","title":"蒋介石当众被挨一脚不敢吱声，这牛人是谁？场面难堪的很","date":"2017-08-11 07:38","category":"头条","author_name":"传记人生","url":"http://mini.eastday.com/mobile/170811073834666.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170811/20170810_3acff1a1fcb3e087bd9d455a8560fab8_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20170811/20170810_a3134994a765e6d9854a062ac97be8a8_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06.imgmini.eastday.com/mobile/20170811/20170810_14b82dba9d7c59f45b3460e73d6b5b05_mwpm_03200403.jpeg"},{"uniquekey":"991d01c5fab8adadc7878e421c9c867b","title":"专访《印度对华战争》作者：印度错把中国当成他们的敌人","date":"2017-08-11 07:26","category":"头条","author_name":"环球时报","url":"http://mini.eastday.com/mobile/170811072619711.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170811/20170811072619_71df1afeeb6456df3567db29d2bf7361_1_mwpm_03200403.jpg"},{"uniquekey":"7f7c3c2016d290b0d3d985ceafe80ce9","title":"面馆雇俩机器人削面 一年多挣十几万","date":"2017-08-11 07:26","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170811072618877.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20170811/20170811072618_06e4fc6297c41d90d78ebc94ee88a216_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20170811/20170811072618_06e4fc6297c41d90d78ebc94ee88a216_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20170811/20170811072618_06e4fc6297c41d90d78ebc94ee88a216_1_mwpm_03200403.jpg"},{"uniquekey":"8a139bfaafeac730e68d3aa4d06ce1de","title":"印度冒出\u201c打赢中国\u201d狂言 专家：莫迪政府若顽固不化将成罪人","date":"2017-08-11 07:23","category":"头条","author_name":"解放网","url":"http://mini.eastday.com/mobile/170811072300169.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170811/20170811072300_bf19d0ad62c7c985fc0ca22790b0dfb5_1_mwpm_03200403.jpg"}]}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * stat : 1
         * data : [{"uniquekey":"a437e64c91c68a45a256cb6e92d6dc52","title":"《侠盗联盟》发\u201c漫画\u201d预告片林肯公园新歌作片尾","date":"2017-08-11 09:26","category":"头条","author_name":"新华网","url":"http://mini.eastday.com/mobile/170811092601245.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170811/20170811092601_1e560cd750a1983d8bdddbb7b8d143e3_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20170811/20170811092601_1e560cd750a1983d8bdddbb7b8d143e3_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04.imgmini.eastday.com/mobile/20170811/20170811092601_1e560cd750a1983d8bdddbb7b8d143e3_1_mwpm_03200403.jpg"},{"uniquekey":"d9a10eba51b1fe099cff0ef48eacbe48","title":"奚国华任新兴际华集团有限公司董事长、党委书记","date":"2017-08-11 09:19","category":"头条","author_name":"人民网","url":"http://mini.eastday.com/mobile/170811091901269.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170811/20170811091901_38386e25c2ef4fa3009be15cc105d648_1_mwpm_03200403.jpg"},{"uniquekey":"606048bbea92bcca96f62a9cec7aebff","title":"注意了！你在韩国穿到的可能是四不像的\u201c假韩服\u201d","date":"2017-08-11 09:17","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170811091712720.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170811/20170811091712_e0ba93937974d6088f69a506a6b1158e_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20170811/20170811091712_e0ba93937974d6088f69a506a6b1158e_1_mwpm_03200403.jpg"},{"uniquekey":"51802445ff64dd9d25304eb461733405","title":"巴黎车撞哨兵嫌犯无任何前科 法国反恐再遇难题","date":"2017-08-11 09:07","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170811090708103.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170811/20170811090708_4f672de0ed72723ba43aa92feeb293d9_1_mwpm_03200403.jpg"},{"uniquekey":"f4ec9e7a6e44c06449271f5a2d793d05","title":"秦岭隧道发生特大交通事故致36死 现场图曝光","date":"2017-08-11 08:58","category":"头条","author_name":"陕视新闻","url":"http://mini.eastday.com/mobile/170811085810136.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20170811/20170811085810_95e20ce652b7cb881b42362fac53f846_1_mwpm_03200403.jpg"},{"uniquekey":"3d548fe308fdb9f9636edc42f430e090","title":"陕西发生交通事故致36人死 涉事车辆撞向隧道口","date":"2017-08-11 08:54","category":"头条","author_name":"中国新闻网","url":"http://mini.eastday.com/mobile/170811085435706.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20170811/20170811085435_2ffe8263b36016bdf39b31f735dad074_1_mwpm_03200403.jpg"},{"uniquekey":"0e7a8ca1b141416a4ccbdda13c863302","title":"东北小伙遇\"北派\"传销 凑不齐入会费被打得肾衰竭","date":"2017-08-11 08:40","category":"头条","author_name":"浙江在线","url":"http://mini.eastday.com/mobile/170811084043373.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20170811/20170811084043_955b517cc06912a52ea96d1e78444c86_1_mwpm_03200403.jpg"},{"uniquekey":"2bae4c86b8105767a12ac7d22f0609e2","title":"美驱逐舰擅闯南沙美济礁 中国派出军舰警告驱离","date":"2017-08-11 08:36","category":"头条","author_name":"环球时报","url":"http://mini.eastday.com/mobile/170811083653832.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170811/20170811083653_819f0fb9569e44a1d454078f70b9e371_1_mwpm_03200403.jpg"},{"uniquekey":"184e83ab45cf3c40dd1dee756baaa7e5","title":"日本称中国海警船又进其领海 一点情况罕见","date":"2017-08-11 08:36","category":"头条","author_name":"环球时报","url":"http://mini.eastday.com/mobile/170811083653239.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170811/20170811083653_5abee779296f2240307fbc1a81ee00fc_1_mwpm_03200403.jpg"},{"uniquekey":"d703025cbb595e05be4088b5dd36aeee","title":"九寨沟地震｜护士连轴转40小时：要把伤员从死亡边缘拉回来","date":"2017-08-11 08:32","category":"头条","author_name":"澎湃新闻网","url":"http://mini.eastday.com/mobile/170811083220568.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20170811/20170811083220_3b05ea65c0166b13103b64db7c60ce94_1_mwpm_03200403.jpg"},{"uniquekey":"eb1fde0ae5429e10d2dbf4a4444bd544","title":"个人定制游需求多元 物有所值怎么体现","date":"2017-08-11 08:25","category":"头条","author_name":"解放日报","url":"http://mini.eastday.com/mobile/170811082535244.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170811/20170811082535_27f2b130c96d6ce796cfe303c5d1a279_1_mwpm_03200403.jpg"},{"uniquekey":"9de73315fa167a9c4611f2e14c5aae57","title":"等解放军打进印度领土后，欢迎达赖发表和平宣言！","date":"2017-08-11 08:21","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170811082127590.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170811/20170811082127_0594bbd1df158c98503541252f6d2074_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20170811/20170811082127_0594bbd1df158c98503541252f6d2074_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20170811/20170811082127_0594bbd1df158c98503541252f6d2074_2_mwpm_03200403.jpg"},{"uniquekey":"811819586c00da8906da8fb49b5955e7","title":"八月份桃花运财运双喜临门的生肖","date":"2017-08-11 08:16","category":"头条","author_name":"美国神婆星座网","url":"http://mini.eastday.com/mobile/170811081650148.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170811/20170811081650_88df3b82c5a44bbfeee2f657e4d78424_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20170811/20170811081650_88df3b82c5a44bbfeee2f657e4d78424_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03.imgmini.eastday.com/mobile/20170811/20170811081650_88df3b82c5a44bbfeee2f657e4d78424_2_mwpm_03200403.jpg"},{"uniquekey":"d4f090a1377a7f13cd168a3ab672fcc5","title":"原北京军区司令员周衣冰中将逝世，享年95岁","date":"2017-08-11 08:14","category":"头条","author_name":"澎湃新闻网","url":"http://mini.eastday.com/mobile/170811081403815.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20170811/20170811081403_25bbd0ef027144e400d0d281ed2e61c3_1_mwpm_03200403.jpg"},{"uniquekey":"e57528812ee1906e1884b0875d2b7a79","title":"中国对马来西亚出售AR3远火传闻令新加坡紧张","date":"2017-08-11 08:06","category":"头条","author_name":"环球时报","url":"http://mini.eastday.com/mobile/170811080640425.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170811/20170811080640_7a8869eac03228b5d8d4e5a0a43ba532_1_mwpm_03200403.jpg"},{"uniquekey":"a0372f31a8e027a84b2e06168de7204e","title":"印防长称已吸取1962年教训 放话印军已足够强大","date":"2017-08-11 08:06","category":"头条","author_name":"环球时报","url":"http://mini.eastday.com/mobile/170811080636728.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170811/20170811080636_6e1a1d1e8ccadb448c8308f9686aa50e_1_mwpm_03200403.jpg"},{"uniquekey":"0dea4a05d20e7cffb8f0f9027771adeb","title":"日称我电子战飞机绕飞台湾 专家：合法、合理","date":"2017-08-11 07:56","category":"头条","author_name":"环球时报","url":"http://mini.eastday.com/mobile/170811075630709.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170811/20170811075630_611c1baabb065ecbd6f2a3dc9792682b_1_mwpm_03200403.jpg"},{"uniquekey":"ae4fe3f3fc3cef302dda29068c56ebeb","title":"印度外长访尼泊尔参加区域外长会，将与不丹外长举行双边会谈","date":"2017-08-11 07:53","category":"头条","author_name":"澎湃新闻网","url":"http://mini.eastday.com/mobile/170811075314395.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170811/20170811075314_c2af0e5e36baa35ca4456539ff1f191e_1_mwpm_03200403.jpg"},{"uniquekey":"b0d2371bb283bf6661df708c611a6cc2","title":"九寨沟地震：14名遇难人员名单身份确认","date":"2017-08-11 07:53","category":"头条","author_name":"四川新闻网","url":"http://mini.eastday.com/mobile/170811075305636.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170811/20170811075305_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03200403.jpg"},{"uniquekey":"a9a55a5774bb38aea9dec943ea8f93d3","title":"这个国家最讨厌大胸的女人：残忍割掉自己乳房 原因让人震惊不已","date":"2017-08-11 07:52","category":"头条","author_name":"映图影志","url":"http://mini.eastday.com/mobile/170811075212168.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170811/20170811_fc52c8c42bbcf087f172aae92965a894_cover_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20170811/20170811_98703c06da64d97488fe93b5effb8e20_cover_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20170811/20170811_86c3a4348bc2e60f3953c5065947624b_cover_mwpm_03200403.jpeg"},{"uniquekey":"3627fd7240c1349221b15339f1c44a7e","title":"\u201c怪物\u201d突然现身居民家中，趴在床上吓得屋主尖叫报警","date":"2017-08-11 07:50","category":"头条","author_name":"图看世界","url":"http://mini.eastday.com/mobile/170811075039782.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170811/20170811_ded1560e564031c2cb14a2e6fa426641_cover_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20170811/20170811_dc543e0068d120a0af75f47913e3eb83_cover_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20170811/20170811_77cb7faf39407a44a16f61043fd6762b_cover_mwpm_03200403.jpeg"},{"uniquekey":"d97a6686aee4b5f76154a731d593494f","title":"日本怎么了？疯狂采购美国进攻武器大肆扩军 对抗中国是一枕黄粱","date":"2017-08-11 07:49","category":"头条","author_name":"军评陈光文","url":"http://mini.eastday.com/mobile/170811074923021.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170811/20170811_1e86359e7a8582067e73ea6f17935127_cover_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20170811/20170811_d22e31c6c6880210000b70da5286a113_cover_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://03.imgmini.eastday.com/mobile/20170811/20170811_b7c1c71078c2d7989044daa8d96dad2d_cover_mwpm_03200403.jpeg"},{"uniquekey":"d44de91666efe99e8500528295e4807e","title":"亨通富贵！喜讯常在的3大生肖属相！","date":"2017-08-11 07:49","category":"头条","author_name":"吉名轩八字星座","url":"http://mini.eastday.com/mobile/170811074915976.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170811/20170811_a680f1ea7d312b47820747df9d6608ee_cover_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20170811/20170811_8bae77d07f30c07be9e0a8fa0c27dc2a_cover_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://04.imgmini.eastday.com/mobile/20170811/20170811_a50009db9dc13dce4f5b78e7e6891537_cover_mwpm_03200403.jpeg"},{"uniquekey":"d7ad8049f6dd16fb51a421d366435545","title":"俄罗斯漂亮美女街头随处可见 为什么结婚后\u201c速成大妈\u201d?","date":"2017-08-11 07:48","category":"头条","author_name":"三国迷醉","url":"http://mini.eastday.com/mobile/170811074817529.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20170811/20170811_5a6231a914e67652f1b2284557398be3_cover_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://02.imgmini.eastday.com/mobile/20170811/20170811_d0b2931946023fb1fa9b059dff9fb969_cover_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://02.imgmini.eastday.com/mobile/20170811/20170811_d91ee0095da753d55d4ab993466fab42_cover_mwpm_03200403.jpeg"},{"uniquekey":"39a31a3f4baecebb3fc17103a1ae471a","title":"新疆精河确保受灾群众入冬前全部住进富民安居房","date":"2017-08-11 07:42","category":"头条","author_name":"人民网","url":"http://mini.eastday.com/mobile/170811074240078.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170811/20170811074240_4d5cf62e009582f44eda2f4bfd662ea7_1_mwpm_03200403.jpg"},{"uniquekey":"ab46c4d86a20b674bed3759ccf17cf03","title":"成都90后女生拾金不昧 上海失主\u201c送\u201d她房被婉拒","date":"2017-08-11 07:41","category":"头条","author_name":"成都晚报","url":"http://mini.eastday.com/mobile/170811074109668.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170811/20170811074109_d245237ebe097a1a98603ec13cff319d_1_mwpm_03200403.jpg"},{"uniquekey":"7058ea2e280811de984f1ce80a436f91","title":"蒋介石当众被挨一脚不敢吱声，这牛人是谁？场面难堪的很","date":"2017-08-11 07:38","category":"头条","author_name":"传记人生","url":"http://mini.eastday.com/mobile/170811073834666.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170811/20170810_3acff1a1fcb3e087bd9d455a8560fab8_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20170811/20170810_a3134994a765e6d9854a062ac97be8a8_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06.imgmini.eastday.com/mobile/20170811/20170810_14b82dba9d7c59f45b3460e73d6b5b05_mwpm_03200403.jpeg"},{"uniquekey":"991d01c5fab8adadc7878e421c9c867b","title":"专访《印度对华战争》作者：印度错把中国当成他们的敌人","date":"2017-08-11 07:26","category":"头条","author_name":"环球时报","url":"http://mini.eastday.com/mobile/170811072619711.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170811/20170811072619_71df1afeeb6456df3567db29d2bf7361_1_mwpm_03200403.jpg"},{"uniquekey":"7f7c3c2016d290b0d3d985ceafe80ce9","title":"面馆雇俩机器人削面 一年多挣十几万","date":"2017-08-11 07:26","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170811072618877.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20170811/20170811072618_06e4fc6297c41d90d78ebc94ee88a216_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20170811/20170811072618_06e4fc6297c41d90d78ebc94ee88a216_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20170811/20170811072618_06e4fc6297c41d90d78ebc94ee88a216_1_mwpm_03200403.jpg"},{"uniquekey":"8a139bfaafeac730e68d3aa4d06ce1de","title":"印度冒出\u201c打赢中国\u201d狂言 专家：莫迪政府若顽固不化将成罪人","date":"2017-08-11 07:23","category":"头条","author_name":"解放网","url":"http://mini.eastday.com/mobile/170811072300169.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170811/20170811072300_bf19d0ad62c7c985fc0ca22790b0dfb5_1_mwpm_03200403.jpg"}]
         */

        private String stat;
        private List<DataBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Serializable {
            /**
             * uniquekey : a437e64c91c68a45a256cb6e92d6dc52
             * title : 《侠盗联盟》发“漫画”预告片林肯公园新歌作片尾
             * date : 2017-08-11 09:26
             * category : 头条
             * author_name : 新华网
             * url : http://mini.eastday.com/mobile/170811092601245.html
             * thumbnail_pic_s : http://04.imgmini.eastday.com/mobile/20170811/20170811092601_1e560cd750a1983d8bdddbb7b8d143e3_4_mwpm_03200403.jpg
             * thumbnail_pic_s02 : http://04.imgmini.eastday.com/mobile/20170811/20170811092601_1e560cd750a1983d8bdddbb7b8d143e3_2_mwpm_03200403.jpg
             * thumbnail_pic_s03 : http://04.imgmini.eastday.com/mobile/20170811/20170811092601_1e560cd750a1983d8bdddbb7b8d143e3_1_mwpm_03200403.jpg
             */

            private String uniquekey;
            private String title;
            private String date;
            private String category;
            private String author_name;
            private String url;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }

            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }

            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "uniquekey='" + uniquekey + '\'' +
                        ", title='" + title + '\'' +
                        ", date='" + date + '\'' +
                        ", category='" + category + '\'' +
                        ", author_name='" + author_name + '\'' +
                        ", url='" + url + '\'' +
                        ", thumbnail_pic_s='" + thumbnail_pic_s + '\'' +
                        ", thumbnail_pic_s02='" + thumbnail_pic_s02 + '\'' +
                        ", thumbnail_pic_s03='" + thumbnail_pic_s03 + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "stat='" + stat + '\'' +
                    ", data=" + data +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "reason='" + reason + '\'' +
                ", result=" + result +
                ", error_code=" + error_code +
                '}';
    }
}
