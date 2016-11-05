package com.swpuiot.jike;

import java.io.Serializable;

/**
 * Created by 羊荣毅_L on 2016/11/4.
 */
public class ResponseEntity implements Serializable {

    /**
     * code : 1
     * msg : success
     * data : {"id":1,"name":"wuhaojie","password":"123456","age":20,"text":"一枚大三狗"}
     */

    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;
    /**
     * id : 1
     * name : wuhaojie
     * password : 123456
     * age : 20
     * text : 一枚大三狗
     */

    private DataBean data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private static final long serialVersionUID = 1L;
        private int id;
        private String name;
        private String password;
        private int age;
        private String text;
        private String avatar;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
