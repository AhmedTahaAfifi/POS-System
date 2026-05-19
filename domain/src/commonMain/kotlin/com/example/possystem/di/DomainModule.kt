package com.example.possystem.di

import com.example.possystem.domain.repository.CartRepository
import com.example.possystem.domain.usecase.AddToCartUseCase
import com.example.possystem.domain.usecase.ClearCartUseCase
import com.example.possystem.domain.usecase.CreateOrderUseCase
import com.example.possystem.domain.usecase.GetCartItemsUseCase
import com.example.possystem.domain.usecase.GetOrdersUseCase
import com.example.possystem.domain.usecase.GetProductUseCase
import com.example.possystem.domain.usecase.GetProductsUseCase
import com.example.possystem.domain.usecase.RemoveFromCartUseCase
import com.example.possystem.domain.usecase.UpdateCartQuantityUseCase
import com.example.possystem.domain.usecase.UpdateOrderStatusUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetProductsUseCase(get()) }
    factory { GetProductUseCase(get()) }
    factory { CreateOrderUseCase(get()) }
    factory { GetOrdersUseCase(get()) }
    factory { UpdateOrderStatusUseCase(get()) }
    factory { GetCartItemsUseCase(get()) }
    factory { AddToCartUseCase(get()) }
    factory { RemoveFromCartUseCase(get()) }
    factory { UpdateCartQuantityUseCase(get()) }
    factory { ClearCartUseCase(get()) }
}