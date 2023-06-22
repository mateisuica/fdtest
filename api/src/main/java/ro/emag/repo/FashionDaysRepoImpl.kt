package ro.emag.repo

import ro.emag.api.FashionDaysApi
import ro.emag.models.Products
import javax.inject.Inject

class FashionDaysRepoImpl @Inject constructor(private val api: FashionDaysApi): FashionDaysRepo {

    override suspend fun getWomenClothingProducts(): Products? {
        return api.getWomenClothingProducts()
    }
}