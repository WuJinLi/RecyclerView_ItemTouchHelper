package com.wujinli.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private ItemTouchHelper mItemTouchHelper;
    private List<String> mItems = new ArrayList<>();
    private TextView tv_show;
    private RecyclerListAdapter adapter;
    private ItemTouchHelper.Callback callback;
    private Button btn_add, btn_clear_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        setListener();
    }


    /**
     * 初始化数据
     */
    private void initData() {
        mItems = getData(true);
    }

    /**
     * 初始化view
     */
    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.view);
        tv_show = (TextView) findViewById(R.id.tv_show);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_clear_add = (Button) findViewById(R.id.btn_clear_add);

        adapter = new RecyclerListAdapter(this, this, mItems);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        callback = new ItemTouchHelperCallBack(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
    }

    public void setListener() {
        btn_add.setOnClickListener(this);
        btn_clear_add.setOnClickListener(this);
    }

    /**
     * 数据源
     *
     * @return
     */
    public List<String> getData(Boolean isFirst) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (isFirst) {
                list.add(i + "初始化数据");
            } else {
                list.add(i + "新增数据");
            }
        }
        return list;
    }

    /**
     * 刷新数据
     *
     * @param mItems
     */
    public void refreshData(List<String> mItems, String flag) {
        /**
         * 展示拖拽后数据源的顺序
         */
        String str = "";
        if (!ListUtils.isEmpty(mItems)) {
            for (int i = 0; i < mItems.size(); i++) {
                str = str + mItems.get(i) + ",";
            }
            if (!TextUtils.isEmpty(flag)) {
                if ("1".equals(flag)) {
                    tv_show.setText("拖拽后数据顺序" + str);
                } else if ("0".equals(flag)) {
                    tv_show.setText("侧滑后数据顺序" + str);
                }
            } else {
                return;
            }
        } else {
            return;
        }
        str = "";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                adapter.addData(getData(false), false);
                break;
            case R.id.btn_clear_add:
                adapter.addData(getData(false), true);
                break;
            default:
                break;
        }
    }
}
