package com.example.clinicwsrapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.clinicwsrapp.R
import com.example.clinicwsrapp.common.datamodels.OnBoardingData
import com.example.clinicwsrapp.databinding.FragmentOnBoardingInfoBinding
import dagger.hilt.android.AndroidEntryPoint

interface Navigator {
    fun goNext()

    fun end()
}

@AndroidEntryPoint
class OnBoardingInfoFragment(private val data: OnBoardingData, private val last: Boolean, private val navigator: Navigator): Fragment(R.layout.fragment_on_boarding_info) {

    private lateinit var binding: FragmentOnBoardingInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding =  FragmentOnBoardingInfoBinding.inflate(inflater, container, false)
        with(binding){
            textView.text =  if (last) "Завершить" else "Пропустить"
            textView2.text = data.name
            textView3.text = data.info
            imageView5.setImageResource(data.image)
            textView.setOnClickListener {
                if (last) {
                    navigator.end()
                } else {
                    navigator.goNext()
                }
            }
        }
        return binding.root
    }
}