package com.example.ecommerceappshoes.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceappshoes.Model.BrandModel
import com.example.ecommerceappshoes.Model.ItemModel
import com.example.ecommerceappshoes.Model.SliderModel
import com.example.ecommerceappshoes.Repository.MainRepository

class MainViewModel: ViewModel() {
    private val repository= MainRepository()

    val brands: LiveData<MutableList<BrandModel>> =repository.brands
    val popular: LiveData<MutableList<ItemModel>> =repository.popular
    val banners: MutableLiveData<List<SliderModel>> =repository.banners


    fun loadBrands() = repository.loadBrands()
    fun loadPopular() = repository.loadPopular()
    fun loadBanners() = repository.loadBanners()
}