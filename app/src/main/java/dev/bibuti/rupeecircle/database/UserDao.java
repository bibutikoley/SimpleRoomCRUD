package dev.bibuti.rupeecircle.database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import dev.bibuti.rupeecircle.database.models.Users;

@Dao
public interface UserDao {

    @Query("select * from users")
        //This will return all Users..
    LiveData<List<Users>> daoGetAllUsers();

    @Query("select * from users where id =:id")
        //This will return only one User..
    Users daoGetUserByID(Long id);

    //Query for login
    @Query("select * from users where email =:email and password =:password")
    Users daoLogin(String email, String password);

    @Insert
    void daoInsertUser(Users user);

    @Update
    void daoUpdateUser(Users user);

    @Delete
    void daoDeleteUser(Users user);

}
