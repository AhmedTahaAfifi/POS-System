package com.example.possystem.di

import com.example.possystem.domain.repository.CartRepository
import com.example.possystem.domain.usecase.CreateOrderUseCase
import com.example.possystem.domain.usecase.GetOrdersUseCase
import com.example.possystem.domain.usecase.GetProductUseCase
import com.example.possystem.domain.usecase.GetProductsUseCase
import com.example.possystem.domain.usecase.UpdateOrderStatusUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetProductsUseCase(get()) }
    factory { GetProductUseCase(get()) }
    factory { CreateOrderUseCase(get()) }
    factory { GetOrdersUseCase(get()) }
    factory { UpdateOrderStatusUseCase(get()) }

    single { CartRepository() }
}