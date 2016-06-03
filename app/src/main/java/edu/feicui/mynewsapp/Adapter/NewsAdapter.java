package edu.feicui.mynewsapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.mynewsapp.R;
import edu.feicui.mynewsapp.bean.News;

/**
 * Created by Administrator on 2016/6/3.
 */
public class NewsAdapter extends BaseAdapter{
    private static final String TAG = "NewsListActivity";
    private List<News.DataBean> mData=new ArrayList<>();
    private LayoutInflater mInflater;

    public NewsAdapter(Context context) {
        mInflater=LayoutInflater.from(context);
    }

    public void addData( List<News.DataBean> bean){
        mData.addAll(bean);
    }

    @Override
    public int getCount() {
        if(mData!=null){
            Log.d(TAG, "getCount: "+mData.size());
            return mData.size();
        }
        return 0;
    }

    @Override
    public News.DataBean getItem(int position) {
        if(mData!=null){
            return mData.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            Log.d(TAG, "getView: "+1111);
            convertView=mInflater.inflate(R.layout.item_news_list,null);
            viewHolder=new ViewHolder();
            viewHolder.icon= (ImageView) convertView.findViewById(R.id.iv_newspic);
            viewHolder.title= (TextView) convertView.findViewById(R.id.tv_newstitle);
            viewHolder.text= (TextView) convertView.findViewById(R.id.tv_newstext);
            convertView.setTag(viewHolder);
        }else{
            Log.d(TAG, "getView: "+22222222);
            viewHolder= (ViewHolder) convertView.getTag();
        }
        News.DataBean bean=mData.get(position);
        Log.d(TAG, "getView: "+bean.getSummary()+bean.getTitle());
        viewHolder.icon.setImageResource(R.drawable.ic_launcher);
        viewHolder.title.setText(bean.getTitle());
        viewHolder.text.setText(bean.getSummary());
        return convertView;
    }

    private class ViewHolder {
        ImageView icon;
        TextView title;
        TextView text;
    }
}
