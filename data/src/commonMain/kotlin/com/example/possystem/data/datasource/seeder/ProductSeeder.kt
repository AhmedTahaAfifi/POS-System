package com.example.possystem.data.datasource.seeder

import com.example.possystem.data.datasource.dao.ProductDao
import com.example.possystem.data.datasource.entity.ProductEntity
import kotlinx.coroutines.flow.first

class ProductSeeder(private val productDao: ProductDao) {
    suspend fun seed() {
        val products = productDao.getAllProducts().first()
        if (products.isEmpty()) {
            val sampleProducts = listOf(
                ProductEntity("1", "Burger", 8.99, "Food"),
                ProductEntity("2", "Pizza", 12.50, "Food"),
                ProductEntity("3", "Coca Cola", 2.50, "Drink"),
                ProductEntity("4", "Water", 1.00, "Drink"),
                ProductEntity("5", "Fries", 3.99, "Food"),
                ProductEntity("6", "Ice Cream", 4.50, "Dessert"),
                ProductEntity("7", "Coffee", 3.00, "Drink"),
                ProductEntity("8", "Salad", 7.99, "Food")
            )
            productDao.insertProducts(sampleProducts)
        }
    }
}
