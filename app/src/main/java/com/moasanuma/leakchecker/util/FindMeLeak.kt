package com.moasanuma.leakchecker.util

fun findMeLeak(mePass: String, leakRaw: String): Int {
    val leakList = leakRaw.split("\n")
    for (leak in leakList) {
        if (mePass.substring(5) == leak.substring(0, 35))
            return leak.substring(36).dropLast(1).toInt()
    }
    return 0
}
