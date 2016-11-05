package com.swpuiot.jike;

/**
 * Created by 羊荣毅_L on 2016/11/1.
 */
public class My {
    public My( int imageid,String words) {
        this.words = words;
        this.imageid = imageid;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    private String words;
    private int imageid;
}
