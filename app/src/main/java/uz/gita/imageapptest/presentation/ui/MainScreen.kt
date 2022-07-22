package uz.gita.imageapptest.presentation.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import uz.gita.imageapptest.R
import uz.gita.imageapptest.data.local.model.DetailsData
import uz.gita.imageapptest.data.local.model.MainHorizontalData
import uz.gita.imageapptest.data.local.model.MainVerticalData
import uz.gita.imageapptest.databinding.ScreenMainBinding
import uz.gita.imageapptest.presentation.ui.adapter.HorizontalAdapter
import uz.gita.imageapptest.presentation.ui.adapter.HorizontalWrapperAdapter
import uz.gita.imageapptest.presentation.ui.adapter.VerticalAdapter
import uz.gita.imageapptest.presentation.viewmodels.main.MainViewModel
import uz.gita.imageapptest.presentation.viewmodels.main.MainViewModelImpl
import uz.gita.imageapptest.utils.setUiMode
import uz.gita.imageapptest.utils.snackErrorMessage

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main), MenuProvider {

    private val viewBinding by viewBinding(ScreenMainBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }

    private val horizontalAdapter by lazy { HorizontalAdapter() }
    private val horizontalWrapperAdapter by lazy { HorizontalWrapperAdapter(horizontalAdapter) }
    private val verticalAdapter by lazy { VerticalAdapter() }
    private val concatAdapter: ConcatAdapter by lazy {
        val config = ConcatAdapter
            .Config
            .Builder()
            .apply {
                setIsolateViewTypes(false)
            }.build()
        ConcatAdapter(config, horizontalWrapperAdapter, verticalAdapter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.apply {
            networkErrorLiveData.observe(this@MainScreen, networkErrorLiveDataObserver)
            localErrorLiveData.observe(this@MainScreen, localErrorLiveDataObserver)
            navigateDetailsScreenLiveData.observe(
                this@MainScreen,
                navigateDetailsScreenLiveDataObserver
            )
            navigateLanguageScreenLiveData.observe(
                this@MainScreen,
                navigateLanguageScreenLiveDataObserver
            )
            uiModeStateLiveData.observe(this@MainScreen, uiModeStateLiveDataObserver)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setMenu()
        viewModel.apply {
            horizontalImagesLiveData.observe(viewLifecycleOwner, horizontalImagesLiveDataObserver)
            verticalImagesLiveData.observe(viewLifecycleOwner, verticalImagesLiveDataObserver)
            countLiveData.observe(viewLifecycleOwner, countLiveDataObserver)
        }
        horizontalAdapter.setOnItemClickListener { id ->
            Timber.d("id: $id")
            viewModel.onClickHorizontalItem(id)
        }
        verticalAdapter.setOnItemClickListener { id ->
            Timber.d("id: $id")
            viewModel.onClickVerticalItem(id)
        }
    }

    private fun setMenu() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(viewBinding.toolbar)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private val networkErrorLiveDataObserver = Observer<String> {
        snackErrorMessage(it)
    }

    private val localErrorLiveDataObserver = Observer<Int> {
        snackErrorMessage(resources.getString(it))
    }

    private val horizontalImagesLiveDataObserver = Observer<List<MainHorizontalData>> {
        horizontalAdapter.submitList(it)
    }

    private val verticalImagesLiveDataObserver = Observer<List<MainVerticalData>> {
        verticalAdapter.submitList(it)
    }

    private val countLiveDataObserver = Observer<Int> { count ->
        if (count == 0) return@Observer
        val layoutManager = GridLayoutManager(requireContext(), count)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (concatAdapter.getItemViewType(position)) {
                    HorizontalAdapter.VIEW_TYPE -> count
                    VerticalAdapter.VIEW_TYPE -> count
                    else -> count
                }
            }
        }
        viewBinding.apply {
            listImages.layoutManager = layoutManager
            listImages.adapter = concatAdapter
        }
    }

    private val navigateDetailsScreenLiveDataObserver = Observer<DetailsData> {
        navController.navigate(MainScreenDirections.actionMainScreenToDetailsScreen(it))
    }

    private val navigateLanguageScreenLiveDataObserver = Observer<Unit> {
        navController.navigate(R.id.action_mainScreen_to_languageScreen)
    }

    private val uiModeStateLiveDataObserver = Observer<Boolean> { uiModeState ->
        setUiMode(uiModeState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        horizontalWrapperAdapter.onSaveState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let { horizontalWrapperAdapter.onRestoreState(it) }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.main_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.menuLanguage -> {
                viewModel.onClickLanguageButton()
                true
            }
            R.id.menuUIMode -> {
                viewModel.onClickUIModeButton()
                true
            }
            else -> false
        }
    }

}