package org.example;

/**
 * interfacing returning
 * a database instance
 */
public interface DatabaseConnection {

    public static Database makeConnection(){
        return new Database();
    }
}
