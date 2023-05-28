package org.dal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * This class provide basic functionality
 * to this console-based app. Contains methods to
 * add or edit a task, change deadline and also priority of
 * a task.
 */
public class TaskManager {

    private List<Task> tasks;
    private final Scanner input;

    public TaskManager(Scanner input){
        this.input = input;
    }

    /**
     * this method helps to add a task.
     * @return List containing tasks added by the user
     */
    public List<Task> addTask(){
        Task task = new Task();

        if (tasks == null) {
            task.setTaskId(1);
            tasks = new ArrayList<>();
        }
        else
            task.setTaskId(tasks.size()+1);

        setTaskAttributes(task, input);

        tasks.add(task);

        return tasks;
    }

    /**
     * this method helps to add a task.
     * @param task task which needs to be added to the list of task
     * @return list containing all the tasks
     */
    public List<Task> addTask(Task task){

        if (tasks == null) {
            task.setTaskId(1);
            tasks = new ArrayList<>();
        }
        else {
            task.setTaskId(tasks.size() + 1);
        }
        tasks.add(task);
        return tasks;
    }

    /**
     * this method helps to delete a task from the list of tasks
     * @param taskId id of the task which needs to be deleted
     * @return list after the task has been deleted
     */
    public List<Task> deleteTask(Integer taskId){

        Optional<Task> task = getTaskOptionalById(taskId);
        if (task.isPresent())
            tasks.remove(task.get());
        return tasks;
    }

    /**
     * this method helps to edit the complete task
     * created by the user.
     * @param taskId belonging to the task which the user
     *               wants to edit
     * @return reference to the task which has been edited
     */
    public Task editCompleteTask(int taskId){

        Optional<Task> requiredTask = getTaskOptionalById(taskId);

        requiredTask.ifPresent(task -> setTaskAttributes(task, input));
        return requiredTask.get();
    }

    /**
     * this method helps to change
     * deadline of a task.
     *
     * @param taskId belonging to the task which the user
     *               wants to edit
     */
    public void changeDeadlineOfTask(int taskId){

        Optional<Task> requiredTask = getTaskOptionalById(taskId);

        requiredTask.ifPresent(task -> setDeadlineInTask(task, input));
    }

    /**
     * this method changes priority of a task.
     *
     * @param taskId belonging to the task which the user
     *               wants to edit
     */
    public void changePriorityOfTask(int taskId){

        Optional<Task> requiredTask = getTaskOptionalById(taskId);

        if (requiredTask.isPresent()){
            System.out.println("Enter task priority ranging from 1 to 10, 10 being least important");
            requiredTask.get().setPriority(Integer.parseInt(input.nextLine()));
        }
        requiredTask.get();
    }

    /**
     * this method will return tasks in priority
     * @param userTasks tasks entered by the user
     * @return tasks sorted according to the priority
     */
    public List<Task> tasksInPriority(List<Task> userTasks){
        List<Task> tasks = new ArrayList<>(userTasks);
        return tasks.stream().sorted(Comparator.comparing(Task::getPriority)).toList();
        //return tasks.stream().sorted((task1, task2) -> ((Integer)task1.getPriority()).compareTo(task2.getPriority())).toList();
    }

    /**
     * this method will return tasks according to the deadline
     * @param userTasks tasks entered by the user
     * @return tasks sorted according to the deadline
     */
    public List<Task> tasksAccordingToDeadline(List<Task> userTasks){
        List<Task> tasks = new ArrayList<>(userTasks);
        return tasks.stream().sorted(Comparator.comparing(Task::getDeadline)).toList();
    }

    /**
     *this method helps to set task attributes
     * according to the input from the user.
     * @param task reference to the Task object
     * @param input reference to the Scanner class
     */
    private void setTaskAttributes(Task task, Scanner input){
        System.out.println("Enter a task name to store");
        task.setTaskName(input.nextLine());

        System.out.println("Enter task priority ranging from 1 to 10, 10 being least important");
        task.setPriority(Integer.parseInt(input.nextLine()));

        setDeadlineInTask(task, input);

        System.out.println("Enter task type");
        task.setTaskType(input.nextLine());
    }

    /**
     * this method helps to set a deadline to a task.
     * Date entered by the user should be in format 'DD-MM-YYYY-hh-mm-ss'.
     * @param task reference to the Task object
     * @param input reference to the Scanner class
     */
    private void setDeadlineInTask(Task task, Scanner input) {
        System.out.println("Enter task deadline in format \"DD-MM-YYYY-hh-mm-ss\" like \"17-05-2023-13-34-23\"");
        String userEnteredDate = input.nextLine();
        boolean dateChecker = false;
        String dateFormatReg = "^(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[0-2])-[0-9]+-(0?[0-9]|1[0-9]|2[0-3])-(0?[0-9]|[1-5][0-9])-(0?[0-9]|[1-5][0-9])$";
        while(!dateChecker){
            if(userEnteredDate.matches(dateFormatReg)){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
                LocalDateTime fetchedDate = LocalDateTime.parse(userEnteredDate,formatter);
                if (fetchedDate.isAfter(LocalDateTime.now())){
                    task.setDeadline(fetchedDate);
                    dateChecker = true;
                }
                else{
                    System.out.println("Deadline time should be a future time");
                    userEnteredDate = input.nextLine();
                }
            }
            else{
                System.out.println("Please enter date in the correct format: \"DD-MM-YYYY-hh-mm-ss\" like \"17-05-2023-13-34-23\"");
                userEnteredDate = input.nextLine();
            }
        }
    }

    /**
     * this method returns the reference to the Task class object
     * based on the task id entered by the user.
     * @param taskId belonging to the task which the user
     *      *              wants to edit
     * @return reference to the task if present
     */
    private Optional<Task> getTaskOptionalById(int taskId) {
        return tasks.stream().filter(task -> task.getTaskId() == taskId).findAny();
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
