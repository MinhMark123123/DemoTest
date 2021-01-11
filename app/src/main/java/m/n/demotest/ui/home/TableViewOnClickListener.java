package m.n.demotest.ui.home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.listener.ITableViewListener;

public class TableViewOnClickListener implements ITableViewListener {
    private DoOnItemClicked onItemClicked;
    @NonNull
    private TableView tableView;

    public TableViewOnClickListener(@NonNull TableView tableView, DoOnItemClicked onItemClicked) {
        this.tableView = tableView;
        this.onItemClicked = onItemClicked;
    }

    public void setOnItemClicked(DoOnItemClicked onItemClicked) {
        this.onItemClicked = onItemClicked;
    }

    @Override
    public void onCellClicked(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {
        if (onItemClicked != null) {
            onItemClicked.onItemClicked(row);
        }
        Log.d("mmm", "showDialog");
    }

    /*@Override
    public void onCellDoubleClicked(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {
        Log.d("mmm", "showDialog");
    }*/

    @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {
        Log.d("mmm", "showDialog");
    }

    @Override
    public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder columnHeaderView, int column) {
        Log.d("mmm", "showDialog");
    }

   /* @Override
    public void onColumnHeaderDoubleClicked(@NonNull RecyclerView.ViewHolder columnHeaderView, int column) {
        Log.d("mmm", "showDialog");
    }
*/
    @Override
    public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder columnHeaderView, int column) {
        Log.d("mmm", "showDialog");
    }

    @Override
    public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {
        if (onItemClicked != null) {
            onItemClicked.onItemClicked(row);
        }
        Log.d("mmm", "showDialog");
    }

   /* @Override
    public void onRowHeaderDoubleClicked(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {
        Log.d("mmm", "showDialog");
    }
*/
    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {
        Log.d("mmm", "showDialog");
    }
}
