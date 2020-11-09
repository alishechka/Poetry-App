package com.example.cognito;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cognito.model.PoemModel;
import com.example.cognito.repo.Repository;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;

public class MainViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable;
    private Repository repo;
    private MutableLiveData<PoemModel> _poem = new MutableLiveData<>();
    private MutableLiveData<String> _error = new MutableLiveData<>();

    public LiveData<PoemModel> poem() {
        return _poem;
    }

    public LiveData<String> error() {
        return _error;
    }

    public void getPoemData() {
        repo = new Repository();
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(
                repo.getRandomPoemRepo().subscribeWith(new DisposableSingleObserver<PoemModel>() {
                    @Override
                    public void onSuccess(PoemModel poemModels) {
                        _poem.setValue(poemModels);
                    }

                    @Override
                    public void onError(Throwable e) {
                        _error.setValue(e.getLocalizedMessage());
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
