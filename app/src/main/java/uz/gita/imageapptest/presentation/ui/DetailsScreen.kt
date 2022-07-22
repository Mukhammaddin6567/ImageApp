package uz.gita.imageapptest.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.imageapptest.R
import uz.gita.imageapptest.databinding.ScreenDetailsBinding
import uz.gita.imageapptest.presentation.viewmodels.details.DetailsViewModel
import uz.gita.imageapptest.presentation.viewmodels.details.DetailsViewModelImpl

@AndroidEntryPoint
class DetailsScreen : Fragment(R.layout.screen_details) {

    private val args by navArgs<DetailsScreenArgs>()
    private val viewBinding by viewBinding(ScreenDetailsBinding::bind)
    private val viewModel: DetailsViewModel by viewModels<DetailsViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.apply {
            popBackStackLiveData.observe(this@DetailsScreen, popBackStackLiveDataObserver)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.apply {
            buttonBack.setOnClickListener {
                viewModel.onClickBack()
            }
            args.details.apply {
                Glide
                    .with(requireContext())
                    .load(downloadUrl)
                    .placeholder(R.drawable.ic_image)
                    .into(image)
                textAuthor.text = resources.getString(R.string.author, author)
                textHeight.text = resources.getString(R.string.height, height)
                textWidth.text = resources.getString(R.string.width, width)
                textURL.text = resources.getString(R.string.url, url)
                textDownloadURL.text = resources.getString(R.string.download_url, downloadUrl)
            }
        }
    }

    private val popBackStackLiveDataObserver = Observer<Unit> {
        findNavController().popBackStack()
    }

}