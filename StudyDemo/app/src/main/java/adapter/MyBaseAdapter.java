package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by zwb
 * Description
 * Date 16/4/22.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
    private List<T> ts;
    private int layoutId;
    private Context context;
    public MyBaseAdapter(int layoutId,Context context,List<T> ts){
        this.layoutId = layoutId;
        this.ts = ts;
        this.context = context;
    }
    @Override
    public int getCount() {
        return ts.size();
    }

    @Override
    public Object getItem(int i) {
        return ts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.getHolder(context,i,view,viewGroup,layoutId);
        convert(holder,ts.get(i));
        return holder.getConverView();
    }
    public abstract void convert(ViewHolder holder,T t);
}
