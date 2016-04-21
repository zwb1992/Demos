package adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zwb on 16/4/21.
 */
public class ViewHolder {
    private Context context;
    private SparseArray<View> views;
    private int position;
    private View mConverView;
    public ViewHolder(Context context,int position,View converView,ViewGroup viewGroup,int layoutId){
        this.context = context;
        this.position = position;
        views = new SparseArray<View>();
        mConverView = View.inflate(context,layoutId,viewGroup);
        mConverView.setTag(this);
    }

    public static ViewHolder getHolder(Context context, int position, View converView, ViewGroup viewGroup, int layoutId){
        if(converView == null){
            return new ViewHolder(context,position,converView,viewGroup,layoutId);
        }else {
            return (ViewHolder)converView.getTag();
        }
    }

    public <T extends View> T getView(int id){
        View view = views.get(id);
        if(view == null){
            view = mConverView.findViewById(id);
            views.put(id,view);
        }
        return (T)view;
    }
    /**以下方法为额外封装的方法，只是简单几个，以后可以慢慢完善*/
    /**
     * 设置TextView的内容
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public View getConverView(){
        return  mConverView;
    }
}
