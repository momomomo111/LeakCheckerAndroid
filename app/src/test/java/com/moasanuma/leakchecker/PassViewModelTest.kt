package com.moasanuma.leakchecker

import android.os.Looper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moasanuma.leakchecker.viewmodel.PassViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.Shadows
import org.robolectric.annotation.LooperMode

@RunWith(AndroidJUnit4::class)
class PassViewModelTest {
    private lateinit var passViewModel: PassViewModel
    @OptIn(ExperimentalCoroutinesApi::class)
    val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        passViewModel = PassViewModel()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getLeakPassList_setString() = testDispatcher.runBlockingTest{
        passViewModel.status.observeForever {}
        val pass = "1234"
        passViewModel.getLeakPassList(pass)
        val value = passViewModel.status.value
        assertThat(value, `is`(PassViewModel.PassApiStatus.DONE))
    }
}
