package com.lib.base.baselib;

/**
 * Created by Administrator on 2017/5/16.
 */

public class LoginData  {
    String poster;
    String synopsis;

    public LoginData(String poster, String synopsis) {
        this.poster = poster;
        this.synopsis = synopsis;
    }


    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public LoginData() {
        super();
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "poster='" + poster + '\'' +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }
}
