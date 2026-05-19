package com.example.possystem.ui.viewmodel.cart

import com.example.possystem.domain.model.OrderItem
import com.example.possystem.domain.model.Product
import com.example.possystem.domain.usecase.*
import com.example.possystem.fakes.FakeCartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class CartViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: FakeCartRepository
    private lateinit var viewModel: CartViewModel

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = FakeCartRepository()
        
        viewModel = CartViewModel(
            cartItemsUseCase = GetCartItemsUseCase(repository),
            updateCartQuantityUseCase = UpdateCartQuantityUseCase(repository),
            removeFromCartUseCase = RemoveFromCartUseCase(repository),
            createOrderUseCase = CreateOrderUseCase(object : com.example.possystem.domain.repository.OrderRepository {
                override fun getAllOrders(): kotlinx.coroutines.flow.Flow<List<com.example.possystem.domain.model.Order>> = kotlinx.coroutines.flow.flowOf(emptyList())
                override suspend fun saveOrder(order: com.example.possystem.domain.model.Order) {}
                override suspend fun updateOrderStatus(orderId: String, status: com.example.possystem.domain.model.OrderStatus) {}
            }),
            clearCartUseCase = ClearCartUseCase(repository),
            printer = object : com.example.possystem.domain.hardware.Printer {
                override suspend fun printReceipt(order: com.example.possystem.domain.model.Order) = Result.success(Unit)
            }
        )
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `ViewModel should calculate total price correctly`() = runTest {
        // Arrange
        repository.addToCart(OrderItem(Product("1", "Burger", 10.0, ""), 2, 10.0))
        repository.addToCart(OrderItem(Product("2", "Soda", 5.0, ""), 1, 5.0))

        // Act
        advanceUntilIdle()

        // Assert
        assertEquals(25.0, viewModel.viewState.value.total)
        assertEquals(2, viewModel.viewState.value.items.size)
    }
}
