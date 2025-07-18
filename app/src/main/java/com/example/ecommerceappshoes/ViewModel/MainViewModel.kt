package com.example.ecommerceappshoes.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceappshoes.Model.BrandModel
import com.example.ecommerceappshoes.Repository.MainRepository

class MainViewModel: ViewModel() {
    private val repository= MainRepository()

    val brands: LiveData<MutableList<BrandModel>> =repository.brands

    fun loadBrands() = repository.loadBrands()
}