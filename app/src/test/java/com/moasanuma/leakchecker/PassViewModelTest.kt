package com.moasanuma.leakchecker

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moasanuma.leakchecker.network.ApiService
import com.moasanuma.leakchecker.viewmodel.PassViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.stub

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class PassViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var passViewModel: PassViewModel

    @Mock
    private lateinit var service: ApiService

    @Before
    fun setup() {
        passViewModel = PassViewModel()
    }

    @Test
    fun getLeakPassList_setString() = testDispatcher.runBlockingTest {
        val dummyLeakPass = (1..36).map {
            // abcを使って36桁のパスワードを作る
            "abc".random().toString()
        }.reduce { acc, c -> acc + c }
        passViewModel.status.observeForever {}
        service.stub {
            onBlocking { getLeakPassProperties(any()) } doReturn dummyLeakPass
        }

        val pass = "1234"
        passViewModel.getLeakPassList(pass, service)

        val value = passViewModel.status.value
        assertThat(value, `is`(PassViewModel.PassApiStatus.DONE))
    }
}
