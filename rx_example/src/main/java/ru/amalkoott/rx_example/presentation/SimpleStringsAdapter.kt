package ru.amalkoott.rx_example.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.subjects.PublishSubject
import ru.amalkoott.rx_example.databinding.ItemLayoutBinding

class SimpleStringsAdapter(
    //private val items : List<String>,
    private val onItemClick : (Int) -> Unit
) : ListAdapter<String, SimpleStringsAdapter.ViewHolder>(DiffCallback()){
//) : RecyclerView.Adapter<SimpleStringsAdapter.ViewHolder>() {

    class DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.item.text = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }

    /*
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            with(holder.binding.item){
                text = items[position]
                setOnClickListener{
                    onItemClick(position)
                }
            }
        }
    */
   // override fun getItemCount(): Int = items.size

}