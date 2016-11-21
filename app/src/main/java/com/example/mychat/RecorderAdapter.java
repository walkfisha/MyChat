package com.example.mychat;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Description  语音消息数据适配器
 * Created by AMOBBS on 2016/11/16.
 */

public class RecorderAdapter extends ArrayAdapter<Recorder> {

    private Boolean isSend = true;
    private Context mContext;
    private List<Recorder> mDatas;
    //item的最小宽度
    private int mMinWidth;
    //item的最大宽度
    private int mMaxWidth;
    private LayoutInflater mInflater;

    public RecorderAdapter(Context context, List<Recorder> datas) {
        super(context, -1, datas);

        mContext = context;
        mDatas = datas;

        //获取屏幕的宽度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        //最大宽度为屏幕宽度的百分之七十
        mMaxWidth = (int) (outMetrics.widthPixels * 0.7f);
        //最大宽度为屏幕宽度的百分之十五
        mMinWidth = (int) (outMetrics.widthPixels * 0.15f);
        mInflater = LayoutInflater.from(context);
    }




    final class ViewHolder {
        // 显示时间
        TextView seconds;
        //控件Item显示的长度
        View length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            if (!isSend){
                convertView = mInflater.inflate(R.layout.send_item_recoder, null);
            }else{
                convertView = mInflater.inflate(R.layout.received_item_recoder,null);
            }
            holder = new ViewHolder();
            holder.seconds = (TextView) convertView.findViewById(R.id.recoder_time);
            holder.length = convertView.findViewById(R.id.recoder_lenght);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.seconds.setText(Math.round(getItem(position).time) + "\"");
        ViewGroup.LayoutParams lp = holder.length.getLayoutParams();
        lp.width = (int) (mMinWidth + (mMaxWidth / 60f) * getItem(position).time);
        return convertView;
    }
}
