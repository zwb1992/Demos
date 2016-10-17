package com.iwanna.learn.viewmodel;

import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.AgeFenlei;
import com.iwanna.learn.model.BanXinFenlei;
import com.iwanna.learn.model.Course;
import com.iwanna.learn.model.CourseFenlei;
import com.iwanna.learn.view.fragment.FindFragment;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.http.net.NetRequest;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

import java.util.List;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/13
 ***************************************/
public class FindFragmentVM extends AbstractViewMode<FindFragment> {

    private NetRequest getCoursNr = null;
    private NetRequest fenleiNr = null;
    private NetRequest banxingNr = null;
    private NetRequest ageNr = null;

    public void getCours(String fenlei,String banxin,String age){
        getCoursNr = Net.get(this).faxian(fenlei,banxin,age);
    }

    public void fenlei(){
        fenleiNr = Net.get(this).courseFenlei();
    }

    public void banxing(){
        banxingNr = Net.get(this).banxinFenlei();
    }

    public void age(){
        ageNr = Net.get(this).ageFenlei();
    }

    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
        getView().dissMissDialog();
        if(netEvent.whatEqual(getCoursNr)) {
            List<Course> courseList = (List<Course>) netEvent.getResult();
            getView().setData(courseList);
        }else if(netEvent.whatEqual(fenleiNr)){
            List<CourseFenlei> courseFenleiList = (List<CourseFenlei>)netEvent.getResult();
            getView().setCoursFenlei(courseFenleiList);
        }else if(netEvent.whatEqual(banxingNr)){
            List<BanXinFenlei> banXinFenleiList = (List<BanXinFenlei>)netEvent.getResult();
            getView().setBanxinFenlei(banXinFenleiList);
        }else if (netEvent.whatEqual(ageNr)){
            List<AgeFenlei> ageFenleiList = (List<AgeFenlei>)netEvent.getResult();
            getView().setAgeFenlei(ageFenleiList);
        }
    }

    @Override
    public boolean netfailed(NetEvent netEvent) {
        getView().dissMissDialog();
        getView().getRlRefresh().endRefreshing();
        return super.netfailed(netEvent);
    }
}
