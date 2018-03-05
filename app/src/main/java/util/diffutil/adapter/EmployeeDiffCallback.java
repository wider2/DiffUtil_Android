package util.diffutil.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import util.diffutil.model.Employee;

import java.util.List;

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

        Employee newContact = mNewEmployeeList.get(newItemPosition);
        Employee oldContact = mOldEmployeeList.get(oldItemPosition);

        Bundle diff = new Bundle();
        if(!newContact.getName().equals(oldContact.getName())){
            diff.putString("name", newContact.getName());
        }
        if(!newContact.getRole().equals (oldContact.getRole())){
            diff.putString("role", newContact.getRole());
        }
        if (diff.size()==0){
            return null;
        }
        return diff;
    }
}
