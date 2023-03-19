package com.cfxc.common.utils

import android.content.res.Resources
import android.util.TypedValue
import com.cfxc.common.base.BaseBean
import java.lang.IllegalStateException


/**
 * @description
 * @author: created by peihao.feng
 * @date: 2021/9/8
 */
object Utils {

    inline fun <reified T> Resources.dpToPx(value: Float): T {
        val applyDimension =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, displayMetrics)
        return when (T::class) {
            Float::class -> applyDimension as T
            Int::class -> applyDimension.toInt() as T
            else -> throw IllegalStateException("Type not supported")
        }
    }

    fun BaseBean.entityToMap(entity: Any): HashMap<String, Any?> {
        val hashMap = HashMap<String, Any?>()
        val declaredFields = entity.javaClass.declaredFields
        for (field in declaredFields) {
            val name = field.name
            val accessible = field.isAccessible
            field.isAccessible = true
            try {
                val value = field.get(entity)
                hashMap[name] = value
                field.isAccessible = accessible
            } catch (e: Exception) {

            }
        }
        return hashMap
    }

    inline fun <reified T> Map<String, Any>.mapToEntity(): T {
        val entity = T::class.java.newInstance()
        val declaredFields = T::class.java.declaredFields
        for (field in declaredFields) {
            val name = field.name
            val accessible = field.isAccessible
            field.isAccessible = true
            try {
                field.set(entity, this[name])
                field.isAccessible = accessible
            } catch (e: Exception) {

            }
        }
        return entity
    }
}