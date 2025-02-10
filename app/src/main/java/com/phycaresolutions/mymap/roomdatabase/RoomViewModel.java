package com.phycaresolutions.mymap.roomdatabase;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RoomViewModel extends AndroidViewModel {
    Context context;

    private PersonRepository repository;
    private LiveData<List<PersonDetails>> allPersonDetails;

    public RoomViewModel(Application application) {
        super(application);
        repository = new PersonRepository(application);
        allPersonDetails = repository.getAllPersonDetails();
    }

    public LiveData<List<PersonDetails>> getAllPersonDetails() {

        return allPersonDetails;
    }

    public void insert(PersonDetails personDetails) {
        repository.insert(personDetails);
    }
    /*public void update(PersonDetails details) {
        userRepository.update(details);
    }*/

    public int delete(String id) {
      return repository.delete(id);
    }
    public void update(PersonDetails details){
        repository.update(details);
    }

}
