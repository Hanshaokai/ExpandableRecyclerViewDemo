package com.example.han.expandablerecyclerviewdemo.bean;

import com.example.han.expandablerecyclerviewdemo.model.ExpandableListItem;

import java.util.List;

/**
 * Created by ${han} on 2016/9/26.
 */

public class Department implements ExpandableListItem {
    private boolean mExpand = false;
    public String name;
    public List<Employee> mEmployees;

    @Override
    public List<?> getChildItemList() {
        return mEmployees;
    }

    @Override
    public boolean isExpanded() {
        return mExpand;
    }

    @Override
    public void setExpanded(boolean isExpanded) {
        mExpand = isExpanded;
    }

    @Override
    public String toString() {
        return "Department{" +
                "mExpand=" + mExpand +
                ", name='" + name + '\'' +
                ", mEmployees=" + mEmployees +
                '}';
    }
}
