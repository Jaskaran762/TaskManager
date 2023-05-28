package org.dal;

import java.util.List;

/**
 * stub for database
 */
public class DatabaseService {

    private List<Task> tasks;
    private static DatabaseService dbService;

    /**
     * this method will return DatabaseService instance if it is not created yet.
     * @return DatabaseService class object
     */
    public static DatabaseService getInstance(){
        if(dbService == null){
            dbService = new DatabaseService();
        }
        return dbService;
    }

    public boolean saveData(List<Task> tasks) {
        this.tasks = tasks;
        return true;
    }

    public List<Task> loadData(){
        return tasks;
    }

    public boolean deleteData(List<Task> tasks){
        this.tasks = null;
        return true;
    }
}
