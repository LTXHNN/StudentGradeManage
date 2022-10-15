package com.ty.pojo;

import java.util.List;

/**
 * @author lemon缚
 * @date 2022/07/06
 **/
public class PageBean<T> {

    private int totalPage;
    private List<T> students;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getStudents() {
        return students;
    }

    public void setStudents(List<T> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalPage=" + totalPage +
                ", students=" + students +
                '}';
    }
}
