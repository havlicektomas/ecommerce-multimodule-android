package dev.havlicektomas.discovery_domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.havlicektomas.discovery_domain.repository.ProductRepository
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchProductsUseCaseTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private val testCoroutineScope = TestScope(testDispatcher)

    private lateinit var useCase: SearchProductsUseCase
    private lateinit var repository: ProductRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        repository = mockk<ProductRepository>(relaxed = true)
        useCase = SearchProductsUseCase(repository)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }

    @Test
    fun `repository not called when query is blank`() {
        val query = " "
        val page = 1
        val pageSize = 10

        testCoroutineScope.runTest {
            useCase.invoke(query, page, pageSize)
            coVerify(inverse = true) { repository.searchProducts(query, page, pageSize) }
        }
    }
}
