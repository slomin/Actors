package com.kotlinblog.actors.view;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.kotlinblog.actors.App;
import com.kotlinblog.actors.data.Actor;
import com.kotlinblog.actors.data.ActorsList;
import com.kotlinblog.actors.data.Api;
import com.kotlinblog.actors.utils.SingleLiveEvent;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import timber.log.Timber;

public class MainViewModel extends ViewModel {

    @Inject Retrofit mRetrofit;
    private MutableLiveData<ActorsList> mActors = new MutableLiveData<>();
    private SingleLiveEvent<String> mConnectionError = new SingleLiveEvent<>();
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public MainViewModel() {
        App.getComponent().inject(this);
    }

    void fetchActors() {
        Api service = mRetrofit.create(Api.class);
        mCompositeDisposable.add(service.getActors()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            Timber.d(response.toString());
                            mActors.postValue(response);
                        },
                        error -> {
                            Timber.e(error.getMessage());
                            mConnectionError.postValue(error.getMessage());
                        }
                )
        );
    }

    public MutableLiveData<ActorsList> getActors() {
        return mActors;
    }

    public SingleLiveEvent<String> getConnectionError() {
        return mConnectionError;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

    /**
     * Adapter callbacks
     */
    Actor getActorAt(int position) {
        if (position < getActorsListSize() && mActors.getValue() != null) {
            return mActors
                    .getValue()
                    .getActors()
                    .get(position);
        } else {
            return null;
        }
    }

    int getActorsListSize() {
        if (mActors.getValue() != null) {
            return mActors
                    .getValue()
                    .getActors()
                    .size();
        } else {
            return 0;
        }
    }
}
