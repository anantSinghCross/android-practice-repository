package com.crossbox.fragmenttest;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FooFragment extends Fragment {

    private OnItemSelectedListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foo,container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSomeClick(v);
            }
        });
    }

    //below code is used when activity wants to communicate with the fragment
    public void doSomething(String arg){
        Log.i("foo","doSomething("+arg+") called");
    }

    public interface OnItemSelectedListener{
        public void onRssItemSelected(String someLink);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnItemSelectedListener){
            listener = (OnItemSelectedListener) context;
        }else{
            throw new ClassCastException(context.toString()+" must implement FooFragment.OnItemSelectedListener");
        }
    }

    public void onSomeClick(View v){
        listener.onRssItemSelected("abcLink");
    }
}