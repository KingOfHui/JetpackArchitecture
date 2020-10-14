package com.whdx.base.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.view.ViewGroup
import android.webkit.ConsoleMessage
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.widget.FrameLayout
import com.just.agentweb.*
import com.whdx.base.R
import com.whdx.base.util.ext.color
import com.wwy.android.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_common_web.*
import kotlinx.android.synthetic.main.layout_custom_title_bar_view.view.*
import org.jetbrains.anko.backgroundColorResource
import timber.log.Timber


/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/10 0010 11:06
 */
class CommonWebActivity:BaseActivity() {

    private var agentWeb: AgentWeb? = null

    override fun setLayoutId() = R.layout.activity_common_web

    override fun initView(savedInstanceState: Bundle?) {
        agentWeb = AgentWeb.with(this)
            .setAgentWebParent(webContainer, ViewGroup.LayoutParams(-1, -1))
            .useDefaultIndicator(color(R.color.color_on_surface_60), 2)
            .interceptUnkownUrl()
//            .setMainFrameErrorView(rootView)
            .setAgentWebWebSettings(AgentWebSettingsImpl.getInstance())
            .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
            .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)
//            .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
            .setWebChromeClient(object : WebChromeClient() {
                override fun onReceivedTitle(view: WebView?, title: String?) {
//                    setTitle(title)
                    super.onReceivedTitle(view, title)
                }

                override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                    Timber.tag("WanAandroidWebView").d("${consoleMessage?.message()}")
                    return super.onConsoleMessage(consoleMessage)
                }
            })
            .setWebViewClient(object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    return super.shouldOverrideUrlLoading(view, request)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
//                    view?.loadUrl(customJs(url))
                }
            })
            .createAgentWeb()
            .ready()
            .get()
        val frameLayout: FrameLayout? = agentWeb?.getWebCreator()?.getWebParentLayout()
        frameLayout?.setBackgroundColor(resources.getColor(R.color.colorPrimary))
        agentWeb?.webCreator?.webView?.run {
            overScrollMode = WebView.OVER_SCROLL_NEVER
            settings.run {
                javaScriptCanOpenWindowsAutomatically = false
                loadsImagesAutomatically = true
                useWideViewPort = true
                loadWithOverviewMode = true
//                textZoom = SettingsStore.getWebTextZoom()
            }
            backgroundColorResource = R.color.colorPrimary
        }
        detailNv.setOnLeftClickListener { finish() }
    }

    override fun initData() {
        val url = intent.extras?.getString("url")
        val title = intent.extras?.getString("title")
        agentWeb?.urlLoader?.let {
            if (url?.startsWith("http") == true) {
                it.loadUrl(url)
            }else{
                it.loadData(url,"","")
            }
        }
        setTitle(title)
    }

    private fun setTitle(title: String?) {
        detailNv.tvTitleNavigationBar.text = title
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (agentWeb?.handleKeyEvent(keyCode, event) == true) {
            return true
        } else {
            super.onKeyDown(keyCode, event)
        }
    }

    override fun onPause() {
        agentWeb?.webLifeCycle?.onPause()
        super.onPause()

    }

    override fun onResume() {
        agentWeb?.webLifeCycle?.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        agentWeb?.webLifeCycle?.onDestroy()
        super.onDestroy()
    }


    fun refreshPage() {
        agentWeb?.urlLoader?.reload()
    }

    companion object{
        fun start(context: Context,url:String, title:String){
            context.startActivity(
                Intent(context,CommonWebActivity::class.java).apply {
                    putExtra("url",url)
                    putExtra("title",title)
                }
            )
        }
    }
}