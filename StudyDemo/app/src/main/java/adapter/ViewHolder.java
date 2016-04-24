package adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zwb
 * Description
 * Date 16/4/24.
 */
public class ViewHolder {
    private View mConvertView;
    private SparseArray<View> views;
    public ViewHolder(int layoutId, Context context, ViewGroup parent){
        mConvertView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        views = new SparseArray<>();
        mConvertView.setTag(this);
    }
    public static ViewHolder getHolder(int layoutId, Context context, ViewGroup parent,View convertView){
        if(convertView == null){
            return new ViewHolder(layoutId,context,parent);
        }else{
            return (ViewHolder)convertView.getTag();
        }
    }
    public <T extends View> T getView(int id){
        View view = views.get(id);
        if(view == null){
            view = mConvertView.findViewById(id);
            Log.i("info","=======findViewById=======");
            views.put(id,view);
        }
        return (T)view;
    }

    public void setText(int id,String text){
        TextView textView = getView(id);
        textView.setText(text);
    }
    public void setImageResource(int id,int viewId){
        ImageView imageView = getView(id);
        imageView.setImageResource(viewId);
    }
    public View getConvertView(){
        return this.mConvertView;
    }
}
