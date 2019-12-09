package com.android.ui

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.R
import com.android.model.Facts
import com.android.utils.SharePreferenceUtils
import com.android.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    private var mainViewModel: MainViewModel? = null
    internal var mBlogAdapter: FactsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "Activity created!")
        SharePreferenceUtils.init(applicationContext)
        toolbar_actionbar!!.title = resources.getString(R.string.toolbar_title)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        getPopularBlog()
        swipe_refresh!!.setOnRefreshListener { getPopularBlog() }
    }

    fun getPopularBlog() {
        swipe_refresh!!.isRefreshing = true
        mainViewModel!!.allBlog.observe(this, Observer { blogList ->
            swipe_refresh!!.isRefreshing = false
            Log.d(TAG, "Response Sucess")
            prepareRecyclerView(blogList)
            toolbar_actionbar!!.title = SharePreferenceUtils.read(SharePreferenceUtils.TITLE, null)
        })
    }

    private fun prepareRecyclerView(blogList: List<Facts>?) {
        mBlogAdapter = FactsAdapter(blogList)
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            blogRecyclerView!!.layoutManager = LinearLayoutManager(this)
        } else {
            blogRecyclerView!!.layoutManager = GridLayoutManager(this, 4)

        }
        // mRecyclerView!!.itemAnimator = DefaultItemAnimator()
        blogRecyclerView!!.adapter = mBlogAdapter
        mBlogAdapter?.notifyDataSetChanged()

    }

}
