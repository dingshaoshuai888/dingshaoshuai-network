package dingshaoshuai.network

import android.app.Application
import okhttp3.Interceptor

/**
 * @author: Xiao Bo
 * @date: 28/8/2021
 */
internal lateinit var commonApplication: Application
internal var commonIsDebug: Boolean = false
internal lateinit var commonBaseUrl: String
internal var commonConnectTimeout: Long = 30L
internal var commonWriteTimeout: Long = 30L
internal var commonReadTimeout: Long = 30L
internal var commonConnectTimeoutDownload: Long = 60L
internal var commonWriteTimeoutDownload: Long = 60L
internal var commonReadTimeoutDownload: Long = 60L
internal lateinit var commonLogTagHttp: String
internal lateinit var commonLogTagTest: String
internal lateinit var commonLogTagError: String
internal var commonIsDefaultLogInterceptor: Boolean = false
internal var commonInterceptorList: List<Interceptor>? = null

fun initNetworkLib(
    application: Application,
    baseUrl: String,
    isDebug: Boolean = false,
    isDefaultLogInterceptor: Boolean = false,
    interceptors: List<Interceptor>? = null,
    connectTimeout: Long = 30L,
    writeTimeout: Long = 30L,
    readTimeout: Long = 30L,
    connectTimeoutDownload: Long = 30L,
    writeTimeoutDownload: Long = 30L,
    readTimeoutDownload: Long = 30L,
    logTagHttp: String = "app_http",
    logTagTest: String = "app_test",
    logTagError: String = "app_error",
) {
    commonApplication = application
    commonBaseUrl = baseUrl
    commonIsDebug = isDebug
    commonIsDefaultLogInterceptor = isDefaultLogInterceptor
    if (!interceptors.isNullOrEmpty()) {
        commonInterceptorList = interceptors
    }
    commonConnectTimeout = connectTimeout
    commonWriteTimeout = writeTimeout
    commonReadTimeout = readTimeout
    commonConnectTimeoutDownload = connectTimeoutDownload
    commonWriteTimeoutDownload = writeTimeoutDownload
    commonReadTimeoutDownload = readTimeoutDownload
    commonLogTagHttp = logTagHttp
    commonLogTagTest = logTagTest
    commonLogTagError = logTagError
}