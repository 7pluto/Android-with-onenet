package com.example.myapplication;

import java.util.List;

public class MyObject {

    String update_at, id, create_time, current_value;
    List<MyObject> data;
    public List<MyObject> getdata() {
        return data;
    }
    public void setdata(List<MyObject> data) {
        this.data = data;
    }

    public String geupdate_at() {
        return update_at;
    }
    public void setupdate_at(String update_at) {
        this.update_at = update_at;
    }

    public String getid() {
        return id;
    }
    public void setid(String id) {
        this.id = id;
    }

    public String getcreate_time() {
        return create_time;
    }
    public void setcreate_time(String create_time) {
        this.create_time = create_time;
    }
    public String getcurrent_value() {
        return current_value;
    }
    public void setcurrent_value(String current_value) {
        this.current_value = current_value;
    }
}
