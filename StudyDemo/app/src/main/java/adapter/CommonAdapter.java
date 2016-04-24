package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by zwb
 * Description 万能适配器
 * Date 16/4/24.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    private Context mContext;
    private List<T> ts;
    private int layoutId;
    public CommonAdapter(Context context,List<T> ts,int layoutId){
        mContext = context;
        this.ts = ts;
        this.layoutId = layoutId;
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
        ViewHolder holder = ViewHolder.getHolder(layoutId,mContext,viewGroup,view);
        convert(holder,ts.get(i));
        return holder.getConvertView();
    }
    public abstract void convert(ViewHolder holder,T t);
}
