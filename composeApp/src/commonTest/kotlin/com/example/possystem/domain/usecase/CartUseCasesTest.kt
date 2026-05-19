package com.example.possystem.domain.usecase

import com.example.possystem.domain.model.OrderItem
import com.example.possystem.domain.model.Product
import com.example.possystem.fakes.FakeCartRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CartUseCasesTest {

    private lateinit var repository: FakeCartRepository
    private lateinit var addToCartUseCase: AddToCartUseCase
    private lateinit var getCartItemsUseCase: GetCartItemsUseCase
    private lateinit var clearCartUseCase: ClearCartUseCase

    @BeforeTest
    fun setup() {
        repository = FakeCartRepository()
        addToCartUseCase = AddToCartUseCase(repository)
        getCartItemsUseCase = GetCartItemsUseCase(repository)
        clearCartUseCase = ClearCartUseCase(repository)
    }

    @Test
    fun `adding item should update repository state`() = runTest {
        val product = Product("p1", "Coffee", 3.50, "")
        val orderItem = OrderItem(product, 1, 3.50)

        addToCartUseCase(orderItem)

        val items = getCartItemsUseCase().first()
        assertEquals(1, items.size)
        assertEquals("Coffee", items[0].product.name)
    }

    @Test
    fun `clearing cart should make it empty`() = runTest {
        repository.addToCart(OrderItem(Product("1", "A", 1.0, ""), 1, 1.0))
        
        clearCartUseCase()

        val items = getCartItemsUseCase().first()
        assertTrue(items.isEmpty())
    }
}
