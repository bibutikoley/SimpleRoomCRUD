package dev.bibuti.rupeecircle.database;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;
import dev.bibuti.rupeecircle.database.models.LoginModel;
import dev.bibuti.rupeecircle.database.models.Users;

public class Repository {

    private UserDao mUserDao;
    private LiveData<List<Users>> usersLiveData;

    public Repository(Application application) {
        RupeeDatabase rupeeDatabase = RupeeDatabase.getInstance(application);
        mUserDao = rupeeDatabase.userDao();

        usersLiveData = mUserDao.daoGetAllUsers();
    }

    public LiveData<List<Users>> repoGetAllUsers() {
        return usersLiveData;
    }

    public Users RepoGetSingleUser(Long userID) {
        try {
            return new GetSingleUserAsyncTask(mUserDao).execute(userID).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Users RepoLogin(String email, String password) {
        try {
            return new LoginAsyncTask(mUserDao).execute(new LoginModel(email, password)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void RepoInsertUser(Users user) {
        new InsertAsyncTask(mUserDao).execute(user);
    }

    public void RepoUpdateUser(Users users) {
        new UpdateAsyncTask(mUserDao).execute(users);
    }

    public void RepoDeleteUser(Users users) {
        new DeleteAsyncTask(mUserDao).execute(users);
    }

    //////////////////////////////////////////////////////////////////////////////
    private static class InsertAsyncTask extends AsyncTask<Users, Void, Void> {

        private UserDao userDao;

        InsertAsyncTask(UserDao mUserDao) {
            userDao = mUserDao;
        }

        @Override
        protected Void doInBackground(Users... users) {
            userDao.daoInsertUser(users[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Users, Void, Void> {

        private UserDao userDao;

        UpdateAsyncTask(UserDao mUserDao) {
            userDao = mUserDao;
        }

        @Override
        protected Void doInBackground(Users... users) {
            userDao.daoUpdateUser(users[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Users, Void, Void> {

        private UserDao userDao;

        DeleteAsyncTask(UserDao mUserDao) {
            userDao = mUserDao;
        }

        @Override
        protected Void doInBackground(Users... users) {
            userDao.daoDeleteUser(users[0]);
            return null;
        }
    }

    private static class GetSingleUserAsyncTask extends AsyncTask<Long, Void, Users> {

        private UserDao userDao;

        GetSingleUserAsyncTask(UserDao mUserDao) {
            userDao = mUserDao;
        }

        @Override
        protected Users doInBackground(Long... longs) {
            return userDao.daoGetUserByID(longs[0]);
        }
    }

    private static class LoginAsyncTask extends AsyncTask<LoginModel, Void, Users> {

        private UserDao userDao;

        LoginAsyncTask(UserDao mUserDao) {
            userDao = mUserDao;
        }

        @Override
        protected Users doInBackground(LoginModel... loginModels) {
            LoginModel loginModel = loginModels[0];
            return userDao.daoLogin(loginModel.getEmail(), loginModel.getPassword());
        }
    }
}
