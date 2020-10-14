package com.whdx.home.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import com.whdx.home.R
import com.wwy.android.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_topic_detail.*

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/14 0014 11:02
 */
class TopicDetailActivity: BaseActivity() {
    override fun setLayoutId()= R.layout.activity_topic_detail

    override fun initView(savedInstanceState: Bundle?) {

        titleBar.setOnLeftClickListener { finish() }

    }

    override fun initData() {
        val url = intent.extras?.getString("url")
        val title = intent.extras?.getString("title")
        titleBar.findViewById<TextView>(R.id.tvTitleNavigationBar).text = title
        webContainer.text = Html.fromHtml(url)
    }

    companion object{
        fun start(context: Context, url:String, title:String){
            context.startActivity(
                Intent(context, TopicDetailActivity::class.java).apply {
                    putExtra("url",url)
                    putExtra("title",title)
                }
            )
        }
    }
}