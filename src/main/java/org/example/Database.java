package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * stub  for
 * creating a database object
 */
public class Database {
    public boolean saveData(List<Task> tasks) {
        return true;
    }

    public List<Task> loadData(){
        return new ArrayList<Task>();
    }
}
