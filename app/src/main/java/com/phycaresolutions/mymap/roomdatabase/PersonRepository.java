package com.phycaresolutions.mymap.roomdatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PersonRepository {
    Application application;
    LiveData<List<PersonDetails>> liveData ;
    int bb = 0;
    UserDataBase userDataBase;
    public PersonRepository(Application application) {
        userDataBase =  UserDataBase.getInstance(application);
        liveData = userDataBase.personDao().getData();
    }

    public LiveData<List<PersonDetails>> getAllPersonDetails() {

       /* UserDataBase.databaseWriteExecutor.execute(() -> {
            liveData = UserDataBase.getInstance(application).personDao().getData();
        });*/
        return liveData;
    }

    public void insert(PersonDetails personDetails) {

       // userDataBase.personDao().insertData(personDetails);
        UserDataBase.databaseWriteExecutor.execute(()-> userDataBase.personDao().insertData(personDetails));
    }
    public int delete(String id){

        UserDataBase.databaseWriteExecutor.execute(() -> bb = userDataBase.personDao().deleteUser(id));
        return bb;
    }

    public void update(PersonDetails details) {
        UserDataBase.databaseWriteExecutor.execute(() -> userDataBase.personDao().updateData(details));
    }
}
