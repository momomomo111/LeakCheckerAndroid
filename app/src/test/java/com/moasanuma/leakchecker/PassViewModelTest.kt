package com.moasanuma.leakchecker

import android.os.Looper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moasanuma.leakchecker.viewmodel.PassViewModel
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows
import org.robolectric.annotation.LooperMode

@RunWith(AndroidJUnit4::class)
class PassViewModelTest {
    private lateinit var passViewModel: PassViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        passViewModel = PassViewModel()
    }

    @Test
    @LooperMode(LooperMode.Mode.PAUSED)
    fun getLeakPassList_setString() {

        val pass = "1234"
        passViewModel.getLeakPassList(pass)

        val value = passViewModel.status.getOrAwaitValue()
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        assertThat(value, `is`(PassViewModel.PassApiStatus.DONE))
    }
}
