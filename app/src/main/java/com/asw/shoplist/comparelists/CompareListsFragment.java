package com.asw.shoplist.comparelists;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asw.shoplist.R;
import com.asw.shoplist.databinding.FragmentCompareListsBinding;

public class CompareListsFragment extends Fragment {
    private FragmentCompareListsBinding viewDataBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewDataBinding = FragmentCompareListsBinding.inflate(inflater, container, false);
        return viewDataBinding.getRoot();
    }
}