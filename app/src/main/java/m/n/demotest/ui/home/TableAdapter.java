package m.n.demotest.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import m.n.demotest.R;
import m.n.demotest.databinding.TableViewCellLayoutBinding;
import m.n.demotest.databinding.TableViewColumnHeaderLayoutBinding;
import m.n.demotest.databinding.TableViewRowHeaderLayoutBinding;
import m.n.demotest.ui.home.ui_model.Cell;
import m.n.demotest.ui.home.ui_model.ColumnHeader;
import m.n.demotest.ui.home.ui_model.RowHeader;

public class TableAdapter extends AbstractTableAdapter<ColumnHeader, RowHeader, Cell> {
    DoOnItemClicked onItemClicked;

    public DoOnItemClicked getOnItemClicked() {
        return onItemClicked;
    }

    /**
     * This is sample CellViewHolder class
     * This viewHolder must be extended from AbstractViewHolder class instead of RecyclerView.ViewHolder.
     */
    static class MyCellViewHolder extends AbstractViewHolder {

        public TableViewCellLayoutBinding cellLayoutBinding;

        public MyCellViewHolder(View itemView) {
            super(itemView);
            cellLayoutBinding = TableViewCellLayoutBinding.bind(itemView);

        }
    }

    @Override
    public int getColumnHeaderItemViewType(int columnPosition) {
        // The unique ID for this type of column header item
        // If you have different items for Cell View by X (Column) position,
        // then you should fill this method to be able create different
        // type of ColumnViewHolder on "onCreateColumnViewHolder"
        return columnPosition;
    }

    @Override
    public int getRowHeaderItemViewType(int rowPosition) {
        // The unique ID for this type of row header item
        // If you have different items for Row Header View by Y (Row) position,
        // then you should fill this method to be able create different
        // type of RowHeaderViewHolder on "onCreateRowHeaderViewHolder"
        return rowPosition;
    }

    @Override
    public int getCellItemViewType(int columnPosition) {
        // The unique ID for this type of cell item
        // If you have different items for Cell View by X (Column) position,
        // then you should fill this method to be able create different
        // type of CellViewHolder on "onCreateCellViewHolder"
        return columnPosition;
    }

    @NonNull
    @Override
    public AbstractViewHolder onCreateCellViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Get cell xml layout
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_view_cell_layout, parent, false);
        // Create a Custom ViewHolder for a Cell item.
        MyCellViewHolder holder = new MyCellViewHolder(layout);
        holder.cellLayoutBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
            }
        });
        return holder ;
    }

    @Override
    public void onBindCellViewHolder(@NonNull AbstractViewHolder holder, @Nullable Cell cellItemModel, int columnPosition, int rowPosition) {
        Cell cell = (Cell) cellItemModel;

        // Get the holder to update cell item text
        MyCellViewHolder viewHolder = (MyCellViewHolder) holder;
        viewHolder.cellLayoutBinding.cellData.setText(cell.getData().toString());

        // If your TableView should have auto resize for cells & columns.
        // Then you should consider the below lines. Otherwise, you can ignore them.

        // It is necessary to remeasure itself.
        viewHolder.cellLayoutBinding.cellContainer.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
        viewHolder.cellLayoutBinding.cellData.requestLayout();
    }

    /**
     * This is sample ColumnHeaderViewHolder class.
     * This viewHolder must be extended from AbstractViewHolder class instead of RecyclerView.ViewHolder.
     */
    static class MyColumnHeaderViewHolder extends AbstractViewHolder {

        TableViewColumnHeaderLayoutBinding binding;

        public MyColumnHeaderViewHolder(View itemView) {
            super(itemView);
            binding = TableViewColumnHeaderLayoutBinding.bind(itemView);
        }
    }

    @NonNull
    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Get Column Header xml Layout
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_view_column_header_layout, parent, false);

        // Create a ColumnHeader ViewHolder
        return new MyColumnHeaderViewHolder(layout);
    }

    @Override
    public void onBindColumnHeaderViewHolder(@NonNull AbstractViewHolder holder, @Nullable ColumnHeader columnHeaderItemModel, int columnPosition) {

        // Get the holder to update cell item text
        MyColumnHeaderViewHolder columnHeaderViewHolder = (MyColumnHeaderViewHolder) holder;
        columnHeaderViewHolder.binding.columnHeaderTextView.setText((String) (columnHeaderItemModel).getData());

        // If your TableView should have auto resize for cells & columns.
        // Then you should consider the below lines. Otherwise, you can ignore them.

        // It is necessary to remeasure itself.
        columnHeaderViewHolder.binding.columnHeaderContainer.getLayoutParams().width = LinearLayout
                .LayoutParams.WRAP_CONTENT;
        columnHeaderViewHolder.binding.columnHeaderTextView.requestLayout();
    }

    /**
     * This is sample RowHeaderViewHolder class.
     * This viewHolder must be extended from AbstractViewHolder class instead of RecyclerView.ViewHolder.
     */
    static class MyRowHeaderViewHolder extends AbstractViewHolder {

        TableViewRowHeaderLayoutBinding binding;

        public MyRowHeaderViewHolder(View itemView) {
            super(itemView);
            binding = TableViewRowHeaderLayoutBinding.bind(itemView);
        }
    }

    @NonNull
    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Get Row Header xml Layout
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_view_row_header_layout, parent, false);

        // Create a Row Header ViewHolder
        return new MyRowHeaderViewHolder(layout);
    }

    @Override
    public void onBindRowHeaderViewHolder(@NonNull AbstractViewHolder holder, @Nullable RowHeader rowHeaderItemModel, int rowPosition) {

        // Get the holder to update row header item text
        MyRowHeaderViewHolder rowHeaderViewHolder = (MyRowHeaderViewHolder) holder;
        rowHeaderViewHolder.binding.rowHeaderTextView.setText(rowHeaderItemModel.getData().toString());
    }

    @NonNull
    @Override
    public View onCreateCornerView(@NonNull ViewGroup parent) {
        return LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_view_corner_layout, parent, false);
    }
}

