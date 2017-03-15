package com.example.dmsmith.transferwisecodechallenge.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.dmsmith.transferwisecodechallenge.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public abstract class SingleFragmentActivity extends AppCompatActivity {
    @BindView(R.id.fragment_toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        setFragments();
    }

    private void setFragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

    public abstract Fragment createFragment();

}
