package com.frank.toolbardemo;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
public class Fragment_2 extends Fragment {
    private static final String ARG_POSITION = "position";
    private int position;

    /**
     * 将url对应的json格式数据，转化为我们所封装的NewsBean对象
     */
    private ListView listView;
    private static String URL = "http://www.imooc.com/api/teacher?type=4&num=30";

    public static Fragment_2 newInstance(int position) {
        Fragment_2 fragment_2 = new Fragment_2();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITION, position);
        fragment_2.setArguments(bundle);
        return fragment_2;
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_POSITION);
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(URL);
        View view = inflater.inflate(R.layout.news_list, null);
        listView = (ListView) view.findViewById(R.id.lv);
        return view;
    }


    private String readStream(InputStream is) {
        /**
         * 通过InputStream解析数据
         */
        InputStreamReader isr;
        String result = "";
        try {

            String line = "";
            isr = new InputStreamReader(is, "utf-8");//字节流转化为字符流
            BufferedReader br = new BufferedReader(isr);//传入缓冲流
            try {
                while ((line = br.readLine()) != null) {//按行读取
                    result += line;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    class MyAsyncTask extends AsyncTask<String, Void, List<NewsBean>> {
        /**
         * 网络异步访问
         */

        @Override
        protected List<NewsBean> doInBackground(String... params) {
            return getJsonData(params[0]);
        }//参数只有一个，就是URL

        @Override
        protected void onPostExecute(List<NewsBean> newsBean) {
            super.onPostExecute(newsBean);
            NewsAdapter adapter = new NewsAdapter(getActivity(), newsBean);//用getApplicationContext，没显示内容
            listView.setAdapter(adapter);
        }
    }

    private List<NewsBean> getJsonData(String url) {

        List<NewsBean> nlist = new ArrayList<>();
        try {
            //此句功能和url.openConnection.getInputStream()相同
            String jsonString = readStream(new URL(url).openStream());
            JSONObject jsonObject;
            NewsBean newsBean;
            try {
                jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    newsBean = new NewsBean();
                    newsBean.newsIconUrl = jsonObject.getString("picSmall");
                    newsBean.newsTitle = jsonObject.getString("name");
                    newsBean.newsContent = jsonObject.getString("description");
                    nlist.add(newsBean);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Log.i("Main", jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nlist;
    }

}
