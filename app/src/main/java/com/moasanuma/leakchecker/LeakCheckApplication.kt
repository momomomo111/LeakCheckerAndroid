package com.moasanuma.leakchecker

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LeakCheckApplication : Application()