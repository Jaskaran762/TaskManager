package org.dal;

import java.util.ArrayList;
import java.util.List;

/**
 * stub for database
 */
public class DatabaseService {
    public boolean saveData(List<Task> tasks) {
        return true;
    }

    public List<Task> loadData(){
        return new ArrayList<Task>();
    }
}
