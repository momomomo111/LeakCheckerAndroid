package com.moasanuma.leakchecker.util

import java.security.MessageDigest

fun hashSHA1String(target: String): String {
    val hashBytes = MessageDigest.getInstance("SHA-1").digest(target.toByteArray())
    val hexChars = "0123456789ABCDEF"
    val result = StringBuilder(hashBytes.size * 2)
    hashBytes.forEach {
        val i = it.toInt()
        result.append(hexChars[i shr 4 and 0x0f])
        result.append(hexChars[i and 0x0f])
    }
    return result.toString()
}
