package dingshaoshuai.network.util

import android.util.Log
import dingshaoshuai.network.commonIsDebug
import dingshaoshuai.network.commonLogTagError
import dingshaoshuai.network.commonLogTagHttp
import dingshaoshuai.network.commonLogTagTest
import com.google.gson.Gson

/**
 * @author: Xiao Bo
 * @date: 18/7/2020
 */


fun logHttp(msg: String) {
    if (commonIsDebug) {
        Log.i(commonLogTagHttp, msg)
    }
}

fun logV(tag: String, msg: String) {
    if (commonIsDebug) {
        Log.v(tag, msg)
    }
}

fun logD(tag: String, msg: String) {
    if (commonIsDebug) {
        Log.d(tag, msg)
    }
}

fun logI(msg: String) {
    logI(commonLogTagTest, msg)
}

fun logI(any: Any) {
    logI(commonLogTagTest, Gson().toJson(any))
}

fun logI(tag: String, msg: String) {
    if (commonIsDebug) {
        Log.i(tag, msg)
    }
}

fun logW(tag: String, msg: String) {
    if (commonIsDebug) {
        Log.w(tag, msg)
    }
}

fun logE(msg: String) {
    logE(commonLogTagError, msg)
}

fun logE(tag: String, msg: String) {
    if (commonIsDebug) {
        Log.e(tag, msg)
    }
}