import org.example.Task;
import org.example.TaskManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskManagerTest {

    private static Scanner scannerMock = mock(Scanner.class);
    private static TaskManager test = null;
    public static Task task = null;

    @BeforeAll
    public static void inputSetup(){

        test = new TaskManager(scannerMock);
    }
    @Test
    public void checkAddTask(){

        when(scannerMock.nextInt()).thenReturn(4);
        when(scannerMock.nextLine()).thenReturn("My first task","22-06-2023-13-34-23", "study");

        task = test.addTask().get(0);
        assertEquals("My first task", task.getTaskName(),"Task Name check ");
        assertEquals(4, task.getPriority(), "Task priority check ");

        verify(scannerMock, times(3)).nextLine();
        verify(scannerMock).nextInt();
    }

    @Test
    public void checkEditCompleteTask(){

        checkAddTask();

        when(scannerMock.nextInt()).thenReturn(3);
        when(scannerMock.nextLine()).thenReturn("My second task","25-06-2023-13-34-23", "sport");
        Task editedTask = test.editCompleteTask(task.getTaskId());

        assertEquals("My second task", editedTask.getTaskName(),"Task Name check ");
        assertEquals(3, editedTask.getPriority(), "Task priority check ");
        assertEquals(task.getTaskId(), editedTask.getTaskId(), "Task id check ");

        verify(scannerMock, times(6)).nextLine();
        verify(scannerMock, times(2)).nextInt();
    }

    @Test
    public void checkChangeDeadlineOfTask(){

        checkAddTask();

        when(scannerMock.nextLine()).thenReturn("28-06-2023-13-34-23");
        test.changeDeadlineOfTask(task.getTaskId());

        String newDeadline = "28-06-2023-13-34-23";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
        LocalDateTime newDeadlineDate = LocalDateTime.parse(newDeadline,formatter);

        assertEquals(newDeadlineDate, task.getDeadline(),"Task id check ");

        verify(scannerMock, times(4)).nextLine();
    }

    @Test
    public void checkChangePriorityOfTask(){

        checkAddTask();

        when(scannerMock.nextInt()).thenReturn(7);
        test.changePriorityOfTask(task.getTaskId());

        assertEquals(7, task.getPriority(), "Priority check:");
    }
}
