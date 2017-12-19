package com.kotlinblog.actors.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.kotlinblog.actors.R;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mViewModel;
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mAdapter = new MainAdapter(mViewModel, this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(mAdapter);

        DividerItemDecoration divider = new DividerItemDecoration(
                recyclerView.getContext(),
                lm.getOrientation()
        );

        recyclerView.addItemDecoration(divider);

        if (savedInstanceState == null) {
            mViewModel.fetchActors();
        }

        observeActors();
    }

    private void observeActors() {
        mViewModel.getActors().observe(this,
                actorsList -> {
                    mAdapter.notifyDataSetChanged();
                    Timber.d("Actors changed...");
                });

        mViewModel.getConnectionError().observe(this,
                error -> Toast.makeText(MainActivity.this,
                        "Connection error: " + error,
                        Toast.LENGTH_LONG)
                        .show());
    }
}
