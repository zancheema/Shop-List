package com.asw.shoplist.home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asw.shoplist.Event;
import com.asw.shoplist.R;
import com.asw.shoplist.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding viewDataBinding;
    private HomeViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewDataBinding = FragmentHomeBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory())
                .get(HomeViewModel.class);
        viewDataBinding.setViewmodel(viewModel);
        return viewDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewDataBinding.setLifecycleOwner(getViewLifecycleOwner());
        setUpNavigation();
    }

    private void setUpNavigation() {
        NavController navController = NavHostFragment.findNavController(this);
        viewModel.observeAddListEvent().observe(getViewLifecycleOwner(), new Event.EventObserver<>(
                add -> {
                    if (add) navController.navigate(R.id.action_homeFragment_to_createListFragment);
                }
        ));
        viewModel.observeCompareListsEvent().observe(getViewLifecycleOwner(), new Event.EventObserver<>(
                compare -> {
                    if (compare)
                        navController.navigate(R.id.action_homeFragment_to_compareListsFragment);
                }
        ));
        viewModel.observeViewListsEvent().observe(getViewLifecycleOwner(), new Event.EventObserver<>(
                view -> {
                    if (view) navController.navigate(R.id.action_homeFragment_to_viewListsFragment);
                }
        ));
    }
}