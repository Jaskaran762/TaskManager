package org.dal;

import java.util.List;
import java.util.Scanner;

/**
 * Main class containing methods
 * to print tasks, main method taking user input
 * and perform task operations accordingly and
 * also methods to save and load data from database
 */
public class TaskManagerApp {

    private static List<Task> tasks = null;
    private static final Scanner input = new Scanner(System.in);

    /**
     * DatabaseService object obtained from makeConnection method of DatabaseConnection
     */
    private static DatabaseService databaseService = DatabaseConnection.makeConnection();

    /**
     * this method helps to save tasks in database
     * @param tasks list containing all the tasks of the user
     * @return true if tasks are saved in the database successfully
     */
    public static boolean saveTasksInDatabase(List<Task> tasks){
        return databaseService.saveData(tasks);
    }

    /**
     * this method helps to load tasks from database
     * @return tasks present in database
     */
    public static List<Task> loadTasksFromDatabase(){
        return databaseService.loadData();
    }

    /**
     * this method will delete tasks from database
     * @return true if tasks are deleted from database successfully
     */
    public static boolean deleteTasksFromDatabase(List<Task> tasks){
        return databaseService.deleteData(tasks);
    }

    /**
     * this method prints all tasks on console
     * @param tasks list containing all the tasks of the user
     */
    public static void showAllTasks(List<Task> tasks){

        System.out.println("----------List of tasks------------");
        System.out.println("   Task Id    |   Task Name   |   Task Priority   |   Task Deadline   ");
        for (Task task : tasks) {
            System.out.format("%14s|%15s|%19s|%19s", Integer.valueOf(task.getTaskId()).toString(),
                    task.getTaskName(), Integer.valueOf(task.getPriority()).toString(),
                    task.getDeadline().toString());
            System.out.println();
        }
    }

    /**
     * main method
     */
    public static void main(String[] args) {

        System.out.println();
        System.out.println("----------------Welcome to the Task Manager application----------------------");

        TaskManager taskManager = new TaskManager(input);
        boolean check = true;
        int userInp;
        while(check){

            System.out.println("Press 1 to add a task");
            System.out.println("Press 2 to show all tasks");
            System.out.println("Press 3 to edit a task");
            System.out.println("Press 4 to change priority of task");
            System.out.println("Press 5 to change deadline of task");
            System.out.println("Press 6 to save tasks");
            System.out.println("Press 7 to load tasks from database");
            System.out.println("Press 8 to delete tasks from database");
            System.out.println("Press 9 to show tasks sorted according to its priority");
            System.out.println("Press 10 to show tasks sorted according to its deadline");
            System.out.println("Press 11 to delete a task");
            System.out.println("Press any other key to exit");
            try {

                userInp = Integer.parseInt(input.nextLine());
                switch (userInp) {
                    case 1 -> {
                        tasks = taskManager.addTask();
                        System.out.println("Task added successfully");

                        System.out.println();
                        System.out.println("Press enter key to continue");
                        input.nextLine();
                    }
                    case 2 -> {
                        showAllTasks(tasks);

                        System.out.println();
                        System.out.println("Press enter key to continue");
                        input.nextLine();
                    }
                    case 3 -> {
                        System.out.println("Enter a task id to edit the task");
                        taskManager.editCompleteTask(Integer.parseInt(input.nextLine()));
                        System.out.println("Task has been edited successfully");

                        System.out.println();
                        System.out.println("Press enter key to continue");
                        input.nextLine();
                    }
                    case 4 -> {
                        System.out.println("Enter a task id to change priority of task");
                        taskManager.changePriorityOfTask(Integer.parseInt(input.nextLine()));
                        System.out.println("Priority changed successfully");

                        System.out.println();
                        System.out.println("Press enter key to continue");
                        input.nextLine();
                    }
                    case 5 -> {
                        System.out.println("Enter a task id to change deadline of task");
                        taskManager.changeDeadlineOfTask(Integer.parseInt(input.nextLine()));
                        System.out.println("Deadline changed successfully");

                        System.out.println();
                        System.out.println("Press enter key to continue");
                        input.nextLine();
                    }
                    case 6 -> {
                        saveTasksInDatabase(tasks);
                        System.out.println("Tasks saved successfully");

                        System.out.println();
                        System.out.println("Press enter key to continue");
                        input.nextLine();
                    }
                    case 7 -> {
                        loadTasksFromDatabase();
                        System.out.println("Tasks loaded successfully");

                        System.out.println();
                        System.out.println("Press enter key to continue");
                        input.nextLine();
                    }
                    case 8 -> {
                        deleteTasksFromDatabase(tasks);
                        System.out.println("Tasks deleted successfully");

                        System.out.println();
                        System.out.println("Press enter key to continue");
                        input.nextLine();
                    }
                    case 9 -> {
                        List<Task> sortedTasks = taskManager.tasksInPriority(tasks);
                        System.out.println();
                        if (sortedTasks != null){
                            System.out.println("Tasks sorted successfully according to priority");
                            showAllTasks(sortedTasks);
                        }

                        System.out.println();
                        System.out.println("Press enter key to continue");
                        input.nextLine();
                    }
                    case 10 -> {
                        List<Task> sortedTasks  = taskManager.tasksAccordingToDeadline(tasks);
                        System.out.println();
                        if(sortedTasks != null) {
                            System.out.println("Tasks sorted successfully according to deadline");
                            showAllTasks(sortedTasks);
                        }

                        System.out.println();
                        System.out.println("Press enter key to continue");
                        input.nextLine();
                    }
                    case 11 -> {
                        showAllTasks(tasks);
                        System.out.println("Enter task id of task which you want to delete");
                        taskManager.deleteTask(Integer.parseInt(input.nextLine()));
                        System.out.println(tasks);
                        System.out.println("Task has been deleted");

                        System.out.println();
                        System.out.println("Press enter key to continue");
                        input.nextLine();
                    }
                    default -> {
                        System.out.println("Program exited successfully");
                        check = false;
                    }
                }
            }
            catch (Exception e){
                System.out.println("Program exited successfully");
                check = false;
            }
        }
    }

    public static DatabaseService getDatabaseService() {
        return databaseService;
    }

    public static void setDatabaseService(DatabaseService databaseService){
        TaskManagerApp.databaseService = databaseService;
    }
}