package com.asw.shoplist.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asw.shoplist.R;
import com.asw.shoplist.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding viewDataBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewDataBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return viewDataBinding.getRoot();
    }
}