package com.example.rafaltekielskibmicalculator

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rafaltekielskibmicalculator.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCalculateBMI.setOnClickListener {
            val weight = binding.etWeight.text.toString().toDoubleOrNull()
            val height = binding.etHeight.text.toString().toDoubleOrNull()

            if (weight != null && height != null && height > 0) {
                val heightInMeters = height / 100
                val bmi = weight / (heightInMeters * heightInMeters)
                binding.tvErrorMessage.visibility = View.GONE
                val bundle = Bundle().apply {
                    putDouble("bmi_value", bmi)
                }
                findNavController().navigate(
                    R.id.action_mainFragment_to_resultFragment,
                    bundle
                )
            } else {
                val error = "Please enter valid positive numbers for height and weight."
                binding.tvErrorMessage.text = error
                binding.tvErrorMessage.visibility = View.VISIBLE
                handler.postDelayed({
                    binding.tvErrorMessage.visibility = View.GONE
                }, 3000)
                showErrorToast(binding.root.context, error)
            }
        }
    }

    private fun showErrorToast(context: android.content.Context, message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)

        val toastView = toast.view
        val textView = toastView?.findViewById<TextView>(android.R.id.message)
        textView?.textSize = 18f
        textView?.setTextColor(android.graphics.Color.WHITE)

        toastView?.setBackgroundResource(android.R.color.holo_red_light)
        toast.setGravity(android.view.Gravity.TOP or android.view.Gravity.CENTER_HORIZONTAL, 0, 100)
        toast.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null)
    }
}
