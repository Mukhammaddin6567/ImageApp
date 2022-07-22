package uz.gita.imageapptest.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.imageapptest.R
import uz.gita.imageapptest.data.local.model.MainHorizontalData
import uz.gita.imageapptest.databinding.ItemHorizontalBinding

class HorizontalAdapter :
    ListAdapter<MainHorizontalData, HorizontalAdapter.Holder>(HorizontalDiffUtils) {

    private var onItemClick: ((id: String) -> Unit)? = null

    companion object {
        const val VIEW_TYPE = 1
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE
    }

    private object HorizontalDiffUtils : DiffUtil.ItemCallback<MainHorizontalData>() {
        override fun areItemsTheSame(
            oldItem: MainHorizontalData,
            newItem: MainHorizontalData
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: MainHorizontalData,
            newItem: MainHorizontalData
        ): Boolean = oldItem == newItem

    }

    inner class Holder(private val viewBinding: ItemHorizontalBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(absoluteAdapterPosition).id)
            }
        }

        fun bind(): MainHorizontalData = with(viewBinding) {
            getItem(absoluteAdapterPosition).apply {
                Glide
                    .with(itemView.context)
                    .load(downloadUrl)
                    .placeholder(R.drawable.ic_image)
                    .into(image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = ItemHorizontalBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal, parent, false)
        )
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind()
    }

    fun setOnItemClickListener(block: (id: String) -> Unit) {
        onItemClick = block
    }

}