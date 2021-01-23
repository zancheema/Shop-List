package com.asw.shoplist.viewlists;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asw.shoplist.R;
import com.asw.shoplist.databinding.FragmentViewListsBinding;

public class ViewListsFragment extends Fragment {
    private FragmentViewListsBinding viewDataBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewDataBinding = FragmentViewListsBinding.inflate(inflater, container, false);
        return viewDataBinding.getRoot();
    }
}