package ro.emag.repo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import ro.emag.api.FashionDaysApi

@Module
@InstallIn(ViewModelComponent::class)
object FashionDaysRepoModule {
    @Provides
    fun fashionDaysRepoProvider(api: FashionDaysApi): FashionDaysRepo
            = FashionDaysRepoImpl(api)
    }