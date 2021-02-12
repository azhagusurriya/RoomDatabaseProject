package com.example.roomdatabaseproject.repository;

import android.app.Application;

import com.example.roomdatabaseproject.database.MyDatabase;
import com.example.roomdatabaseproject.database.dao.MenuDao;
import com.example.roomdatabaseproject.database.model.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuRepository {

    /*Repositories are the single entry point for the database along with the DAO*/

    private MyDatabase db;
    private MenuDao menuDao;

    public MenuRepository(Application application){
        db = MyDatabase.getDatabase(application);
        menuDao = db.menuDao();
    }

    public void insertMenu(ArrayList<Menu> items){
        menuDao.insertMenuList(items);
    }

    public List<Menu> getItems(){
        return menuDao.getList();
    }

    public Menu getMenuItem(int id){ return menuDao.getMenuItem(id);};

}
