package com.example.mytreeview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by sxj on 2019/9/24.
 */
public class ItemView extends RelativeLayout {
    private View mView;
    private TextView rb_name;
    private ImageView iv_anmi;
    private int value;
    /**
     * 是否选中
    * */
    public boolean isSelected=false;

    public ItemView(Context context) {
        this(context,null);
    }

    public ItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mView= LayoutInflater.from(context).inflate(R.layout.item_user,this);
//        mView.setId(generateViewId());
        rb_name = (TextView)mView.findViewById(R.id.rb_name);
        iv_anmi = (ImageView)mView.findViewById(R.id.iv_anmi);
    }


    public void setData(String title,int value){
        rb_name.setText(title);
        this.value=value;
    }
    public void setTextColor(int color){
        isSelected=!isSelected;
        rb_name.setTextColor(color);
    }
    public int getValue(){
        return value;
    }
    /**
     * @param isExpend 是否展开 true表示展开
     *
     * */
    public void setLogoAnim(boolean isExpend){
        if(!isExpend){
            iv_anmi.animate().setDuration(500).rotation(180).start();
        }else {
            iv_anmi.animate().setDuration(500).rotation(0).start();
        }

    }
    public void setLogoVisible(int visible){
        iv_anmi.setVisibility(visible);
    }




}
