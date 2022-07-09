//package com.xiangxue.common.utils
//
//import java.lang.Exception
//import java.util.*
//
//object MyWebviewServiceLoader {
//
//    fun <S> load(service: Class<S>?): S? {
//        return try {
//            ServiceLoader.load(service).iterator().next()
//        } catch (e: Exception) {
//            null
//        }
//    }
//
//}