package com.example.rafaltekielskibmicalculator

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rafaltekielskibmicalculator.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bmiValue = arguments?.getDouble("bmi_value")
        if (bmiValue != null) {
            binding.tvBMIValue.text = getString(R.string.your_bmi_2f).format(bmiValue)

            when {
                bmiValue < 18.5 -> {
                    binding.tvBMICategory.text = getString(R.string.underweight)
                    binding.tvBMICategory.setTextColor(Color.BLUE)
                    binding.ivBMICategoryImage.setImageResource(R.drawable.underweight)
                }

                bmiValue in 18.5..24.9 -> {
                    binding.tvBMICategory.text = getString(R.string.healthy_weight)
                    binding.tvBMICategory.setTextColor(Color.GREEN)
                    binding.ivBMICategoryImage.setImageResource(R.drawable.normal)
                }

                bmiValue in 25.0..29.9 -> {
                    binding.tvBMICategory.text = getString(R.string.overweight)
                    binding.tvBMICategory.setTextColor(Color.rgb(255, 165, 0))
                    binding.ivBMICategoryImage.setImageResource(R.drawable.overweight)
                }

                else -> {
                    binding.tvBMICategory.text = getString(R.string.obesity)
                    binding.tvBMICategory.setTextColor(Color.RED)
                    binding.ivBMICategoryImage.setImageResource(R.drawable.obesity)
                }
            }
        }

        binding.btnBackToMainFragment.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_mainFragment)
        }
    }
}
