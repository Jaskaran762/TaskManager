import org.dal.Task;
import org.dal.TaskManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * this class contains all the unit tests for
 * testing the behaviour of methods of
 * TaskManager class. Mockito is used
 * to mock the Scanner(input from the user).
 */
public class TaskManagerTest {

    private static final Scanner scannerMock = mock(Scanner.class);
    private static TaskManager test = null;
    public static Task task = null;

    /**
     * this method helps to create an instance of TaskManager
     */
    @BeforeAll
    public static void inputSetup(){

        test = new TaskManager(scannerMock);
    }

    @BeforeEach
    public void addDummyTasks(){
        Task task1 = new Task("code",3,LocalDateTime.MAX,"study");
        Task task2 = new Task("cricket",2,LocalDateTime.now().plusHours(2),"sport");
        test.addTask(task1);
        test.addTask(task2);

        task = task2;
    }

    @AfterEach
    public void deleteTasks(){
        test.setTasks(new ArrayList<>());
    }

    /**
     * method to test addTask()
     */
    @Test
    public void checkAddTask(){

        when(scannerMock.nextInt()).thenReturn(4);
        when(scannerMock.nextLine()).thenReturn("My first task","4","22-06-2023-13-34-23", "study");

        task = test.addTask().get(test.getTasks().size()-1);


        assertEquals("My first task", task.getTaskName(),"Task Name check ");
        assertEquals(4, task.getPriority(), "Task priority check ");

        verify(scannerMock, times(4)).nextLine();
    }

    /**
     * method to test editCompleteTask()
     */
    @Test
    public void checkEditCompleteTask(){

        when(scannerMock.nextInt()).thenReturn(3);
        when(scannerMock.nextLine()).thenReturn("My second task","3","25-06-2023-13-34-23", "sport");
        Task editedTask = test.editCompleteTask(task.getTaskId());

        assertEquals("My second task", editedTask.getTaskName(),"Task Name check ");
        assertEquals(3, editedTask.getPriority(), "Task priority check ");
        assertEquals(task.getTaskId(), editedTask.getTaskId(), "Task id check ");

        verify(scannerMock, times(4)).nextLine();
    }

    /**
     * method to test changeDeadlineOfTask()
     */
    @Test
    public void checkChangeDeadlineOfTask(){

        when(scannerMock.nextLine()).thenReturn("28-06-2023-13-34-23");
        test.changeDeadlineOfTask(task.getTaskId());

        String newDeadline = "28-06-2023-13-34-23";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
        LocalDateTime newDeadlineDate = LocalDateTime.parse(newDeadline,formatter);

        assertEquals(newDeadlineDate, task.getDeadline(),"Task id check ");

        verify(scannerMock).nextLine();
    }

    /**
     * method to test changePriorityOfTask()
     */
    @Test
    public void checkChangePriorityOfTask(){

        when(scannerMock.nextLine()).thenReturn("7");
        test.changePriorityOfTask(task.getTaskId());

        assertEquals(7, task.getPriority(), "Priority check:");
    }

    /**
     * method to test tasksInPriority()
     */
    @Test
    public void checkTasksInPriority(){

        Task task3 = new Task("code",1,LocalDateTime.now().plusHours(1),"IT");
        test.addTask(task3);
        List<Task> tasks = test.getTasks();
        List<Task> sortedListOfTasks = new ArrayList<>(tasks);

        sortedListOfTasks.sort(Comparator.comparing(Task::getPriority));
        List<Task> sortedTasks = test.tasksInPriority(tasks);

        assertNotEquals(tasks, sortedTasks);
        assertEquals(sortedListOfTasks, sortedTasks);
    }

    /**
     * method to test tasksAccordingToDeadline()
     */
    @Test
    public void checkTasksAccordingToDeadline(){

        Task task3 = new Task("code",1,LocalDateTime.now().plusHours(1),"IT");
        test.addTask(task3);
        List<Task> tasks = test.getTasks();
        List<Task> sortedListOfTasks = new ArrayList<>(tasks);

        sortedListOfTasks.sort(Comparator.comparing(Task::getDeadline));
        List<Task> sortedTasksUsingMethod = test.tasksAccordingToDeadline(tasks);

        assertNotEquals(tasks, sortedTasksUsingMethod);
        assertEquals(sortedListOfTasks, sortedTasksUsingMethod);
    }

    /**
     * method to test deleteTask()
     */
    @Test
    public void checkDeleteTasks(){

        List<Task> tasks = test.getTasks();
        Task task = tasks.get(tasks.size()-1);

        assertTrue(test.getTasks().contains(task));

        test.deleteTask(task.getTaskId());
        assertFalse(test.getTasks().contains(task));
    }
}
