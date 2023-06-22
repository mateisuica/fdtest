package ro.emag.api

import android.content.Context
import android.content.pm.ApplicationInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object FashionDaysApiModule {

    @Provides fun fashionDaysApiProvider(@ApplicationContext appContext: Context): FashionDaysApi = let {
        val isDebuggable = 0 !=  appContext.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
        FashionDaysApiProvider.provideFashionDaysAPI(isDebuggable)
    }
}