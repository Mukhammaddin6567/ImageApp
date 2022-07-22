package uz.gita.imageapptest.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.imageapptest.R
import uz.gita.imageapptest.data.local.model.LanguageData
import uz.gita.imageapptest.databinding.ItemLanguageBinding

class LanguageAdapter :
    ListAdapter<LanguageData, LanguageAdapter.LanguageViewHolder>(LanguageDiffUtils) {

    private var onChangeLanguageListener: ((language: String) -> Unit)? = null

    private object LanguageDiffUtils : DiffUtil.ItemCallback<LanguageData>() {
        override fun areItemsTheSame(oldItem: LanguageData, newItem: LanguageData): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: LanguageData, newItem: LanguageData): Boolean =
            oldItem == newItem
    }

    inner class LanguageViewHolder(private val viewBinding: ItemLanguageBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        init {
            viewBinding.root.setOnClickListener {
                getItem(absoluteAdapterPosition).apply {
                    onChangeLanguageListener?.invoke(code)
                }
            }
            viewBinding.buttonIsChecked.setOnClickListener {
                getItem(absoluteAdapterPosition).apply {
                    onChangeLanguageListener?.invoke(code)
                }
            }
        }

        fun bind(): LanguageData = with(viewBinding) {
            getItem(absoluteAdapterPosition).apply {
                imageIcon.text = icon
                textLanguage.text = itemView.context.getString(language)
                buttonIsChecked.isChecked = isChecked
                when (absoluteAdapterPosition) {
                    itemCount - 1 -> line.visibility = GONE
                    else -> line.visibility = VISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = ItemLanguageBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_language, parent, false)
        )
        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.bind()
    }

    fun setOnChangeLanguageListener(block: (language: String) -> Unit) {
        onChangeLanguageListener = block
    }

}