package ir.behzadnematzadeh.githubrepo.ui.main

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.behzadnematzadeh.githubrepo.R
import ir.behzadnematzadeh.githubrepo.databinding.FragmentMainBinding
import ir.behzadnematzadeh.githubrepo.databinding.RecyclerRowBinding
import ir.behzadnematzadeh.githubrepo.model.UserRepo

class MainAdapter(
    private val listener: (UserRepo) -> Unit
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var items: List<UserRepo> = listOf()

    override fun getItemCount(): Int {
        return items.size
    }

    private var _binding: RecyclerRowBinding? = null
    private val binding: RecyclerRowBinding
        get() = requireNotNull(_binding) { "You can't touch binding when it's null" }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        _binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(items[position], listener)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(items: List<UserRepo>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(private val view: RecyclerRowBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(
            repository: UserRepo,
            listener: (UserRepo) -> Unit
        ) {
            with(itemView) {
                view.rMainTxt.text = repository.name
                setOnClickListener { listener(repository) }
            }
        }
    }
}