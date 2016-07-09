package com.frank.toolbardemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView listView;
    private String[] lvs = {"DEFAULT", "RED", "BLUE", "MATERIAL GREY"};
    private ImageView yoona;
    private String[] titles = new String[]{"风景图集", "搞笑段子", "自拍达人", "煮酒论史", "莲蓬鬼话", "读史明智", "体育娱乐", "轻松一刻"};
    SlidingTabLayout slidingTabLayout;
    ViewPager viewPager;
    private List<ItemBean> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.real_main);
        toolbar = (Toolbar) findViewById(R.id.tl_custom);
        drawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        listView = (ListView) findViewById(R.id.lv_left_menu);
        //yoona = (ImageView) findViewById(R.id.yoona);
        toolbar.setTitle("Yoona");
       // toolbar.setSubtitle("林允儿");

           /*
           设置toolbar文字颜色
            */
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.setSubtitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);//设置返回键可以用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//返回键有打开关闭功能
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // yoona.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //yoona.setVisibility(View.VISIBLE);
            }
        };
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);
       /* arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, lvs);
        listView.setAdapter(arrayAdapter);*/
        mList = new ArrayList<>();
        mList.add(new ItemBean(R.drawable.self, "GREEN"));
        mList.add(new ItemBean(R.drawable.self, "RED"));
        mList.add(new ItemBean(R.drawable.self, "BLUE"));
        mList.add(new ItemBean(R.drawable.self, "GREY"));
        listView.setAdapter(new ListViewAdapter(MainActivity.this, mList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        listView.setBackgroundColor(getResources().getColor(R.color.material_deep_teal_500));
                        toolbar.setBackgroundColor(getResources().getColor(R.color.material_deep_teal_500));
                        slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.material_deep_teal_500));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        listView.setBackgroundColor(getResources().getColor(R.color.red));
                        toolbar.setBackgroundColor(getResources().getColor(R.color.red));
                        slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.red));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        listView.setBackgroundColor(getResources().getColor(R.color.blue));
                        toolbar.setBackgroundColor(getResources().getColor(R.color.blue));
                        slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.blue));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        listView.setBackgroundColor(getResources().getColor(R.color.material_blue_grey_800));
                        toolbar.setBackgroundColor(getResources().getColor(R.color.material_blue_grey_800));
                        slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.material_blue_grey_800));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });

        /*
        下面是关于ViewPager和SlidingTabLayout
         */
        viewPager = (ViewPager) findViewById(R.id.id_vp);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), titles));
        slidingTabLayout.setViewPager(viewPager);
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.WHITE;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.action_share));
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        mShareActionProvider.setShareIntent(shareIntent);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                //Toast.makeText(MainActivity.this, "关于被点击了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_feedback:
                Intent intent1 = new Intent(MainActivity.this, FeedbackActivity.class);
                startActivity(intent1);
                //Toast.makeText(MainActivity.this, "反馈被点击了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                Toast.makeText(MainActivity.this, "设置被点击了", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

