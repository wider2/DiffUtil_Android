package util.diffutil.adapter;

import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import util.diffutil.R;
import util.diffutil.model.Employee;

import java.util.ArrayList;
import java.util.List;

import static util.diffutil.model.Employee.UPDATE_THUMB;
import static util.diffutil.model.Employee.UPDATE_TIME;


public class EmployeeRecyclerViewAdapter extends
        RecyclerView.Adapter<EmployeeRecyclerViewAdapter.ViewHolder> {

    private List<Employee> mEmployees = new ArrayList<>();
    private OnProductRequestClickedListener mListener;


    public EmployeeRecyclerViewAdapter(List<Employee> employeeList, OnProductRequestClickedListener listener) {
        this.mEmployees.addAll(employeeList);
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {

        if (payloads.isEmpty()) {
            //mListener.onProfileRequestClicked(position, "");
            //onBindViewHolder(holder, position);
            super.onBindViewHolder(holder, position, payloads);
        } else {

            Bundle bundle = (Bundle) payloads.get(0);
            if (bundle.size() != 0) {
                String name = bundle.getString("name");
                if (name != null) {
                    holder.tv_name.setText(name);
                }
                String role = bundle.getString("role");
                if (role != null) {
                    holder.tv_role.setText(role);
                }
            }
            mListener.onProfileRequestClicked(position, bundle.toString());
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Employee employee = mEmployees.get(position);

        holder.tv_name.setText(employee.getName());
        holder.tv_role.setText(employee.getRole());
    }

    //trigger DiffUtil to detect changes.
    public void updateEmployeeListItems(List<Employee> employees) {
        //calling the CallBack class and getting the difference between old and new list and dispatching it to the adapter.
        final EmployeeDiffCallback diffCallback = new EmployeeDiffCallback(this.mEmployees, employees);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.mEmployees.clear();
        this.mEmployees.addAll(employees);
        //adapter will receive all the corresponding notifyItemRange events
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public int getItemCount() {
        return mEmployees.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_role;
        private final TextView tv_name;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.employee_name);
            tv_role = (TextView) itemView.findViewById(R.id.employee_role);
        }
    }
}
