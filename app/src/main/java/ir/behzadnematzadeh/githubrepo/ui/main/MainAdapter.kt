package ir.behzadnematzadeh.githubrepo.ui.main

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.behzadnematzadeh.githubrepo.R
import ir.behzadnematzadeh.githubrepo.model.UserRepo

class MainAdapter(
        private val listener: (UserRepo) -> Unit
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var items: List<UserRepo> = listOf()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.recycler_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
            holder: ViewHolder,
            position: Int
    ) {
        holder.bind(items[position], listener)
    }

    fun addAll(items: List<UserRepo>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(
                repository: UserRepo,
                listener: (UserRepo) -> Unit
        ) {
            with(itemView) {
                //r_main_txt.text = repository.name
                setOnClickListener { listener(repository) }
            }
        }
    }
}