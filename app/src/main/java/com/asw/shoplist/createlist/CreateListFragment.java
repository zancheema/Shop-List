package com.asw.shoplist.createlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.asw.shoplist.databinding.FragmentCreateListBinding;

public class CreateListFragment extends Fragment {
    private FragmentCreateListBinding viewDataBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewDataBinding = FragmentCreateListBinding.inflate(inflater, container, false);
        return viewDataBinding.getRoot();
    }
}