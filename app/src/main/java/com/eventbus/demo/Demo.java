package com.eventbus.demo;

import java.io.Serializable;

/**
 * 作者：潇湘夜雨 on 2018/8/21.
 * 邮箱：879689064@qq.com
 */
public class Demo  implements Serializable {
    private String name;
    private String password;

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

    public Demo(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

