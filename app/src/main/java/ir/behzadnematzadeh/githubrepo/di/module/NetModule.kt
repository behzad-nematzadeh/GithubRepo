package ir.behzadnematzadeh.githubrepo.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import ir.behzadnematzadeh.githubrepo.remote.services.ApiService
import ir.behzadnematzadeh.githubrepo.remote.services.GITHUB_BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetModule {
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(GITHUB_BASE_URL)
            .build()
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }
}