package ro.emag.repo

import ro.emag.models.Products


interface FashionDaysRepo {
    suspend fun getWomenClothingProducts(): Products?
}