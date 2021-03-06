package util.diffutil.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import util.diffutil.model.Employee;

import java.util.ArrayList;
import java.util.List;

import static util.diffutil.model.Employee.UPDATE_THUMB;
import static util.diffutil.model.Employee.UPDATE_TIME;

public class EmployeeDiffCallback extends DiffUtil.Callback {

    private final List<Employee> mOldEmployeeList;
    private final List<Employee> mNewEmployeeList;

    public EmployeeDiffCallback(List<Employee> oldEmployeeList, List<Employee> newEmployeeList) {
        this.mOldEmployeeList = oldEmployeeList;
        this.mNewEmployeeList = newEmployeeList;
    }

    @Override
    public int getOldListSize() {
        return mOldEmployeeList != null ? mOldEmployeeList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return mNewEmployeeList != null ? mNewEmployeeList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldEmployeeList.get(oldItemPosition).getId()
                == mNewEmployeeList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final Employee oldEmployee = mOldEmployeeList.get(oldItemPosition);
        final Employee newEmployee = mNewEmployeeList.get(newItemPosition);
        return oldEmployee.getName().equals(newEmployee.getName());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        // Implement method if you're going to use ItemAnimator
        //return super.getChangePayload(oldItemPosition, newItemPosition);

        Employee newItem = mNewEmployeeList.get(newItemPosition);
        //Employee oldItem = mOldEmployeeList.get(oldItemPosition);
        Employee oldItem = new Employee(0, "", "", 0);
        if (oldItemPosition < mOldEmployeeList.size()) oldItem = mOldEmployeeList.get(oldItemPosition);

        Bundle diff = new Bundle();
        if(!newItem.getName().equals(oldItem.getName())){
            diff.putString("name", newItem.getName());
        }
        if(!newItem.getRole().equals(oldItem.getRole())){
            diff.putString("role", newItem.getRole());
        }
        if (diff.size()==0){
            return null;
        }
        return diff;
/*
        if (oldItem.getTimestamp() != newItem.getTimestamp()) {
            return UPDATE_TIME;
        } else {
            return UPDATE_THUMB;
        }
        */
    }
}
