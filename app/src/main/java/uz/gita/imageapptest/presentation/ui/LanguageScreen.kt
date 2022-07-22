package uz.gita.imageapptest.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import uz.gita.imageapptest.R
import uz.gita.imageapptest.data.local.model.LanguageData
import uz.gita.imageapptest.databinding.ScreenLanguageBinding
import uz.gita.imageapptest.presentation.ui.adapter.LanguageAdapter
import uz.gita.imageapptest.presentation.viewmodels.language.LanguageViewModel
import uz.gita.imageapptest.presentation.viewmodels.language.LanguageViewModelImpl
import uz.gita.imageapptest.utils.setAppLanguage

@AndroidEntryPoint
class LanguageScreen : Fragment(R.layout.screen_language) {

    private val viewBinding by viewBinding(ScreenLanguageBinding::bind)
    private val viewModel: LanguageViewModel by viewModels<LanguageViewModelImpl>()
    private val adapter by lazy { LanguageAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.apply {
            navigateNextScreenLiveData.observe(
                this@LanguageScreen,
                navigateNextScreenLiveDataObserver
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        subscribeViewBinding(viewBinding)
        subscribeViewModel(viewModel)
    }

    private fun subscribeViewBinding(viewBinding: ScreenLanguageBinding) = with(viewBinding) {
        listLanguages.adapter = adapter
        val layoutManager = object : LinearLayoutManager(requireContext()) {
            override fun canScrollVertically(): Boolean = false
        }
        listLanguages.layoutManager = layoutManager
        adapter.setOnChangeLanguageListener { language ->
            Timber.d("setOnChangeLanguageListener: $language")
            viewModel.onLanguageSelect(language)
        }
        buttonSubmit.setOnClickListener {
            viewModel.onClickSubmit()
        }
    }

    private fun subscribeViewModel(viewModel: LanguageViewModel) = with(viewModel) {
        languagesLiveData.observe(viewLifecycleOwner, languagesLiveDataObserver)
        titleTextLiveData.observe(viewLifecycleOwner, titleTextLiveDataObserver)
        buttonTextLiveData.observe(viewLifecycleOwner, buttonTextLiveDataObserver)
    }

    private val navigateNextScreenLiveDataObserver = Observer<String> { language ->
        setAppLanguage(language, requireContext())
        findNavController().navigate(R.id.action_languageScreen_to_mainScreen)
    }

    private val languagesLiveDataObserver = Observer<List<LanguageData>> {
        adapter.submitList(it)
    }

    private val titleTextLiveDataObserver = Observer<Int> {
        viewBinding.textTitle.text = resources.getString(it)
    }

    private val buttonTextLiveDataObserver = Observer<Int> {
        viewBinding.buttonSubmit.text = resources.getString(it)
    }

}