package ro.emag.api

import retrofit2.http.GET
import ro.emag.models.Products

interface FashionDaysApi {
    @GET("women/clothing")
    suspend fun getWomenClothingProducts(): Products?
}