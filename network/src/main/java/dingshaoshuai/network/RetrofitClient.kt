package dingshaoshuai.network

import android.util.LruCache
import androidx.core.util.lruCache
import dingshaoshuai.network.adapter.CoroutineCallAdapterFactory
import dingshaoshuai.network.util.isNetworkAvailable
import dingshaoshuai.network.util.logE
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException

/**
 * @author: Xiao Bo
 * @date: 28/8/2021
 */
object RetrofitClient {
    private val apiMap: LruCache<String, Any> = lruCache(10)
    private val retrofitMap: LruCache<String, Retrofit> = lruCache(10)

    fun <T> createService(clazz: Class<T>, baseUrl: String = commonBaseUrl, client: OkHttpClient = okHttpClient): T {
        val clazzKey = "${clazz.name}$baseUrl${client.hashCode()}"
        var apiService = apiMap[clazzKey]
        if (apiService == null || !clazz.isInstance(apiService)) {
            synchronized(RetrofitClient) {
                apiService = apiMap[clazzKey]
                if (apiService == null || !clazz.isInstance(apiService)) {
                    apiService = getRetrofit(baseUrl, client).create(clazz)
                    apiMap.put(clazzKey, apiService)
                }
            }
        }
        return apiService as T
    }

    inline fun <T> sendRequestForReturn(block: () -> T): T? {
        return try {
            if (isNetworkAvailable()) {
                block()
            } else {
                null
            }
        } catch (e: HttpException) {
            logE("Error Code: ${e.code()}  Error message: ${e.message()}")
            null
        } catch (e: ConnectException) {
            logE("Error catch, Error message: ${e.message}")
            null
        } catch (e: Exception) {
            logE("Error catch, Error message: ${e.message}")
            null
        }
    }

    private fun getRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
        val key = "$$baseUrl${client.hashCode()}"
        var retrofit = retrofitMap[key]
        if (retrofit == null) {
            synchronized(RetrofitClient) {
                retrofit = retrofitMap[key]
                if (retrofit == null) {
                    retrofit = createRetrofit(baseUrl, client)
                    retrofitMap.put(key, retrofit)
                }
            }
        }
        return retrofit!!
    }

    private fun createRetrofit(baseUrl: String, client: OkHttpClient) = Retrofit.Builder().apply {
        baseUrl(baseUrl)  // 设置服务器路径
        client(client)  // 设置okhttp的网络请求
        addConverterFactory(GsonConverterFactory.create())// 添加转化库,默认是Gson
        addCallAdapterFactory(CoroutineCallAdapterFactory())//添加会调库，适用kotlin协程
    }.build()
}