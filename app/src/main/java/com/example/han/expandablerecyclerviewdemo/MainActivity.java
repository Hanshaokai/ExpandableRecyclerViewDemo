package com.example.han.expandablerecyclerviewdemo;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.han.expandablerecyclerviewdemo.adapter.BaseExpandableAdapter;
import com.example.han.expandablerecyclerviewdemo.bean.Company;
import com.example.han.expandablerecyclerviewdemo.bean.Department;
import com.example.han.expandablerecyclerviewdemo.bean.Employee;
import com.example.han.expandablerecyclerviewdemo.itemview.CompanyItem;
import com.example.han.expandablerecyclerviewdemo.itemview.DepartmentItem;
import com.example.han.expandablerecyclerviewdemo.itemview.EmployeeItem;
import com.example.han.expandablerecyclerviewdemo.viewholder.AbstractAdapterItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final int ITEM_TYPE_COMPANY = 1;
    private final int ITEM_TYPE_DEPARTMENT = 2;
    private final int ITEM_TYPE_EMPLOYEE = 3;
    private RecyclerView mRecyclerView;
    private BaseExpandableAdapter mBaseExpandableAdapter;
    private ArrayList mCompanylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(false);
        mRecyclerView = (RecyclerView) findViewById(R.id.rcv);
       /* final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRecyclerView != null) {
                    Log.d("tag", "点击了fab");
                    Company company = (Company) mBaseExpandableAdapter.getDataList().get(0);
                    company.name = company.name + "-has changed";
                 //   mBaseExpandableAdapter.modifyItem(0, company);
                }


            }
        });*/
        initData();
        mBaseExpandableAdapter = new BaseExpandableAdapter(mCompanylist) {
            @NonNull
            @Override
            public AbstractAdapterItem<Object> getItemView(Object type) {
                int itemType = (int) type;
                switch (itemType) {

                    case ITEM_TYPE_COMPANY:
                        return new CompanyItem();
                    case ITEM_TYPE_DEPARTMENT:
                        return new DepartmentItem();
                    case ITEM_TYPE_EMPLOYEE:
                        return new EmployeeItem();
                }
                return null;
            }

            @Override
            public Object getItemViewType(Object t) {
                if (t instanceof Company) {
                    return ITEM_TYPE_COMPANY;
                } else if (t instanceof Department) {
                    return ITEM_TYPE_DEPARTMENT;
                } else if (t instanceof Employee)

                    return ITEM_TYPE_EMPLOYEE;
                return -1;
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mBaseExpandableAdapter);
    }

    private void initData() {
        mCompanylist = new ArrayList<>();
        mCompanylist.add(createCompany("Google1", true));
        mCompanylist.add(createCompany("Google2", true));
        mCompanylist.add(createCompany("Google3", true));
        mCompanylist.add(createCompany("Google4", true));
        mCompanylist.add(createCompany("Google5", true));
        mCompanylist.add(createCompany("Google6", true));
        mCompanylist.add(createCompany("Google7", true));

    }

    private Object createCompany(String companyName, boolean isExpandDefault) {
        Company firstCompany = new Company();
        firstCompany.name = companyName;
        List<Department> departments = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Department department = new Department();
            department.name = "Department:" + i;


            List<Employee> employeeList = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                Employee employee = new Employee();
                employee.name = "Employee:" + j;
                employeeList.add(employee);
            }
            department.mEmployees = employeeList;
            departments.add(department);
        }
        firstCompany.mDepartments = departments;
        firstCompany.mExpanded = isExpandDefault;
        return firstCompany;
    }

}
