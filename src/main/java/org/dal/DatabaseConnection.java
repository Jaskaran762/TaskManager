package org.dal;

/**
 * interfacing returning
 * a database service instance
 */
public interface DatabaseConnection {

    static DatabaseService makeConnection(){
        return DatabaseService.getInstance();
    }
}
