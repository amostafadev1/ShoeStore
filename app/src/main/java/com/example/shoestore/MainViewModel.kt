package com.example.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val shoeName = MutableLiveData<String>()

    val shoeCompany = MutableLiveData<String>()

    val shoeSize = MutableLiveData<String>()

    val shoeDesc = MutableLiveData<String>()

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    init {
        _shoeList.value = mutableListOf()
    }

    fun updateShoe() {
        val shoe = Shoe(
            shoeName.value ?: "",
            shoeCompany.value ?: "",
            shoeSize.value ?: "",
            shoeDesc.value ?: ""
        )
        _shoeList.value?.add(shoe)
    }

    /**
     *  For at exit,
     *  fields get cleared so when user comes back they don't have previous values
     */
    fun clear() {
        shoeName.value = ""
        shoeCompany.value = ""
        shoeSize.value = ""
        shoeDesc.value = ""
    }

}
