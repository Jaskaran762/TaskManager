import org.dal.DatabaseService;
import org.dal.Task;
import org.dal.TaskManagerApp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * this class contains all the unit tests for
 * testing the behaviour of methods of
 * TaskManagerApp class. Mockito is used
 * to mock the database service.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TaskManagerAppTest {

    @Mock
    private static DatabaseService databaseServiceMock;
    private static List<Task> tasks = null;
    @Captor
    private ArgumentCaptor<List<Task>> tasksCaptor;

    private static TaskManagerApp app;

    /**
     * this method helps to initialize list of tasks
     */
    @BeforeAll
    public static void appTestSetup(){
        tasks = new ArrayList<>(){};
    }

    /**
     * method helps to pass mock of DatabaseService to TaskManagerApp class
     */
    @BeforeEach
    public void dbSetup(){
        TaskManagerApp.setDatabaseService(databaseServiceMock);
    }

    /**
     * this method helps to test the functionality of saveTasksInDatabase() method
     */
    @Test
    @Order(1)
    public void checkSaveTasksInDatabase(){
        when(databaseServiceMock.saveData(tasks)).thenReturn(true);

        boolean result = TaskManagerApp.saveTasksInDatabase(tasks);

        verify(databaseServiceMock).saveData(tasksCaptor.capture());
        assertEquals(tasks, tasksCaptor.getValue());
        assertTrue(result, "status of tasks saving to database");
    }

    /**
     * this method helps to test the functionality of loadTasksFromDatabase() method
     */
    @Test
    @Order(2)
    public void checkLoadTasksFromDatabase(){
        when(databaseServiceMock.loadData()).thenReturn(tasks);
        TaskManagerApp.loadTasksFromDatabase();

        verify(databaseServiceMock).loadData();
    }

    /**
     * this method helps to test the functionality of deleteTasksFromDatabase() method
     */
    @Test
    @Order(3)
    public void checkDeleteTasksFromDatabase(){
        when(databaseServiceMock.deleteData(tasks)).thenReturn(false);
        boolean result = TaskManagerApp.deleteTasksFromDatabase(tasks);
        assertFalse(result);

        verify(databaseServiceMock).deleteData(tasks);
    }
}
