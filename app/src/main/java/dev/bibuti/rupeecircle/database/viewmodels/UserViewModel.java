package dev.bibuti.rupeecircle.database.viewmodels;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import dev.bibuti.rupeecircle.database.Repository;
import dev.bibuti.rupeecircle.database.models.Users;

public class UserViewModel extends AndroidViewModel {

    private Repository mRepository;
    private LiveData<List<Users>> mUserLiveData;

    public UserViewModel(@NonNull Application application) {
        super(application);
        mRepository = new Repository(application);
        mUserLiveData = mRepository.repoGetAllUsers();
    }

    public LiveData<List<Users>> getAllUsers() {
        return mUserLiveData;
    }

    public void insertUser(Users users) {
        mRepository.RepoInsertUser(users);
    }

    public void updateUser(Users users) {
        mRepository.RepoUpdateUser(users);
    }

    public void deleteUser(Users users) {
        mRepository.RepoDeleteUser(users);
    }

    public Users getSingleUser(Long userId) {
        return mRepository.RepoGetSingleUser(userId);
    }

    public Users login(String email, String password) {
        return mRepository.RepoLogin(email, password);
    }

}
