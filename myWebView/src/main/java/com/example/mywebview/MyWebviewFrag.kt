package com.example.mywebview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.base.loadsir.LoadingCallback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener


const val WEBVIEW_FRAGMENT_URL = "WEBVIEW_FRAGMENT_URL"
const val WEBVIEW_FRAGMENT_CAN_NATIVE_REFRESH = "WEBVIEW_FRAGMENT_CAN_NATIVE_REFRESH"

class XiangxueWebViewFragment : Fragment(), OnRefreshListener {
    private var mUrl: String? = null
    private var mCanNativeRefresh = true
    lateinit var mLoadService: LoadService<*>

    private var mIsError = false
    private val TAG = "WebViewFragment"

    companion object {
        fun newInstance(url: String?, canNativeRefresh: Boolean): XiangxueWebViewFragment {
            val fragment = XiangxueWebViewFragment()
            val bundle = Bundle()
            bundle.putString(WEBVIEW_FRAGMENT_URL, url)
            bundle.putBoolean(WEBVIEW_FRAGMENT_CAN_NATIVE_REFRESH, canNativeRefresh)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            mUrl = bundle.getString(WEBVIEW_FRAGMENT_URL)
            mCanNativeRefresh = bundle.getBoolean(WEBVIEW_FRAGMENT_CAN_NATIVE_REFRESH)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_webview, container, false)
        var wb = view.findViewById<WebView>(R.id.wb)
        var smartrefreshlayout = view.findViewById<SmartRefreshLayout>(R.id.smartrefreshlayout)
        mUrl?.let { wb.loadUrl(it) }
        mLoadService = LoadSir.getDefault().register(smartrefreshlayout) {
            mLoadService!!.showCallback(LoadingCallback::class.java)
            wb.reload()
        }
        smartrefreshlayout.setOnRefreshListener(this)
        smartrefreshlayout.isEnableRefresh = mCanNativeRefresh
        smartrefreshlayout.isEnableLoadMore = false
        return mLoadService.getLoadLayout()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {

    }


}