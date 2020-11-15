package com.example.cognito;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cognito.model.PoemModel;
import com.example.cognito.repo.Repository;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import timber.log.Timber;

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

    public void getPoemData() {
        compositeDisposable.add(
                repo.getRandomPoemRepo().subscribeWith(new DisposableSingleObserver<PoemModel>() {
                    @Override
                    public void onSuccess(PoemModel poemModels) {
                        _poem.setValue(poemModels);
//                        repo.addPoemToDb(poemModels);

                    }

                    @Override
                    public void onError(Throwable e) {
                        _error.setValue(e.getLocalizedMessage());
                        Timber.d("failed to get PoemData");
                        Timber.d(Log.getStackTraceString(e));
                        Timber.d(e);

                    }
                })
        );
    }

    public void getPoemByTitle(String title) {
        compositeDisposable.add(
                repo.getPoemFromDb(title).subscribe(
                        i -> Timber.d(String.valueOf(i)),
                        Timber::d)

        );
    }

    public void addToRoomDB(PoemModel model) {
        compositeDisposable.add(
                repo.addPoemToDb(model).subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {
                        Timber.d("WIN added to ROOM DB");
                        Timber.d("added %s", model);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d("FAIL to add to ROOM DB");
                    }
                })
        );
    }


    public void addToFavourites(String poemTitle) {
        compositeDisposable.add(
                repo.addToFavouritesList(poemTitle).subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {
                        Timber.d("SUCCESS added to favs list: %s", poemTitle);
                        Toast.makeText(App.getAppContext(), "added to Favs", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(App.getAppContext(), "failed to add to favs", Toast.LENGTH_SHORT).show();
                        Timber.e("error adding to favourites: ");
                        Timber.e(e);
                        Timber.e(e.getLocalizedMessage());
                        Timber.e(Log.getStackTraceString(e));
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
