package com.example.infinite_scroll.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.infinite_scroll.databinding.FragmentFirstBinding
import com.example.infinite_scroll.view.adapter.PictureAdapter
import com.example.infinite_scroll.viewmodel.FirstViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FirstFragment : Fragment() {
    lateinit var binding: FragmentFirstBinding
    private val adapter = PictureAdapter()

    private val viewModel: FirstViewModel by activityViewModels()

    private lateinit var dialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater,container,false)
        dialog = LoadingDialog(requireContext())
        setRecyclerView()

        viewModel.getPictureList()
        subscribeToObservables()
        return binding.root
    }

    private fun subscribeToObservables() {
        lifecycleScope.launch {
            viewModel.pictureList.collect() { pictureList ->
                adapter.submitList(pictureList.toList())
            }
        }
        viewModel.isLoading.observe(requireActivity()) { loading ->
            showLoading(loading)
        }
    }

    private fun showLoading(loading: Boolean) {
        if (loading) dialog.show() else dialog.dismiss()
    }

    private fun setRecyclerView(){
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

}