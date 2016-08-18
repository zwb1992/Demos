package com.example.zwb.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * RxJava的初步使用技巧
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
//        firstType();
//        secondeType();
//        thirdType();
//        forthType();

    }

    private void firstType() {
        Integer[] ints = new Integer[]{1, 3, 5, 8};

        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i("info", "======onCompleted========");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("info", "=======onError=======" + e.toString());
            }

            @Override
            public void onNext(Integer i) {
                Log.i("info", "======onNext=======" + i);
            }
        };
        Observable.from(ints).subscribe(subscriber);
    }

    private void secondeType() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("HelloWorld");
                subscriber.onCompleted();
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i("info", "======onCompleted========");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("info", "=======onError=======" + e.toString());
            }

            @Override
            public void onNext(String s) {
                Log.i("info", "======onNext=======" + s);
            }
        });
    }

    private void thirdType() {
        String[] str = new String[]{"STR", "HELlOworlde", "Good"};
        Observable.from(str).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s.toLowerCase();
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i("info", "=====全部转化成小写======" + s);
            }
        });
    }

    private void forthType() {
        Student s1 = new Student();
        s1.setName("老王");
        List<Course> l1 = new ArrayList<>();
        Course c1 = new Course("语文");
        Course c2 = new Course("数学");
        Course c3 = new Course("物理");
        l1.add(c1);
        l1.add(c2);
        l1.add(c3);
        s1.setList(l1);

        Student s2 = new Student();
        s2.setName("老周");
        List<Course> l2 = new ArrayList<>();
        Course c4 = new Course("java");
        Course c5 = new Course("android");
        l2.add(c4);
        l2.add(c5);
        s2.setList(l2);

        Subscription subscription = Observable.just(s1, s2)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        Log.i("info", "==Observable<Course> call====student======" + student.getName());
                        return Observable.from(student.getList());
                    }
                })
                .subscribe(new Action1<Course>() {
                    @Override
                    public void call(Course course) {
                        Log.i("info", "==call====course======" + course.getName());
                    }
                });
        Log.i("info", "=====subscription.isUnsubscribed()=====" + subscription.isUnsubscribed());
    }
}
