package com.example.cognito;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cognito.model.Favourites;
import com.example.cognito.model.PoemModel;
import com.example.cognito.model.TitleSearch;
import com.example.cognito.repo.Repository;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

public class MainViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Repository repo = new Repository();
    private MutableLiveData<PoemModel> _poemRandom = new MutableLiveData<>();
    private MutableLiveData<PoemModel> _poem = new MutableLiveData<>();
    private MutableLiveData<String> _error = new MutableLiveData<>();
    private MutableLiveData<Favourites> _favourites = new MutableLiveData<>();
    private MutableLiveData<List<TitleSearch>> _titleSearch = new MutableLiveData<>();

    public LiveData<PoemModel> poemRandom() {
        return _poemRandom;
    }

    public LiveData<PoemModel> poem() {
        return _poem;
    }

    public LiveData<Favourites> favourites() {
        return _favourites;
    }

    public LiveData<List<TitleSearch>> titleSearch() {
        return _titleSearch;
    }

    public LiveData<String> error() {
        return _error;
    }


    public void getRandomPoemData() {
        compositeDisposable.add(
                repo.getRandomPoemRepo().subscribe(
                        poemModels ->
                                _poemRandom.setValue(poemModels),
                        e -> {
                            _error.setValue(e.getLocalizedMessage());
                            Timber.d("failed to get PoemData");
                            Timber.d(Log.getStackTraceString(e));
                            Timber.d(e);
                        })
        );
    }

    public void getPoemByTitle(String title) {
        compositeDisposable.add(
                repo.getPoemFromDb(title).subscribe(
                        i -> Timber.d(String.valueOf(i)),
                        e -> _error.setValue(e.getLocalizedMessage())
                ));
    }

    public void addToRoomDB(PoemModel model) {
        compositeDisposable.add(
                repo.addPoemToDb(model).subscribe(
                        () -> {
                            Timber.d("WIN added to ROOM DB");
                            Timber.d("added %s", model);
                        },
                        e -> {
                            Timber.d("FAIL to add to ROOM DB");
                            Timber.d(e.getLocalizedMessage());
                        }
                )
        );
    }


    public void addToFavourites(String poemTitle) {

        compositeDisposable.add(
                repo.addToFavouritesList(poemTitle).subscribe(
                        () -> {
                            Timber.d("SUCCESS added to favs list: %s", poemTitle);
                        },
                        e -> {
                            Timber.e(e.getLocalizedMessage());
                        }
                )
        );
    }

    public void removeFavourite(String poemTitle) {
        compositeDisposable.add(
                repo.removeFromFavourites(poemTitle).subscribe(
                        () -> {
                            Timber.d("Removed from Fav List: %s", poemTitle);
                        },
                        e -> {
                            Timber.e("failed to remove from fav list: %s", poemTitle);
                        }
                )
        );
    }

    public void getFavourites() {
        compositeDisposable.add(
                repo.getFavouritesList().subscribe(
                        data -> _favourites.setValue(data),
                        e -> _error.setValue(e.getMessage())
                )
        );
    }

    public void getPoem(String title) {
        compositeDisposable.add(
                repo.getPoem(title).subscribe(
                        data -> _poem.setValue(data),
                        e -> _error.setValue(e.getMessage())
                )
        );
    }

    public void getTitleSearch(String title) {
        compositeDisposable.add(
                repo.getTitleSearch(title).subscribe(
                        data -> _titleSearch.setValue(data),
                        e -> _error.setValue(e.getMessage())
                )
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
