package com.android.games.presentation.features.fullimage

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.android.games.R
import com.android.games.databinding.FragmentFullSizeImageBinding
import com.android.games.presentation.loadImage


class FullSizeImageFragment : Fragment() {
    private var _binding: FragmentFullSizeImageBinding? = null
    private val binding get() = _binding!!

    private val navArgs: FullSizeImageFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_full_size_image, container, false)

        val animation = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move)

        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation

        binding.imageFullSize.loadImage(navArgs.imageUrl)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}