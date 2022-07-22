package uz.gita.imageapptest.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.imageapptest.R
import uz.gita.imageapptest.data.local.model.MainVerticalData
import uz.gita.imageapptest.databinding.ItemVerticalBinding

class VerticalAdapter : ListAdapter<MainVerticalData, VerticalAdapter.Holder>(VerticalDiffUtils) {

    private var onItemClick: ((id: String) -> Unit)? = null

    companion object {
        const val VIEW_TYPE = 3
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE
    }

    private object VerticalDiffUtils : DiffUtil.ItemCallback<MainVerticalData>() {
        override fun areItemsTheSame(
            oldItem: MainVerticalData,
            newItem: MainVerticalData
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: MainVerticalData,
            newItem: MainVerticalData
        ): Boolean = oldItem == newItem
    }

    inner class Holder(private val viewBinding: ItemVerticalBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        init {
            itemView.setOnClickListener {
                if (absoluteAdapterPosition == itemCount) return@setOnClickListener
                onItemClick?.invoke(getItem(absoluteAdapterPosition).id)
            }
        }

        fun bind() {
            getItem(absoluteAdapterPosition).apply {
                Glide
                    .with(itemView.context)
                    .load(downloadUrl)
                    .placeholder(R.drawable.ic_image)
                    .into(viewBinding.image)
                viewBinding.textAuthor.text = author
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = ItemVerticalBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_vertical, parent, false)
        )
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        if (position == itemCount - 1) return
        holder.bind()
    }

    fun setOnItemClickListener(block: (id: String) -> Unit) {
        onItemClick = block
    }

}