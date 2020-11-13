package com.example.cognito;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cognito.model.PoemModel;
import com.example.cognito.repo.Repository;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;

public class MainViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Repository repo = new Repository();
    private MutableLiveData<PoemModel> _poem = new MutableLiveData<>();
    private MutableLiveData<String> _error = new MutableLiveData<>();

    public LiveData<PoemModel> poem() {
        return _poem;
    }

    public LiveData<String> error() {
        return _error;
    }

//    public LiveData<String> favsObservableStringForTesting

    public void getPoemData() {
        compositeDisposable.add(
                repo.getRandomPoemRepo().subscribeWith(new DisposableSingleObserver<PoemModel>() {
                    @Override
                    public void onSuccess(PoemModel poemModels) {
                        _poem.setValue(poemModels);
                        repo.addPoemToDb(poemModels);
                        Log.d("ROOMDB", "onSuccess: " + poemModels.getTitle());
                    }

                    @Override
                    public void onError(Throwable e) {
                        _error.setValue(e.getLocalizedMessage());
                        Log.d("ROOMDB", "onError: " + e.getLocalizedMessage());

                    }
                })
        );
    }

    public void addToFavourites(String poemTitle) {
        compositeDisposable.add(
                repo.addToFavouritesList(poemTitle).subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {
                        Log.d("SUCCESS", "onComplete: it worked " + poemTitle);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("ERROR", "onError: " + e.getLocalizedMessage());
                        Log.d("ERROR", "onError: " + e.getMessage());
                        Log.d("ERROR", "onError: " + Log.getStackTraceString(e));
                        Log.d("ERROR", "onError: " + e.toString());

                    }
                })
        );
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }
}
