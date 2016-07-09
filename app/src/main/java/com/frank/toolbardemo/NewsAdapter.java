package com.frank.toolbardemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28 0028.
 */

public class NewsAdapter extends BaseAdapter{
    private List<NewsBean> mList;
    private LayoutInflater mInflater;
    private MyImageLoader myImageLoader;
    public int mStart, mEnd;
    public static String[] URLS;

    public NewsAdapter(Context context, List<NewsBean> data) {
        mList = data;
        mInflater = LayoutInflater.from(context);
        myImageLoader=new MyImageLoader();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //文艺式BaseAdapter写法 converView为缓存
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_1, null);
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.iv);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv1);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ivIcon.setImageResource(R.mipmap.ic_launcher);
        String url = mList.get(position).newsIconUrl;
        viewHolder.ivIcon.setTag(url);
        //方法一
       // new MyImageLoader().showImageByThread(viewHolder.ivIcon,url);
        //方法二
        myImageLoader.showImageByAsyncTask(viewHolder.ivIcon, url);
        viewHolder.tvTitle.setText(mList.get(position).newsTitle);
        viewHolder.tvContent.setText(mList.get(position).newsContent);

        return convertView;
    }

    class ViewHolder {
        public TextView tvTitle, tvContent;
        public ImageView ivIcon;
    }

}
