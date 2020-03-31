package com.crossbox.multiplefragmentstest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {

    private int page;
    private String title;

    // below function is required since we want to pass arguments (fragment constructor needs to be free of arguments, therefore)
    public static FirstFragment newInstance(int page , String title){
        FirstFragment firstFragment = new FirstFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("someInt",page);
        bundle.putString("someTitle",title);
        firstFragment.setArguments(bundle);
        return firstFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt",0);
        title = getArguments().getString("someTitle");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment,container,false);
        TextView textView = view.findViewById(R.id.tvLabel);
        textView.setText(page+" "+title);
        return view;
    }
}
