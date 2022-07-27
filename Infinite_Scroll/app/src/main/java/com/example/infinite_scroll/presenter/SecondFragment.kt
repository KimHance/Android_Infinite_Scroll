package com.example.infinite_scroll.presenter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.infinite_scroll.databinding.FragmentSecondBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private val viewModel: SecondViewModel by activityViewModels()
    private lateinit var picturePagingAdapter: PicturePagingAdapter
    private lateinit var dialog: LoadingDialog


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        picturePagingAdapter = PicturePagingAdapter()
        dialog = LoadingDialog(requireContext())
        initRecyclerView()

        lifecycleScope.launch {
            viewModel.getPictureList()
        }
        collectFlows()
        return binding.root
    }

    private fun initRecyclerView() {
        binding.rvPicture.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = picturePagingAdapter
        }
    }

    private fun collectFlows() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.picList.collectLatest { picture ->
                        picturePagingAdapter.submitData(picture)
                    }
                }

                launch {
                    picturePagingAdapter.loadStateFlow
                        .collectLatest {
                            if (it.source.append is LoadState.Loading) {
                                Log.d("석주형", "collectFlows: ${it.source.append}")
                                dialog.show()
                            } else {
                                Log.d("석주형", "collectFlows: ${it.source.append}")
                                dialog.dismiss()
                            }
                        }
                }
            }
        }
    }
}