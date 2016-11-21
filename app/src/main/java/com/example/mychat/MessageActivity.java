package com.example.mychat;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.AudioRecorderButton;
import util.Person;

public class MessageActivity extends AppCompatActivity{
    private Person person = null;
    private Person me = null;
    private AudioRecorderButton mAudioRecorderButton;
    private View animView;
    private ArrayAdapter<Recorder> mAdapter = null;
    private List<Recorder> mDatas = new ArrayList<Recorder>();
    private ListView mListView;
    private EditText inputMsg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Intent intent = getIntent();
        //person = (Person) intent.getExtras().getSerializable("person");
        //me = (Person) intent.getExtras().getSerializable("me");

        //((TextView)findViewById(R.id.tv_nickname)).setText(person.personNickNameId);

        Button btnTalk = (Button) findViewById(R.id.btnTalk);
        Button btnSend = (Button) findViewById(R.id.send_message);
        mAudioRecorderButton = (AudioRecorderButton) findViewById(R.id.id_recorder_button);
        mListView = (ListView) findViewById(R.id.id_listview);
        inputMsg = (EditText) findViewById(R.id.input_message);
        final LinearLayout chatTool = (LinearLayout) findViewById(R.id.btn_chat_Tool);
        mAudioRecorderButton.setFinishRecordCallBack(new AudioRecorderButton.AudioFinishRecordCallBack() {
            @Override
            public void onFinish(float seconds, String filePath) {
                Recorder recorder = new Recorder(filePath, seconds);
                mDatas.add(recorder);
                //更新数据
                mAdapter.notifyDataSetChanged();
                //设置位置
                mListView.setSelection(mDatas.size()-1);
            }
        });


        //设置消息发送按钮监听事件
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = inputMsg.getText().toString();
                if(msg == null || msg.length()<0){
                    Toast.makeText(getApplicationContext(),getString(R.string.content_is_empty),Toast.LENGTH_SHORT).show();
                    return;
                }
                inputMsg.setText("");
                View view = getLayoutInflater().inflate(R.layout.msg_layout,null);
                ImageView myHeadIcon = (ImageView) findViewById(R.id.me_headIcon);
                TextView sendContent = (TextView) findViewById(R.id.send_msg);
                TextView sendTime = (TextView) findViewById(R.id.send_time);

                myHeadIcon.setImageResource(me.personHeadIconId);
                sendContent.setText(msg);
                sendTime.setText(new Date().toString());
            }
        });



        //设置语音按钮监听事件
        btnTalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatTool.setVisibility(View.GONE);
                mAudioRecorderButton.setVisibility(View.VISIBLE);
            }
        });


        mAdapter = new RecorderAdapter(this, mDatas);
        mListView.setAdapter(mAdapter);

        //listView的item点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                // 声音播放动画
                if (animView != null) {
                    animView.setBackgroundResource(R.drawable.adj);
                    animView = null;
                }
                animView = view.findViewById(R.id.recoder_anim);
                animView.setBackgroundResource(R.drawable.play_anim);
                AnimationDrawable animation = (AnimationDrawable) animView.getBackground();
                animation.start();
                // 播放录音
                MediaPlayerManager.playSound(mDatas.get(position).filePath, new MediaPlayer.OnCompletionListener() {

                    public void onCompletion(MediaPlayer mp) {
                        //播放完成后修改图片
                        animView.setBackgroundResource(R.drawable.adj);
                    }
                });
            }
        });

    }

}
