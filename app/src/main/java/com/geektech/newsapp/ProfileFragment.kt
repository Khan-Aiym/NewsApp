package com.geektech.newsapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.geektech.newsapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var prefs: Prefs
    private lateinit var uri:Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return (binding.root)
    }

    var activityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ActivityResultCallback<ActivityResult>(){result ->
        if (result.resultCode == Activity.RESULT_OK){
            uri = result.data?.data!!
            prefs.saveAvatar(uri.toString())
            binding.imageView.setImageURI(uri)
        }

    override fun onStart() {
        super.onStart()
        if (prefs.getAvatar() != null) {
            uri = Uri.parse(prefs.getAvatar())
            Glide.with(requireContext()).load(uri).circleCrop().into(binding.imageView)
        }
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onResume() {
        super.onResume()
        Glide.with(requireContext()).load(uri).circleCrop().into(binding.imageView)
    }
}