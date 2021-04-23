package com.kardabel.mareu.di;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kardabel.mareu.repository.MeetingsRepository;
import com.kardabel.mareu.ui.add.AddMeetingViewModel;
import com.kardabel.mareu.ui.details.DetailsActivityViewModel;
import com.kardabel.mareu.ui.list.MainViewModel;

/**
 * Created by stéphane Warin OCR on 01/04/2021.
 */
public class MareuViewModelFactory implements ViewModelProvider.Factory {

  private static MareuViewModelFactory sFactory;

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

  @NonNull
  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
      if (modelClass.isAssignableFrom(MainViewModel.class)) {
          return (T) new MainViewModel(new MeetingsRepository());

      }
      else if(modelClass.isAssignableFrom(DetailsActivityViewModel.class)){
          return (T) new DetailsActivityViewModel(new MeetingsRepository());

      }
      else if(modelClass.isAssignableFrom(AddMeetingViewModel.class)){
          return (T) new AddMeetingViewModel(new MeetingsRepository());

      }

      throw new IllegalArgumentException("Unknown ViewModel class");

  }
}
