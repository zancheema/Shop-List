package com.asw.shoplist.createlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.asw.shoplist.Event.EventObserver;
import com.asw.shoplist.MyApplication;
import com.asw.shoplist.createlist.CreateListViewModel.CreateListViewModelFactory;
import com.asw.shoplist.databinding.FragmentCreateListBinding;
import com.google.android.material.snackbar.Snackbar;

public class CreateListFragment extends Fragment {
    private FragmentCreateListBinding viewDataBinding;
    private CreateListViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewDataBinding = FragmentCreateListBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this, new CreateListViewModelFactory(
                ((MyApplication) requireActivity().getApplication()).getRepository()
        )).get(CreateListViewModel.class);
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
        viewModel.observeEmptyFieldEvent().observe(getViewLifecycleOwner(), new EventObserver<>(
                empty -> {
                    if (empty) Snackbar.make(getView(), "Empty Field", Snackbar.LENGTH_SHORT).show();
                }
        ));
        viewModel.observeItemAddedEvent().observe(getViewLifecycleOwner(), new EventObserver<>(
                added -> {
                    if (added) {
                        Snackbar.make(getView(), "Item added", Snackbar.LENGTH_SHORT).show();
                        viewModel.itemSaveComplete();
                    }
                }
        ));
    }
}