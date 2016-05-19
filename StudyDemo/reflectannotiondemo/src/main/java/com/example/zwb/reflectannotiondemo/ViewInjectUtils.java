package com.example.zwb.reflectannotiondemo;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/5/19
 ***************************************/
public class ViewInjectUtils {

    public static void init(Activity activity) {
        initContentView(activity);
        initView(activity);

    }

    private static void initContentView(Activity activity) {
        Class<? extends Activity> c = activity.getClass();
        ContentView contentView = c.getAnnotation(ContentView.class);
        Log.i("TAG", "======c.isAnnotationPresent========" + c.isAnnotationPresent(ContentView.class));
        if (contentView != null) {
            try {
                Method m = c.getMethod("setContentView", int.class);
                m.setAccessible(true);
                int layout = contentView.layout();
                Log.e("TAG", layout + "");
                m.invoke(activity, layout);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private static void initView(final Activity activity) {
        Class<? extends Activity> c = activity.getClass();
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            ViewInject viewInject = field.getAnnotation(ViewInject.class);
            Log.i("TAG", "======field.isAnnotationPresent========" + field.isAnnotationPresent(ViewInject.class));
            if (viewInject != null) {
                int id = viewInject.id();
                Log.e("TAG", id + "");
                if (id != -1) {
                    try {
                        Method m = c.getMethod("findViewById", int.class);
                        m.setAccessible(true);
                        final Object o = m.invoke(activity, id);
                        field.setAccessible(true);
                        field.set(activity, o);

                        final Method click = c.getDeclaredMethod("onMyClick", View.class);
                        if (click.isAnnotationPresent(OnClick.class)) {
                            OnClick onClick = click.getAnnotation(OnClick.class);
                            for (int td : onClick.value()) {
                                if(td == id) {
                                    ((View) o).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            try {
                                                click.setAccessible(true);
                                                click.invoke(activity, o);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
