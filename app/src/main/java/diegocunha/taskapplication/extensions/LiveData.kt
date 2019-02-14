package diegocunha.taskapplication.extensions

import androidx.lifecycle.MutableLiveData

fun <T> mutableLiveDataOf(initialValue: T) = MutableLiveData<T>().apply {
    value = initialValue
}