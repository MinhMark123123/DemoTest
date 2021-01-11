package m.n.demotest.commons;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import dagger.hilt.android.AndroidEntryPoint;


public abstract class BaseFragment extends Fragment {
    abstract public int provideResIdLayout();

    abstract public void doBindView(View view);

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(provideResIdLayout(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doBindView(view);
        doListenObserver();
    }


    abstract public void doListenObserver();
}
