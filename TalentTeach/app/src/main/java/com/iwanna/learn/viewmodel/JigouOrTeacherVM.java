package com.iwanna.learn.viewmodel;

import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.JigouIntroduce;
import com.iwanna.learn.model.Teacher;
import com.iwanna.learn.view.activity.JigouOrTeacherActivity;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.http.net.NetRequest;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

/**
 * Created by zwb
 * Description
 * Date 16/9/1.
 */
public class JigouOrTeacherVM extends AbstractViewMode<JigouOrTeacherActivity> {

    private NetRequest jigouNR = null;
    private NetRequest teacherNR = null;

    public void getData(int id,boolean isTeacher){
        jigouNR = Net.get(this).jigouIntroduce(id,isTeacher);
    }

    public void getTeacherData(int id,boolean isTeacher){
        teacherNR = Net.get(this).teacherIntroduce(id,isTeacher);
    }
    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
        if(getView() == null){
            return;
        }
        getView().dissMissDialog();
        if(netEvent.whatEqual(jigouNR)) {
            JigouIntroduce jigouOrTeacher = (JigouIntroduce) netEvent.getResult();
            getView().setData(jigouOrTeacher);
        }else if (netEvent.whatEqual(teacherNR)){
            Teacher teacher = (Teacher) netEvent.getResult();
            getView().setData(teacher);
        }
    }

    @Override
    public boolean netfailed(NetEvent netEvent) {
        if(getView() != null){
            getView().dissMissDialog();
        }
        return super.netfailed(netEvent);
    }
}
