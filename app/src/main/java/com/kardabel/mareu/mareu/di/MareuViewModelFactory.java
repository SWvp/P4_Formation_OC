package com.kardabel.mareu.mareu.di;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kardabel.mareu.mareu.repository.MeetingsRepository;
import com.kardabel.mareu.mareu.ui.details.DetailsActivityViewModel;
import com.kardabel.mareu.mareu.ui.list.MeetingViewModel;

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
      if (modelClass.isAssignableFrom(MeetingViewModel.class)) {
          return (T) new MeetingViewModel(new MeetingsRepository());
      }
      else if(modelClass.isAssignableFrom(DetailsActivityViewModel.class)){
          return (T) new DetailsActivityViewModel(new MeetingsRepository());
      }

      throw new IllegalArgumentException("Unknown ViewModel class");
  }
}
