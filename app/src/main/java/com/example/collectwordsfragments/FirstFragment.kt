package com.example.collectwordsfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.collectwordsfragments.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val words = ArrayList<String>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonSave.setOnClickListener{
            var word = binding.editTextFirst.text.trim().toString()
            if (word.isEmpty()){
                binding.editTextFirst.error = "Input word"
                return@setOnClickListener
            }
            words.add(word)
            binding.editTextFirst.text.clear()
        }

        binding.buttonShow.setOnClickListener {
            val wordsArray = words.toTypedArray()
            if(wordsArray.isEmpty()){
                val snack = Snackbar.make(it, "Your list is empty!", Snackbar.LENGTH_LONG)
                snack.show()
            }
            if (wordsArray.isNotEmpty()){
                val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(wordsArray)
                findNavController().navigate(action)
            }
        }

        binding.buttonClear.setOnClickListener {
            val snack = Snackbar.make(it, "You cleared the list!", Snackbar.LENGTH_LONG)
            snack.show()
            words.clear()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}