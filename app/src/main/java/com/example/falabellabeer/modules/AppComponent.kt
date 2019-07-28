package com.example.falabellabeer.modules

import com.example.falabellabeer.common.AppInstance
import com.example.falabellabeer.ui.DashboardActivity
import com.example.falabellabeer.viewmodel.BeerVM
import com.example.falabellabeer.webservices.RetrofitService
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [RetrofitModule::class])
interface AppComponent {
    /**
     * Injects required dependencies into the specified BeerVM.
     * @param beerVM BeerVM in which to inject the dependencies
     */
    abstract fun inject(beerVM: BeerVM)


    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        fun networkModule(networkModule: RetrofitModule): Builder
    }


}