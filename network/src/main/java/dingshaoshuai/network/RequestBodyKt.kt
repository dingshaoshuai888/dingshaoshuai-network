package dingshaoshuai.network

import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * @author: Xiao Bo
 * @date: 1/9/2021
 */
fun Map<String, String>.toRequestBody(): RequestBody {
    val content = Gson().toJson(this)
    val mediaType = MediaType.parse("application/json;charset=UTF8")
    return RequestBody.create(mediaType, content)
}

