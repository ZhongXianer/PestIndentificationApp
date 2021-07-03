package com.example.pestidentificationapp.viewModel;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.pestidentificationapp.model.Pest;
import com.example.pestidentificationapp.view.LibraryShowActivity;

public class LibraryShowViewModel extends ViewModel {

    private ObservableField<Pest> pest;

    public LibraryShowViewModel() {

    }

    private void getData(){}


    public ObservableField<Pest> getPest() {
        return pest;
    }

    public void setPest(ObservableField<Pest> pest) {
        this.pest = pest;
    }
}
