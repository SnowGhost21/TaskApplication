package diegocunha.taskapplication.dependenyinjection

import com.google.gson.GsonBuilder
import diegocunha.taskapplication.BuildConfig
import diegocunha.taskapplication.model.repository.TaskRepository
import diegocunha.taskapplication.model.repository.retrofit.RetrofitTaskRepository
import diegocunha.taskapplication.model.repository.retrofit.TaskApi
import diegocunha.taskapplication.view.home.HomeViewModel
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    factory { GsonBuilder().create() }

    factory {
        OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()
    }

    factory {
        Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(get()))
                .client(get())
                .build()
    }


    factory {
        val retrofit: Retrofit = get()
        retrofit.create(TaskApi::class.java)
    }

    single { RetrofitTaskRepository(get()) as TaskRepository }

    viewModel { HomeViewModel(get()) }
}