package com.asw.shoplist.viewlists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.asw.shoplist.MyApplication;
import com.asw.shoplist.databinding.FragmentViewListsBinding;

public class ViewListsFragment extends Fragment {
    private FragmentViewListsBinding viewDataBinding;
    private ViewListsViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewDataBinding = FragmentViewListsBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(
                this, new ViewListsViewModel.ViewListsViewModelFactory(
                ((MyApplication) requireActivity().getApplication()).getRepository()
        )).get(ViewListsViewModel.class);
        viewDataBinding.setViewmodel(viewModel);
        return viewDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewDataBinding.setLifecycleOwner(getViewLifecycleOwner());
    }
}