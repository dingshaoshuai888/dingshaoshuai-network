package dingshaoshuai.network

import dingshaoshuai.network.interceptor.LogInterceptor
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * @author: Xiao Bo
 * @date: 28/8/2021
 */
val okHttpClient: OkHttpClient by lazy {

    OkHttpClient.Builder().apply {
        //设置超时时间
        connectTimeout(commonConnectTimeout, TimeUnit.SECONDS)
        writeTimeout(commonWriteTimeout, TimeUnit.SECONDS)
        readTimeout(commonReadTimeout, TimeUnit.SECONDS)
        //Header参数拦截器
        commonInterceptorList?.let {
            for (interceptor in it) {
                addInterceptor(interceptor)
            }
        }
        //日志拦截器
        if (commonIsDefaultLogInterceptor) {
            addInterceptor(LogInterceptor())
        }
        //设置连接池
        connectionPool(ConnectionPool(5, 1, TimeUnit.SECONDS))
        //默认重试一次，若需要重试N次，则要实现拦截器
        retryOnConnectionFailure(true)
    }.build()
}

val okHttpClientUpload: OkHttpClient by lazy {

    OkHttpClient.Builder().apply {
        //设置超时时间
        connectTimeout(commonConnectTimeoutDownload, TimeUnit.SECONDS)
        writeTimeout(commonWriteTimeoutDownload, TimeUnit.SECONDS)
        readTimeout(commonReadTimeoutDownload, TimeUnit.SECONDS)
        //Header参数拦截器
        commonInterceptorList?.let {
            for (interceptor in it) {
                addInterceptor(interceptor)
            }
        }
        //日志拦截器
        if (commonIsDefaultLogInterceptor) {
            addInterceptor(LogInterceptor())
        }
        //设置连接池
        connectionPool(ConnectionPool(5, 1, TimeUnit.SECONDS))
        //默认重试一次，若需要重试N次，则要实现拦截器
        retryOnConnectionFailure(true)
    }.build()
}