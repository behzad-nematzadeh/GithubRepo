package ir.behzadnematzadeh.githubrepo.ui.repo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import ir.behzadnematzadeh.githubrepo.databinding.RecyclerItemBinding

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
internal class RepoItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding = RecyclerItemBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    var repoName: String? = null
        @ModelProp set(value) {
            field = value
            binding.txtRepoName.text = value
        }

    var cardClick: OnClickListener? = null
        @CallbackProp set(value) {
            field = value
            binding.cardView.setOnClickListener(value)
        }
}