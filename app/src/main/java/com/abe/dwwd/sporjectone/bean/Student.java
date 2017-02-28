package com.abe.dwwd.sporjectone.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/28.
 */

public class Student {
    private String name;
    private List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
