package com.example.clinicwsrapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.clinicwsrapp.R
import com.example.clinicwsrapp.common.datamodels.OnBoardingData
import com.example.clinicwsrapp.databinding.FragmentOnBoardingBinding
import com.example.clinicwsrapp.databinding.FragmentOnBoardingInfoBinding
import com.example.clinicwsrapp.viewmodel.OnBoardingViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class OnBoardingFragment: Fragment(R.layout.fragment_on_boarding) {
    private lateinit var binding: FragmentOnBoardingBinding
    @Inject
    lateinit var viewModel: OnBoardingViewModel
    private var nowSelectedTab = -1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
//        binding.viewPager.setAdapter(BetterAdapter(requireActivity(), vie))
        binding.viewPager.adapter = Adapter(requireActivity() as MainActivity, viewModel, object: Navigator{
            override fun goNext() {
                binding.viewPager.currentItem += 1
            }

            override fun end() {
                (requireActivity() as MainActivity).goTo(R.id.authFragment)
            }

        }, requireParentFragment())

        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab, position ->
            tab.icon = resources.getDrawable(R.drawable.circle_tab)
            tab.tag = position
        }.attach()
        (binding.tabLayout.getChildAt(0) as? ViewGroup)?.getChildAt(0)?.isEnabled = false
        (binding.tabLayout.getChildAt(0) as? ViewGroup)?.getChildAt(1)?.isEnabled = false
        (binding.tabLayout.getChildAt(0) as? ViewGroup)?.getChildAt(2)?.isEnabled = false
        return binding.root
    }

    class Adapter(val activity: MainActivity, val viewModel: OnBoardingViewModel, val navigator: Navigator, fragment: Fragment): FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            Log.d("test", "creating fragment")
            var data: OnBoardingData? = null
            viewModel.updateOnBoardingData()
            viewModel.onBoardingData.observe(activity){
                data = it!!
            }

            var last: Boolean? = null
            viewModel.updateNowSize()
            viewModel.nowSize.observe(activity){
                last = it == 1
            }
            while (data == null || last == null){
            }
            viewModel.dropItem()
            return OnBoardingInfoFragment(data!!, last!!, navigator)

        }
    }

}