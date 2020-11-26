package com.example.cognito.service;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cognito.MainViewModel;
import com.example.cognito.OnClickedListener;
import com.example.cognito.R;
import com.example.cognito.TitleSearchAdapter;
import com.example.cognito.databinding.FragmentSearchBinding;

import java.util.Timer;
import java.util.TimerTask;

import timber.log.Timber;

import static com.example.cognito.common.Constants.POEM_TITLE;

public class SearchFragment extends Fragment implements OnClickedListener {
    private FragmentSearchBinding binding;
    private MainViewModel viewModel;
    private TitleSearchAdapter adapter;
    private final long DELAY = 3000; // milliseconds

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.titleSearch().observe(this, titleSearches -> {
            binding.searchRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
            adapter = new TitleSearchAdapter(titleSearches, this);
            binding.searchRecyclerView.setAdapter(adapter);
        });
        viewModel.error().observe(this, e -> Timber.d(e));

        binding.searchSearch.addTextChangedListener(new TextWatcher() {
            private Timer timer = new Timer();

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                timer.cancel();
                timer = new Timer();
                timer.schedule(
                        new TimerTask() {
                            @Override
                            public void run() {
                                viewModel.getTitleSearch(s.toString());
                                Timber.d("searching for %s", s);
                            }
                        }, DELAY);
            }
        });
    }

    @Override
    public void onItemClicked(String title) {
        Timber.d(title);
        Bundle bundle = new Bundle();
        bundle.putString(POEM_TITLE, title);

        Fragment frag = new DisplaySinglePoemFragment();
        frag.setArguments(bundle);
        FragmentManager ft = getFragmentManager();
        ft.beginTransaction()
                .replace(R.id.fragment_container, frag)
                .addToBackStack("tag")
                .commit();
    }
}
