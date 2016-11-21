package com.example.mychat;

/**
 * Description  录音实体类
 * Created by AMOBBS on 2016/11/11.
 */

class Recorder {
    float time;//时间长度
    String filePath;//文件路径

    public Recorder(String filePath, float time) {
        super();
        this.filePath = filePath;
        this.time = time;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

}
