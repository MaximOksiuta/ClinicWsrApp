package com.example.clinicwsrapp.view

import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.PaintDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.clinicwsrapp.R
import com.example.clinicwsrapp.databinding.FragmentSplashBinding
import com.example.clinicwsrapp.viewmodel.OnBoardingViewModel
import dagger.Lazy
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment: Fragment(R.layout.fragment_splash) {
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        FillCustomGradient(binding.splashFrgLt)
        object: CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                (requireActivity() as MainActivity).goTo(R.id.onBoardingFragment)
            }
        }.start()

        return binding.root
    }

    private fun FillCustomGradient(v: View) {
        val view: View = v
        val layers = arrayOfNulls<Drawable>(2)
        val sf: ShapeDrawable.ShaderFactory = object : ShapeDrawable.ShaderFactory() {
            override fun resize(width: Int, height: Int): Shader {
                return LinearGradient(
                    0f,
                    0f,
                    0f,
                    view.height.toFloat(),
                    intArrayOf(
                        resources.getColor(R.color.transparent_light_blue),  // please input your color from resource for color-4
                        resources.getColor(R.color.light_blue),
                        resources.getColor(R.color.blue),
                        resources.getColor(R.color.light_blue),
                        resources.getColor(R.color.transparent_light_blue)
                    ),
                    floatArrayOf(0f, 0.33f, 0.50f, 0.83f, 1f),
                    Shader.TileMode.CLAMP
                )
            }
        }
        val sf2: ShapeDrawable.ShaderFactory = object : ShapeDrawable.ShaderFactory(){
            override fun resize(width: Int, height: Int): Shader {
                return LinearGradient(
                    0f,
                    0f,
                    0f,
                    view.height.toFloat(),
                    intArrayOf(
                        resources.getColor(R.color.cyan_light),
                        resources.getColor(R.color.cyan),
                        resources.getColor(R.color.cyan_light)
                    ),
                    floatArrayOf(0f, 0.5f, 1f),
                    Shader.TileMode.CLAMP
                )
            }
        }


        val p2 = PaintDrawable()
        p2.shape = RectShape()
        p2.shaderFactory = sf2
        val p = PaintDrawable()
        p.shape = RectShape()
        p.shaderFactory = sf
//        p.setCornerRadii(floatArrayOf(5f, 5f, 5f, 5f, 0f, 0f, 0f, 0f))
        layers[0] = p2 as Drawable
        layers[1] = p as Drawable
//        layers[2] = CustomDrawable(resources.getColor(R.color.circle_color))
        val composite = LayerDrawable(layers)
        view.setBackgroundDrawable(composite)
    }
}