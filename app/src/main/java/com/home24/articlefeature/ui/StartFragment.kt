package com.home24.articlefeature.ui


import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.home24.R
import com.home24.infrastructure.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_start.*

/**
 * A simple [Fragment] subclass.
 */
class StartFragment : BaseFragment() {
    private var totalArticles = 0

    override val layoutResourceId: Int = R.layout.fragment_start
    override var displayToolbar = false

    //region overriden methods
    override fun ignite(bundle: Bundle?) {
        val navController = findNavController()
        btn_start.setOnClickListener {

            edt_total_articles.text.toString().trim().let {
                if(it.isNotEmpty()){
                    totalArticles = it.toInt()
                }
            }
            if( totalArticles < 10 || totalArticles > 100){
                Toast.makeText(activity,R.string.invalid_article_count,Toast.LENGTH_LONG).show()
            }else{
                navController.navigate(R.id.toProductListFragment,bundleOf(getString(R.string.total_articles) to totalArticles))
            }
        }
    }
    //endregion
}
