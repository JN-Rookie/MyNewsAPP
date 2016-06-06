package edu.feicui.mynewsapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.feicui.mynewsapp.R;
import edu.feicui.mynewsapp.bean.News;
import edu.feicui.mynewsapp.utils.GetIntnetImage;

/**
 * Created by Administrator on 2016/6/3.
 */
public class NewsAdapter extends BaseAdapter {
    private static final String              TAG   = "NewsListActivity";
    private              List<News.DataBean> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private ImageLoader mImageLoader;

    public NewsAdapter(Context context,ImageLoader imageLoader) {
        mImageLoader=imageLoader;
        mInflater = LayoutInflater.from(context);
    }

    public void addData(List<News.DataBean> bean) {
        mData.addAll(bean);
    }

    @Override
    public int getCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    @Override
    public News.DataBean getItem(int position) {
        if (mData != null) {
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_news_list, null);
            viewHolder = new ViewHolder();
            viewHolder.icon = (NetworkImageView) convertView.findViewById(R.id.iv_newspic);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_newstitle);
            viewHolder.text = (TextView) convertView.findViewById(R.id.tv_newstext);
            viewHolder.date= (TextView) convertView.findViewById(R.id.tv_newsdate);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        News.DataBean bean = mData.get(position);
        viewHolder.icon.setDefaultImageResId(R.drawable.ic_launcher);
        viewHolder.icon.setErrorImageResId(R.drawable.failed_image);
        viewHolder.icon.setImageUrl(bean.getIcon(), mImageLoader);
        viewHolder.title.setText(bean.getTitle());
        viewHolder.text.setText(bean.getSummary());
        viewHolder.date.setText(bean.getStamp());
        return convertView;
    }

    private class ViewHolder {
        NetworkImageView icon;
        TextView         title;
        TextView         text;
        TextView date;
    }
}
