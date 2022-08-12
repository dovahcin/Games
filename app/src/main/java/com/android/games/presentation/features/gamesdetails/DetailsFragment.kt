package com.android.games.presentation.features.gamesdetails

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgument
import androidx.recyclerview.widget.GridLayoutManager
import com.android.games.R
import com.android.games.databinding.FragmentDetailsBinding
import com.android.games.domain.DetailsDataModel
import com.android.games.presentation.features.gamesdetails.adapter.*
import com.android.games.presentation.loadImage
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val detailsViewModel: DetailsViewModel by viewModel()

    private val imageClick: (String) -> Unit = { url ->
        val extras = FragmentNavigatorExtras(
            binding.recyclerScreenShots to "image_fullsize"
        )
        findNavController().navigate(
            DetailsFragmentDirections.actionDetailsFragmentToFullSizeImageFragment(url), extras
        )
        detailsViewModel.saveFullFragmentState()
    }

    private val developersAdapter = DevelopersAdapter()
    private val platformsAdapter = PlatformsAdapter()
    private val publishersAdapter = PublishersAdapter()
    private val screenShotsAdapter = ScreenShotsAdapter(imageClick)
    private val storesAdapter = StoresAdapter()
    private val tagsAdapter = TagsAdapter()


    private val navArgs: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        detailsViewModel.getDetailsForView(navArgs.gameId)

        binding.recyclerDevs.adapter = developersAdapter
        binding.recyclerPlatforms.adapter = platformsAdapter
        binding.recyclerScreenShots.adapter = screenShotsAdapter
        binding.recyclerPublishers.adapter = publishersAdapter
        binding.recyclerStores.adapter = storesAdapter

        binding.recyclerTags.apply {
            adapter = tagsAdapter
            layoutManager = GridLayoutManager(context, 3)
        }

        fetchDataForList()

        return binding.root
    }

    private fun fetchDataForList() {
        lifecycleScope.launch {
            detailsViewModel.uiState.collect { uiState ->
                when (uiState) {
                    is DetailsUiState.Success -> {
                        showDetails(uiState.detailsDataModel)
                    }
                    is DetailsUiState.Failure -> showError(uiState.exception)
                }
                showLoadingView(uiState is DetailsUiState.Loading)
            }
        }
    }

    private fun showLoadingView(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
        binding.scrollView.isVisible = !isVisible
    }

    private fun showError(exception: Throwable) {
        Snackbar.make(binding.root, exception.localizedMessage!!, 10000).show()
    }

    private fun showDetails(detailsDataModel: DetailsDataModel) {
        val results = detailsDataModel.screenShots.results

        developersAdapter.update(detailsDataModel.gameDetails.developers)
        platformsAdapter.update(detailsDataModel.gameDetails.platforms)
        publishersAdapter.update(detailsDataModel.gameDetails.publishers)
        screenShotsAdapter.update(results)
        storesAdapter.update(detailsDataModel.gameDetails.stores)
        tagsAdapter.update(detailsDataModel.gameDetails.tags)

        binding.details = detailsDataModel.gameDetails

        if (detailsDataModel.gameDetails.esrb_rating == null) {
            binding.textEsrb.isVisible = false
            binding.titleEsrb.isVisible = false
        }

        showDescriptionText(detailsDataModel)

        binding.banner.loadImage(detailsDataModel.gameDetails.background_image)
    }


    private fun showDescriptionText(detailsDataModel: DetailsDataModel) {
        val spannableString =
            SpannableStringBuilder("${detailsDataModel.gameDetails.description_raw.take(150)}...\n show more")
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                spannableString.clear()
                spannableString.append(detailsDataModel.gameDetails.description_raw)
                binding.textDesc.text = spannableString
            }
        }


        if (detailsDataModel.gameDetails.description_raw.length > 200) {
            spannableString.setSpan(clickableSpan, 154, 164, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            binding.textDesc.text = spannableString
        } else
            binding.textDesc.text = detailsDataModel.gameDetails.description_raw

        binding.textDesc.movementMethod = LinkMovementMethod.getInstance()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
