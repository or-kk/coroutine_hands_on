package kr.co.fastcampus.co.kr.coroutines.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.co.fastcampus.co.kr.coroutines.R
import kr.co.fastcampus.co.kr.coroutines.databinding.FragmentFavouritesBinding
import kr.co.fastcampus.co.kr.coroutines.databinding.FragmentMainBinding


class FavouritesFragment : Fragment() {

    private lateinit var imageSearchViewModel: ImageSearchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageSearchViewModel = ViewModelProvider(requireActivity())[ImageSearchViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        val root = binding.root

        val adapter = FavouritesAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)

        viewLifecycleOwner.lifecycleScope.launch {
            imageSearchViewModel.favoritesFlow.collectLatest { items ->
                Log.e("ralph", "items size1 = $items")
                adapter.setItems(items)
            }
        }

        return root
    }

    companion object {

    }
}