package com.eindproject.v2.eindprojectv2.logic;

import com.eindproject.v2.eindprojectv2.dal.DataBase;

import java.io.*;


public class DbLogic {
    final File localDb = new File("database.db");
    public void SaveDB(DataBase dataBase) throws IOException {
        try(FileOutputStream FileoutputStream = new FileOutputStream(localDb))
        {
            ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(FileoutputStream);
            ObjectOutputStream.writeObject(dataBase);
        }

    }

    public DataBase LoadDB() throws IOException, ClassNotFoundException {
        try(FileInputStream inputstream = new FileInputStream(localDb)){
            ObjectInputStream DeserialisedData = new ObjectInputStream(inputstream);
            return (DataBase) DeserialisedData.readObject() ;

        }

    }
}
