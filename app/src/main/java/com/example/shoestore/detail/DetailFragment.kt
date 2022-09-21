package com.example.shoestore.detail

import android.os.Bundle
import android.text.method.TextKeyListener.clear
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shoestore.MainViewModel
import com.example.shoestore.R
import com.example.shoestore.Shoe
import com.example.shoestore.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(
            layoutInflater, R.layout.fragment_detail, container, false
        )

        binding.viewModel = mainViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.setOnClickListener {
            if (!binding.nameShoeLabelEditText.text.isNullOrEmpty())
                mainViewModel.updateShoe()
            navigateToList()
        }

        binding.cancelButton.setOnClickListener {
            navigateToList()
        }

    }

    private fun navigateToList() {
        mainViewModel.clear()
        findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToListFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}