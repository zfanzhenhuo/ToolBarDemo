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
 * Created by Administrator on 2016/7/7 0007.
 */
public class ListViewAdapter extends BaseAdapter {
    public List<ItemBean> beanList;
    public LayoutInflater inflater;

    public ListViewAdapter(Context context, List<ItemBean> beanList) {
        this.beanList = beanList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.listlayout,null);
            viewHolder.icon= (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.color= (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        ItemBean itemBean=beanList.get(position);
        viewHolder.icon.setImageResource(itemBean.IconID);
        viewHolder.color.setText(itemBean.colorID);
        return convertView;
    }

    class ViewHolder {
        private ImageView icon;
        private TextView color;
    }
}
