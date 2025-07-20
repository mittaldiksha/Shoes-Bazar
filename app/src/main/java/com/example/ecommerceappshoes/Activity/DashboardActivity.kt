package com.example.ecommerceappshoes.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.ecommerceappshoes.Adapter.BrandsAdapter
import com.example.ecommerceappshoes.Adapter.PopularAdapter
import com.example.ecommerceappshoes.Adapter.SliderAdapter
import com.example.ecommerceappshoes.Model.SliderModel
import com.example.ecommerceappshoes.R
import com.example.ecommerceappshoes.ViewModel.MainViewModel
import com.example.ecommerceappshoes.databinding.ActivityMainBinding
import com.example.ecommerceappshoes.databinding.ActivitySplashBinding

class DashboardActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(owner = this)[MainViewModel::class.java]
    }

    private lateinit var  binding: ActivityMainBinding

    private val brandsAdapter= BrandsAdapter(mutableListOf())
    private val popularAdapter= PopularAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI(){
        initBrands()
        initBanner()
        initRecommeded()
    }

    private fun initRecommeded() {
        binding.recyclerViewRecommended.layoutManager= GridLayoutManager(this,2)
        binding.recyclerViewRecommended.adapter= popularAdapter
        binding.progressBarRecommendation.visibility= View.VISIBLE

        viewModel.popular.observe(this){
            data->
            popularAdapter.updateDate(data)
            binding.progressBarRecommendation.visibility= View.GONE
        }
        viewModel.loadPopular()
    }

    private fun initBrands(){
        binding.recyclerViewBrands.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewBrands.adapter=brandsAdapter
        binding.progressBarCategory.visibility= View.VISIBLE

        viewModel.brands.observe(this){
            data->
            brandsAdapter.updateData(data)
            binding.progressBarCategory.visibility=View.GONE
        }
        viewModel.loadBrands()
    }

    private fun setupBanners(image:List<SliderModel>){
        binding.viewpageslider.apply {
            adapter= SliderAdapter(image,this)
            clipToPadding=false
            clipChildren=false
            offscreenPageLimit=3
            (getChildAt(0) as? RecyclerView)?.overScrollMode=
                RecyclerView.OVER_SCROLL_NEVER
            setPageTransformer(CompositePageTransformer().apply {
                addTransformer(MarginPageTransformer(40))
            })
        }
        binding.dotIndicator.apply {
            visibility=if (image.size>1) View.VISIBLE else View.GONE
            if (image.size>1) attachTo(binding.viewpageslider)
        }
    }

    private fun initBanner(){
        binding.progressBarBanner.visibility=View.VISIBLE
        viewModel.banners.observe(this){
            items->
            setupBanners(items)
            binding.progressBarBanner.visibility= View.GONE
        }
        viewModel.loadBanners()
    }
}