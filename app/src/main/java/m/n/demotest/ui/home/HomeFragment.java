package m.n.demotest.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.evrencoskun.tableview.TableView;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import m.n.demotest.R;
import m.n.demotest.commons.BaseFragment;
import m.n.demotest.data.entity.remote.ConsolidatedWeather;
import m.n.demotest.databinding.FragmentHomeBinding;

@AndroidEntryPoint
public class HomeFragment extends BaseFragment {
    private FragmentHomeBinding binding;
    private HomeFragmentViewModel viewModel;
    TableAdapter tableAdapter = new TableAdapter();
    TableViewOnClickListener tableViewOnClickListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);
        getLifecycle().addObserver(viewModel);
    }

    @Override
    public int provideResIdLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void doBindView(View view) {
        binding = FragmentHomeBinding.bind(view);
        binding.loadDataButton.setOnClickListener(v -> {
            if (viewModel.isInProgressLive() != null) {
                Boolean isInProgress = viewModel.isInProgressLive().getValue();
                if (isInProgress == null || !isInProgress) {
                    viewModel.loadData();
                }
            }

        });
        tableViewOnClickListener = new TableViewOnClickListener(binding.contentContainer, rowPosition -> {
            List<ConsolidatedWeather> listData = viewModel.weatherLive().getValue();
            if (listData != null) {
                showDialog(listData.get(rowPosition).getId());
            }

        });
        ((TableView)(binding.contentContainer)).setTableViewListener(tableViewOnClickListener);
        binding.contentContainer.setAdapter(tableAdapter);

    }

    private void showDialog(Long id) {
        Log.d("mmm", "showDialog");
        m.n.demotest.ui.detail.DetailFragment.showDialog(id, getChildFragmentManager().beginTransaction());
        // DetailFragment.showDialog(id, getChildFragmentManager().beginTransaction());
       /* FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        DialogFragment newFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("id", id);
        newFragment.setArguments(bundle);
        newFragment.show(ft, "dialog");*/
    }


    @Override
    public void onDestroy() {
        binding = null;
        super.onDestroy();
    }

    @Override
    public void doListenObserver() {
        viewModel.errorLiveData().observe(this, error -> {
            m.n.demotest.utils.ShowCookieKt.showCookie(requireActivity(), "Error", error.getErrorMessage());
        });
        viewModel.cellDataLive().observe(this, cellList -> {
            tableAdapter.setAllItems(viewModel.columnHeaderLive().getValue(), viewModel.rowHeaderLive().getValue(), viewModel.cellDataLive().getValue());
        });
        viewModel.isInProgressLive().observe(this, isInProgress -> {
            if (isInProgress != null && isInProgress) {
                binding.progressBar.setVisibility(View.VISIBLE);
            } else {
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

}