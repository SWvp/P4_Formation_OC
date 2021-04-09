package com.kardabel.mareu.mareu.di;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kardabel.mareu.mareu.repository.MeetingsRepository;
import com.kardabel.mareu.mareu.viewmodel.MeetingActivityViewModel;

/**
 * Created by stéphane Warin OCR on 01/04/2021.
 */
public class MareuViewModelFactory implements ViewModelProvider.Factory {

  private static MareuViewModelFactory sFactory;


  private MareuViewModelFactory() {
  }

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
      if (modelClass.isAssignableFrom(MeetingActivityViewModel.class)) {
          return (T) new MeetingActivityViewModel(new MeetingsRepository());
      }

      throw new IllegalArgumentException("Unknown ViewModel class");
  }
}
