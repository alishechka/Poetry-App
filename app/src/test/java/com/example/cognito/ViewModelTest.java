package com.example.cognito;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.boss.poetrydb.MainViewModel;
import com.boss.poetrydb.model.PoemModel;
import com.boss.poetrydb.repo.Repository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;

import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule =
            new InstantTaskExecutorRule();

    private MainViewModel sut;
    @Mock
    private Repository repo;

    @Mock
    private Observer<PoemModel> successPoemModel;
    private PoemModel poem;

    @Before
    public void setup() {
        sut = new MainViewModel();
        sut.poemRandom().observeForever(successPoemModel);
        poem = Common.getCommonPoemModel();
    }

    @Test
    public void getRandomPoemData_success_returnPoemModel() {
        //given
        given(repo.getRandomPoemRepo()).willReturn(Single.just(poem));
        //when
        sut.getRandomPoemData();
        //then
        verify(repo, times(1)).getRandomPoemRepo();

    }
}
