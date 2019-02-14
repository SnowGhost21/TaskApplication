package diegocunha.taskapplication.extensions

import android.util.Log
import androidx.lifecycle.LiveData
import io.reactivex.*
import io.reactivex.disposables.Disposable

fun <T> Single<T>.asLiveData(): LiveData<T> = SingleLiveData(this)

fun <T> Observable<T>.asLiveData(strategy: BackpressureStrategy = BackpressureStrategy.LATEST): LiveData<T> =
    FlowableLiveData(this.toFlowable(strategy))

fun <T> Single<T>.subscribe(observer: Observer<T>) = this.subscribe({
    observer.onNext(it)
}, {
    observer.onError(it)
})

private class FlowableLiveData<T>(private val flowable: Flowable<T>) : RxLiveData<T>() {
    override fun onActive() {
        disposable = flowable.subscribe(observer, errorListener)
    }
}

private abstract class RxLiveData<T> : LiveData<T>() {

    protected val observer = { value: T ->
        postValue(value)
    }

    protected val errorListener = { t: Throwable ->
        Log.e("LIVEDATA_ERROR", "$t")
        postValue(null)
    }

    protected var disposable: Disposable? = null

    override fun onInactive() {
        disposable?.dispose()
    }
}

private class SingleLiveData<T>(private val single: Single<T>) : RxLiveData<T>() {
    override fun onActive() {
        disposable = single.subscribe(observer, errorListener)
    }
}