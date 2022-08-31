package ir.behzadnematzadeh.githubrepo.ui.repo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import ir.behzadnematzadeh.githubrepo.databinding.LoadingItemBinding

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
internal class RepoLoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding = LoadingItemBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    var isFullScreen: Boolean = false
        @ModelProp set(value) {
            field = value
            binding.parent.layoutParams.height =
                if (value) ViewGroup.LayoutParams.MATCH_PARENT
                else ViewGroup.LayoutParams.WRAP_CONTENT
        }
}