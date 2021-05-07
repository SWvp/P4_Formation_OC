package com.kardabel.mareu.di;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kardabel.mareu.repository.MeetingsRepository;
import com.kardabel.mareu.ui.add.AddMeetingViewModel;
import com.kardabel.mareu.ui.details.DetailsViewModel;
import com.kardabel.mareu.ui.list.MainViewModel;


public class MareuViewModelFactory implements ViewModelProvider.Factory {

    private static MareuViewModelFactory sFactory;
    private final MeetingsRepository mMeetingsRepository;

    public static MareuViewModelFactory getInstance() {
        if (sFactory == null) {
            synchronized (MareuViewModelFactory.class) {
                if (sFactory == null) {
                    sFactory = new MareuViewModelFactory();

                }
            }
        }
        return sFactory;

    }

    public MareuViewModelFactory() {
        mMeetingsRepository = new MeetingsRepository();
    }

    // Create an instance for each viewModel
    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(mMeetingsRepository);

        } else if (modelClass.isAssignableFrom(DetailsViewModel.class)) {
            return (T) new DetailsViewModel(mMeetingsRepository);

        } else if (modelClass.isAssignableFrom(AddMeetingViewModel.class)) {
            return (T) new AddMeetingViewModel(mMeetingsRepository);

        }

        throw new IllegalArgumentException("Unknown ViewModel class");

    }
}
