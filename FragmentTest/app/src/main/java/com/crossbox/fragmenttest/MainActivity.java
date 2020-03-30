
// This app is only has a single fragment.
// Its basically meant to show how a frag communicates with an activity
// and how an activity communicates with a frag
// There is another app if you want to see several fragments in a single activity


package com.crossbox.fragmenttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements FooFragment.OnItemSelectedListener{

    String fragment1Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.your_placeholder,new FooFragment(),"foo")
                .addToBackStack(null)
                .commit();

        getSupportFragmentManager().executePendingTransactions(); // VERY IMPORTANT LINE otherwise findfragmentbytag()
        // Either do the above or override onAttachFragment() (SEE THE COMMENT BELOW)
        FooFragment fooFragment = (FooFragment) getSupportFragmentManager().findFragmentByTag("foo");
        Log.i("fooFrag Tag", fooFragment.getTag());
        fooFragment.doSomething("arguments");
    }

    @Override // implementation of onItemSelectedListener's method onRssItemSelected
    public void onRssItemSelected(String someLink) {
        Log.i("FromFragmentToActivity",someLink);
    }

//    @Override
//    public void onAttachFragment(@NonNull Fragment fragment) {
//        super.onAttachFragment(fragment);
//        if(fragment.getTag()=="foo"){
//            FooFragment ff = (FooFragment) fragment;
//            ff.doSomething(null);
//        }
//    }
}
