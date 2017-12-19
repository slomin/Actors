package com.kotlinblog.actors.view

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kotlinblog.actors.App
import com.kotlinblog.actors.data.Actor
import com.kotlinblog.actors.data.ActorsList
import com.kotlinblog.actors.data.Api
import com.kotlinblog.actors.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

class MainViewModel : ViewModel() {

    @Inject lateinit var mRetrofit: Retrofit

    private var mActors: MutableLiveData<ActorsList>
    val actors: LiveData<ActorsList> get() = mActors

    private var mConnectionError: SingleLiveEvent<String>
    val connectionError: SingleLiveEvent<String> get() = mConnectionError

    private val mCompositeDisposable = CompositeDisposable()

    init {
        App.getComponent().inject(this)
        mActors = MutableLiveData()
        mConnectionError = SingleLiveEvent()
    }

    fun fetchActors() {
        val service = mRetrofit.create<Api>(Api::class.java)
        mCompositeDisposable.add(service.getActors()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    mActors.postValue(response)
                }, {
                    error ->
                    Timber.e(error.message)
                    connectionError.postValue(error.message)
                }))
    }

    override fun onCleared() {
        super.onCleared()
        if (!mCompositeDisposable.isDisposed) {
            mCompositeDisposable.dispose()
        }
    }

    /**
     * Adapter callbacks
     */
    fun getActorAt(position: Int): Actor? {
        return if (position < getMealListSize()) {
            actors.value?.actors?.get(position)
        } else {
            null
        }
    }

    fun getMealListSize(): Int {
        actors.value?.actors?.let{
            return it.size
        }
        return 0
    }
}