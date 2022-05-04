package ir.behzadnematzadeh.githubrepo.ui.repo

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ir.behzadnematzadeh.githubrepo.databinding.RecyclerItemBinding
import ir.behzadnematzadeh.githubrepo.model.UserRepo

class RepoAdapter(
    private val onItemClickListener: (UserRepo) -> Unit
) : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    private var items: List<UserRepo> = listOf()

    override fun getItemCount(): Int {
        return items.size
    }

    private var _binding: RecyclerItemBinding? = null
    private val binding: RecyclerItemBinding
        get() = requireNotNull(_binding) { "You can't touch binding when it's null" }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        _binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(items[position], onItemClickListener)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(items: List<UserRepo>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(private val view: RecyclerItemBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(
            userRepo: UserRepo,
            listener: (UserRepo) -> Unit
        ) {
            with(itemView) {
                view.rMainTxt.text = userRepo.name
                setOnClickListener { listener(userRepo) }
            }
        }
    }
}