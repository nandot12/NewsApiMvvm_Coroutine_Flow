package com.example.newsapimvvm_coroutine_flow.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapimvvm_coroutine_flow.R
import com.example.newsapimvvm_coroutine_flow.model.ArticlesItem
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        onObserver()
    }

    private fun onObserver() {

        viewModel.responseNews.observe(viewLifecycleOwner, { showResponse(it) })
        viewModel.error?.observe(viewLifecycleOwner, { showError(it) })
        viewModel.loading?.observe(viewLifecycleOwner, { showLoading(it) })
    }

    private fun showLoading(it: Boolean?) {

        //show loading
    }

    private fun showError(it: Throwable?) {

        //show error
    }

    private fun showResponse(it: List<ArticlesItem?>?) {

        for (i in it?.indices ?: 0..1){
            Log.d("Data ",it?.get(i)?.title.toString())
        }

    }

}