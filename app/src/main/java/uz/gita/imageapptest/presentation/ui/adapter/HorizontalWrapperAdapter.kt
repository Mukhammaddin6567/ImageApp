package uz.gita.imageapptest.presentation.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.gita.imageapptest.R
import uz.gita.imageapptest.databinding.ItemHorizontalWrapperBinding

class HorizontalWrapperAdapter(private val adapter: HorizontalAdapter) :
    RecyclerView.Adapter<HorizontalWrapperAdapter.HorizontalWrapperViewHolder>() {

    private var lastScrollX = 0

    companion object {
        private const val KEY_SCROLL_X = "horizontal.wrapper.adapter.key_scroll_x"
        const val VIEW_TYPE = 2
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE
    }

    inner class HorizontalWrapperViewHolder(private val viewBinding: ItemHorizontalWrapperBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        init {
            viewBinding.listWrapper.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            viewBinding.listWrapper.adapter = adapter
        }

        fun bind(lastScrollX: Int, onScrolled: (Int) -> Unit) {
            viewBinding.apply {
                listWrapper.doOnPreDraw {
                    listWrapper.scrollBy(lastScrollX, 0)
                }
                listWrapper.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        onScrolled(recyclerView.computeHorizontalScrollOffset())
                    }
                })
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalWrapperViewHolder {
        val view = ItemHorizontalWrapperBinding.bind(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_horizontal_wrapper, parent, false
            )
        )
        return HorizontalWrapperViewHolder(view)
    }

    override fun onBindViewHolder(holder: HorizontalWrapperViewHolder, position: Int) {
        holder.bind(lastScrollX) { x ->
            lastScrollX = x
        }
    }

    fun onSaveState(outState: Bundle) {
        outState.putInt(KEY_SCROLL_X, lastScrollX)
    }

    fun onRestoreState(savedState: Bundle) {
        lastScrollX = savedState.getInt(KEY_SCROLL_X)
    }

    override fun getItemCount(): Int = 1

}