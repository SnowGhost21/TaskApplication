package diegocunha.taskapplication.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

fun <T> mutableLiveDataOf(initialValue: T) = MutableLiveData<T>().apply {
    value = initialValue
}

fun <T, R> LiveData<T>.map(mapper: (T?) -> R?): LiveData<R> = Transformations.map(this, mapper)