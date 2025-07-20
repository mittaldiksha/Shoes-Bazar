package com.example.ecommerceappshoes.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ecommerceappshoes.Model.BrandModel
import com.example.ecommerceappshoes.Model.ItemModel
import com.example.ecommerceappshoes.Model.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainRepository {
    private val firebaseDatabase= FirebaseDatabase.getInstance()

    private val _brands=MutableLiveData<MutableList<BrandModel>>()
    private val _popular=MutableLiveData<MutableList<ItemModel>>()
    private val _banners=MutableLiveData<List<SliderModel>>()


    val brands: LiveData<MutableList<BrandModel>> get() = _brands
    val banners: MutableLiveData<List<SliderModel>> get() = _banners
    val popular: LiveData<MutableList<ItemModel>> get() = _popular


    fun loadBrands(){
        val ref = firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list= mutableListOf<BrandModel>()
                for(childSnapshot in snapshot.children){
                    childSnapshot.getValue(BrandModel::class.java)?.let {
                        list.add(it)
                    }
                }
                _brands.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MainRepository", "Failed to load brands: ${error.message}")
            }

        })
    }

    fun loadPopular(){
        val ref = firebaseDatabase.getReference("Items")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list= mutableListOf<ItemModel>()
                for(childSnapshot in snapshot.children){
                    childSnapshot.getValue(ItemModel::class.java)?.let {
                        list.add(it)
                    }
                }
                _popular.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MainRepository", "Failed to load brands: ${error.message}")
            }

        })
    }

    fun loadBanners() {
        val ref = firebaseDatabase.getReference("Banner")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<SliderModel>()
                for (childSnapshot in snapshot.children) {
                    childSnapshot.getValue(SliderModel::class.java)?.let {
                        list.add(it)
                    }
                }
                _banners.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MainRepository", "Failed to load banners: ${error.message}")
            }
        })
    }

}
