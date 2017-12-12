package com.kotlinblog.actors.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.kotlinblog.actors.R
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        mAdapter = MainAdapter(mViewModel, applicationContext)
        val lm = LinearLayoutManager(this)

        recyclerView.apply {
            layoutManager = lm
            adapter = mAdapter
            val divider = DividerItemDecoration(
                    recyclerView.context,
                    lm.orientation)
            addItemDecoration(divider)
        }

        if (savedInstanceState == null) {
            mViewModel.fetchActors()
        }
        observeActors()
    }

    private fun observeActors() {
        mViewModel.actors.observe(this, Observer {
            mAdapter.notifyDataSetChanged()
            Timber.d("Actors changed...")
        })

        mViewModel.connectionError.observe(this,
                Observer {
                    Toast.makeText(this@MainActivity,
                            "Connection error: $it",
                            Toast.LENGTH_LONG)
                            .show()
                })
    }
}
