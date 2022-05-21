package com.viiishoppinglistapp.doit.ui.inventory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.viiishoppinglistapp.doit.databinding.FragmentTabbedInventoryBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragmentInventory extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModelInventory pageViewModelInventory;
    private FragmentTabbedInventoryBinding binding;

    public static PlaceholderFragmentInventory newInstance(int index) {
        PlaceholderFragmentInventory fragment = new PlaceholderFragmentInventory();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModelInventory = new ViewModelProvider(this).get(PageViewModelInventory.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModelInventory.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentTabbedInventoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.sectionLabel;
        pageViewModelInventory.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}