package util.diffutil;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import util.diffutil.adapter.EmployeeRecyclerViewAdapter;
import util.diffutil.adapter.OnProductRequestClickedListener;
import util.diffutil.utils.DummyEmployeeDataUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private EmployeeRecyclerViewAdapter mRecyclerViewAdapter;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_output)
    TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        mRecyclerViewAdapter = new EmployeeRecyclerViewAdapter(DummyEmployeeDataUtils.getEmployeeListSortedByRole(), mListener);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRecyclerViewAdapter.updateEmployeeListItems(DummyEmployeeDataUtils.getUpdatedData());
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    OnProductRequestClickedListener mListener = new OnProductRequestClickedListener() {
        @Override
        public void onProfileRequestClicked(int position, String data) {
            tvOutput.setText("Output: " + data);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_by_name:
                mRecyclerViewAdapter.updateEmployeeListItems(
                        DummyEmployeeDataUtils.getEmployeeListSortedByName());
                return true;
            case R.id.sort_by_role:
                mRecyclerViewAdapter.updateEmployeeListItems(
                        DummyEmployeeDataUtils.getEmployeeListSortedByRole());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //swipeRefreshLayout
    }
}
