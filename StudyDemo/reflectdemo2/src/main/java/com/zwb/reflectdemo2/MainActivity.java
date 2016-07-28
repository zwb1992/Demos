package com.zwb.reflectdemo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void reflect(View view) {
        try {

            Class c;
//        c = Class.forName("Test");
            c = Test.class;
//        Test test = new Test();
//        c = test.getClass();

//            Test t = (Test) c.newInstance();
//            Constructor<Test> constructor = c.getConstructor();//获取无参的构造函数
//            Test t = constructor.newInstance();
            Constructor<Test> constructor = c.getConstructor(String.class,int.class);//获取有参的构造函数
            Test t = constructor.newInstance("test", 1);


            Field[] fields = c.getDeclaredFields();//获取属性集合
            Field field = c.getDeclaredField("name");//获取特定的属性
            Test t1 = (Test)c.newInstance();
            field.setAccessible(true);//打破封装性，实现属性赋值
            field.set(t1, "test");//赋值
            System.out.print(t1.getName());
            //获取属性的修饰以及类型
            Modifier.toString(field.getModifiers());//private
            field.getGenericType();//String

            Method method = c.getDeclaredMethod("print",String.class);
            method.invoke(t1,"反射获取的方法");//调用真实对象t1的print方法去执行
            Method method1 = c.getDeclaredMethod("print1",String.class);
            method1.invoke(null,"反射获取的Static方法");//调用Test的静态print1方法去执行

            //反射与数组
            Object o = Array.newInstance(Integer.TYPE,10);//创造一个长度为10的一维int数组
            int[] ints = new int[]{5,8};
            Object array = Array.newInstance(Integer.TYPE,ints);//返回一个二维数组
            System.out.print("====二位数组====" + array);
            array = Array.get(array,3);//返回索引为三的一维数组
            System.out.print("====一位数组====" + array);
            Array.set(array, 3, 12);//设置索引为3的值是12
            System.out.print(Array.get(array, 3) + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
