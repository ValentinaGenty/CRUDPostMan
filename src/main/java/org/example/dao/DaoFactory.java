package org.example.dao;

public class DaoFactory {
    private static Dao dao;
    private DaoFactory(){

    }

    public static Dao getDao(){
        if(dao==null){
            dao=new DaoImpl();
        }
        return dao;
    }
}
