package com.example.shoestore.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shoestore.MainViewModel
import com.example.shoestore.R
import com.example.shoestore.Shoe
import com.example.shoestore.databinding.FragmentListBinding
import com.example.shoestore.databinding.ShoeBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(
            layoutInflater, R.layout.fragment_list, container, false
        )

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addShoeFab.setOnClickListener {
            navigateToDetail()
        }

        mainViewModel.shoeList.observe(viewLifecycleOwner) { list ->
            list.forEach { shoe ->
                addToLayout(shoe)
            }
        }
    }

    fun navigateToDetail() {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment())
    }

    fun logout() {
        findNavController().navigate(ListFragmentDirections.logout())
    }

    fun addToLayout(shoe: Shoe) {
        val view = ShoeBinding.inflate(LayoutInflater.from(requireContext()))
        view.nameShoeText.text = shoe.name
        view.companyShoeText.text = shoe.company
        view.sizeShoeText.text = shoe.size
        view.descShoeText.text = shoe.desc
        binding.listLinearLayout.addView(view.root)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        logout()
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}