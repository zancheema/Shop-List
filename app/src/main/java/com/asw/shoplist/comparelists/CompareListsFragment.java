package com.asw.shoplist.comparelists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.asw.shoplist.Event;
import com.asw.shoplist.MyApplication;
import com.asw.shoplist.databinding.FragmentCompareListsBinding;
import com.google.android.material.snackbar.Snackbar;

public class CompareListsFragment extends Fragment {
    private FragmentCompareListsBinding viewDataBinding;
    private CompareListsViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewDataBinding = FragmentCompareListsBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(
                this, new CompareListsViewModel.CompareListsViewModelFactory(
                ((MyApplication) requireActivity().getApplication()).getRepository()
        )).get(CompareListsViewModel.class);
        viewDataBinding.setViewmodel(viewModel);
        return viewDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewDataBinding.setLifecycleOwner(getViewLifecycleOwner());
        setUpSnackbar();
    }

    private void setUpSnackbar() {
        viewModel.observeInvalidShopEvent().observe(getViewLifecycleOwner(), new Event.EventObserver<>(
                invalid -> {
                    if (invalid)
                        Snackbar.make(getView(), "Invalid List Selected", Snackbar.LENGTH_SHORT).show();
                }
        ));
    }
}