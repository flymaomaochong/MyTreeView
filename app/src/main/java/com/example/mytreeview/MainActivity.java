package com.example.mytreeview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private LinearLayout radioList;
    private final int PADDING = 80;
    private int selectedTag;

    /* 转义之前的数据
    { "code": 0, "data": [ { "children": [ { "children": [ { "pId": "2", "title": "部门A-1", "value": 13 }, { "pId": "2", "title": "部门A-2", "value": 23 }, { "pId": "2", "title": "部门A-3", "value": 24 }, { "pId": "2", "title": "部门A-4", "value": 25 }, { "pId": "2", "title": "部门A-5", "value": 26 }, { "pId": "2", "title": "部门A-6", "value": 27 }, { "pId": "2", "title": "部门A-7", "value": 28 }, { "pId": "2", "title": "部门A-8", "value": 29 }, { "pId": "2", "title": "部门A-9", "value": 31 }, { "pId": "2", "title": "部门A-10", "value": 32 }, { "pId": "2", "title": "部门A-11", "value": 74 } ], "pId": "1", "title": "公司A", "value": 2 }, { "children": [ { "pId": "3", "title": "部门B-1", "value": 14 }, { "pId": "3", "title": "部门B-2", "value": 30 }, { "pId": "3", "title": "部门B-3", "value": 40 }, { "pId": "3", "title": "部门B-4", "value": 41 }, { "pId": "3", "title": "部门B-5", "value": 42 }, { "pId": "3", "title": "部门B-6", "value": 43 }, { "pId": "3", "title": "部门B-7", "value": 44 }, { "pId": "3", "title": "部门B-8", "value": 46 }, { "pId": "3", "title": "部门B-9", "value": 47 }, { "pId": "3", "title": "部门B-10", "value": 48 }, { "pId": "3", "title": "部门B-11", "value": 49 }, { "pId": "3", "title": "部门B-12", "value": 50 } ], "pId": "1", "title": "公司B", "value": 3 }, { "children": [ { "pId": "4", "title": "部门C-1", "value": 19 }, { "pId": "4", "title": "部门C-1", "value": 45 } ], "pId": "1", "title": "公司C", "value": 4 }, { "children": [ { "pId": "5", "title": "部门D-1", "value": 20 } ], "pId": "1", "title": "公司D", "value": 5 }, { "children": [ { "pId": "6", "title": "部门E-1", "value": 21 } ], "pId": "1", "title": "公司E", "value": 6 }, { "pId": "1", "title": "公司F", "value": 7 }, { "pId": "1", "title": "公司G", "value": 8 }, { "pId": "1", "title": "公司H", "value": 9 }, { "pId": "1", "title": "公司I", "value": 22 }, { "children": [ { "children": [ { "pId": "34", "title": "分部J-1-1", "value": 36 }, { "pId": "34", "title": "分部J-1-2", "value": 38 } ], "pId": "33", "title": "部门J-1", "value": 34 }, { "children": [ { "children": [ { "pId": "37", "title": "小组J-2-1-1", "value": 81 } ], "pId": "35", "title": "分部J-2-1", "value": 37 }, { "pId": "35", "title": "分部J-2-2", "value": 82 } ], "pId": "33", "title": "部门J-2", "value": 35 }, { "pId": "33", "title": "部门J-3", "value": 83 } ], "pId": "1", "title": "公司J", "value": 33 }, { "pId": "1", "title": "公司K", "value": 39 }, { "pId": "1", "title": "公司L", "value": 80 } ], "title": "GM管理", "value": 1 } ], "msg": "成功" }
     */
    /*
     * 转义之后的数据
     * */
    private String data = "{ \"code\": 0, \"data\": [ { \"children\": [ { \"children\": [ { \"pId\": \"2\", \"title\": \"部门A-1\", \"value\": 13 }, { \"pId\": \"2\", \"title\": \"部门A-2\", \"value\": 23 }, { \"pId\": \"2\", \"title\": \"部门A-3\", \"value\": 24 }, { \"pId\": \"2\", \"title\": \"部门A-4\", \"value\": 25 }, { \"pId\": \"2\", \"title\": \"部门A-5\", \"value\": 26 }, { \"pId\": \"2\", \"title\": \"部门A-6\", \"value\": 27 }, { \"pId\": \"2\", \"title\": \"部门A-7\", \"value\": 28 }, { \"pId\": \"2\", \"title\": \"部门A-8\", \"value\": 29 }, { \"pId\": \"2\", \"title\": \"部门A-9\", \"value\": 31 }, { \"pId\": \"2\", \"title\": \"部门A-10\", \"value\": 32 }, { \"pId\": \"2\", \"title\": \"部门A-11\", \"value\": 74 } ], \"pId\": \"1\", \"title\": \"公司A\", \"value\": 2 }, { \"children\": [ { \"pId\": \"3\", \"title\": \"部门B-1\", \"value\": 14 }, { \"pId\": \"3\", \"title\": \"部门B-2\", \"value\": 30 }, { \"pId\": \"3\", \"title\": \"部门B-3\", \"value\": 40 }, { \"pId\": \"3\", \"title\": \"部门B-4\", \"value\": 41 }, { \"pId\": \"3\", \"title\": \"部门B-5\", \"value\": 42 }, { \"pId\": \"3\", \"title\": \"部门B-6\", \"value\": 43 }, { \"pId\": \"3\", \"title\": \"部门B-7\", \"value\": 44 }, { \"pId\": \"3\", \"title\": \"部门B-8\", \"value\": 46 }, { \"pId\": \"3\", \"title\": \"部门B-9\", \"value\": 47 }, { \"pId\": \"3\", \"title\": \"部门B-10\", \"value\": 48 }, { \"pId\": \"3\", \"title\": \"部门B-11\", \"value\": 49 }, { \"pId\": \"3\", \"title\": \"部门B-12\", \"value\": 50 } ], \"pId\": \"1\", \"title\": \"公司B\", \"value\": 3 }, { \"children\": [ { \"pId\": \"4\", \"title\": \"部门C-1\", \"value\": 19 }, { \"pId\": \"4\", \"title\": \"部门C-1\", \"value\": 45 } ], \"pId\": \"1\", \"title\": \"公司C\", \"value\": 4 }, { \"children\": [ { \"pId\": \"5\", \"title\": \"部门D-1\", \"value\": 20 } ], \"pId\": \"1\", \"title\": \"公司D\", \"value\": 5 }, { \"children\": [ { \"pId\": \"6\", \"title\": \"部门E-1\", \"value\": 21 } ], \"pId\": \"1\", \"title\": \"公司E\", \"value\": 6 }, { \"pId\": \"1\", \"title\": \"公司F\", \"value\": 7 }, { \"pId\": \"1\", \"title\": \"公司G\", \"value\": 8 }, { \"pId\": \"1\", \"title\": \"公司H\", \"value\": 9 }, { \"pId\": \"1\", \"title\": \"公司I\", \"value\": 22 }, { \"children\": [ { \"children\": [ { \"pId\": \"34\", \"title\": \"分部J-1-1\", \"value\": 36 }, { \"pId\": \"34\", \"title\": \"分部J-1-2\", \"value\": 38 } ], \"pId\": \"33\", \"title\": \"部门J-1\", \"value\": 34 }, { \"children\": [ { \"children\": [ { \"pId\": \"37\", \"title\": \"小组J-2-1-1\", \"value\": 81 } ], \"pId\": \"35\", \"title\": \"分部J-2-1\", \"value\": 37 }, { \"pId\": \"35\", \"title\": \"分部J-2-2\", \"value\": 82 } ], \"pId\": \"33\", \"title\": \"部门J-2\", \"value\": 35 }, { \"pId\": \"33\", \"title\": \"部门J-3\", \"value\": 83 } ], \"pId\": \"1\", \"title\": \"公司J\", \"value\": 33 }, { \"pId\": \"1\", \"title\": \"公司K\", \"value\": 39 }, { \"pId\": \"1\", \"title\": \"公司L\", \"value\": 80 } ], \"title\": \"GM管理\", \"value\": 1 } ], \"msg\": \"成功\" }";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioList = (LinearLayout) findViewById(R.id.ll_content);
        setData(data);
        radioList.getChildAt(0).setVisibility(View.VISIBLE);

    }

    public void setData(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonObject1 = jsonObject.getJSONArray("data");
//            JSONArray jsonObject2 = jsonObject1.getJSONArray("children");
            parseJson(radioList, "data", (JSONObject) jsonObject1.get(0));
        } catch (Exception ex) {
            Log.i("输出的数据", ex.toString());
        }
    }

    /**
     * 手动遍历解析数据，并添加布局
     */
    private void parseJson(final ViewGroup viewGroup, String tag, JSONObject jsonObject) throws Exception {
        if (jsonObject.has("children")) {
            JSONArray jsonArray = jsonObject.getJSONArray("children");
            final LinearLayout linearLayout = new LinearLayout(getBaseContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.setPadding(PADDING, 0, 0, 0);
            viewGroup.addView(linearLayout);
            linearLayout.setVisibility(View.GONE);
            for (int i = 0; i < jsonArray.length(); i++) {
                final ItemView itemView = new ItemView(getBaseContext());
                JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
                Log.i("输出的数据哈哈", jsonObject2.getString("title"));
                linearLayout.addView(itemView);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = ((ViewGroup) itemView.getParent()).indexOfChild(itemView);
                        selectedTag = itemView.getValue();
                        //实现单选
                        setState((ViewGroup) radioList.getChildAt(0));
//修改字体颜色
                        if (linearLayout.getChildAt(position + 1) instanceof LinearLayout) {
                            int vis = linearLayout.getChildAt(position + 1).getVisibility();
                            if (vis == View.GONE) {
                                itemView.setLogoAnim(false);
                                linearLayout.getChildAt(position + 1).setVisibility(View.VISIBLE);
                            } else {
                                itemView.setLogoAnim(true);
                                linearLayout.getChildAt(position + 1).setVisibility(View.GONE);
                            }
                        }
                    }
                });
                itemView.setData(jsonObject2.getString("title"), jsonObject2.getInt("value"));
                if (jsonObject2.has("children")) {
                    parseJson(linearLayout, jsonObject2.getString("value"), jsonObject2);
                } else {
                    itemView.setLogoVisible(View.GONE);
                }
            }
        } else {//没有下一级
        }
    }


    /**
     * 选中状态，改变字体颜色
     */
    private void setState(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View selectView = viewGroup.getChildAt(i);
            if (selectView instanceof ItemView) {
                ((ItemView) selectView).setTextColor(((ItemView) selectView).getValue() == selectedTag ? getResources().getColor(R.color.blue) : getResources().getColor(R.color.black));
                if (i + 1 < viewGroup.getChildCount() && viewGroup.getChildAt(i + 1) instanceof LinearLayout) {
                    setState((ViewGroup) viewGroup.getChildAt(i + 1));
                }
            }
        }
    }
}
