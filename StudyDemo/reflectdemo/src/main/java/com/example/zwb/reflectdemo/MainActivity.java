package com.example.zwb.reflectdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * java的反射机制
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.bt)
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt)
    public void onClick() {
        try {
//            Class rd = Class.forName("Reflectd");
            Class rd = ReflectD.class;
            Field[] f = rd.getDeclaredFields();
            //定义可变长的字符串，用来存储属性
            StringBuffer sb = new StringBuffer();
            //通过追加的方法，将每个属性拼接到此字符串中
            //最外边的public定义
            sb.append(Modifier.toString(rd.getModifiers()) + " class " + rd.getSimpleName() +"{\n");
            //里边的每一个属性
            for(Field field:f){
                sb.append("\t");//空格
                sb.append(Modifier.toString(field.getModifiers())+" ");//获得属性的修饰符，例如public，static等等
                sb.append(field.getType().getSimpleName() + " ");//属性的类型的名字
                sb.append(field.getName()+";\n");//属性的名字+回车
            }
            sb.append("}");
            System.out.println(sb);

            Field idF = rd.getDeclaredField("id");
            Object o = rd.newInstance();
            //打破封装
            idF.setAccessible(true); //使用反射机制可以打破封装性，导致了java对象的属性不安全。
            idF.set(o, 110);
            Log.i("info", "利用反射给属性赋值-======" + o);
            Constructor constructor = rd.getDeclaredConstructor(String.class, int.class);
            o = constructor.newInstance("周",20);
            Log.i("info", "利用反射构造函数生成对象-======" + o);

            Method setN = rd.getDeclaredMethod("setName",String.class);
            setN.invoke(o,"周伟斌");
            Log.i("info", "利用反射获取对象执行的方法-======" + o);

        } catch (Exception e) {
            e.printStackTrace();
            Log.i("info", "执行反射失败！");
        }
    }
}
