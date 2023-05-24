import org.example.Database;
import org.example.Task;
import org.example.TaskManagerApp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TaskManagerAppTest {

    @Mock
    private static Database databaseMock;
    private static List<Task> tasks = null;
    @Captor
    private ArgumentCaptor<List<Task>> tasksCaptor;

    @BeforeAll
    public static void appTestSetup(){
        tasks = new ArrayList<>(){};
    }

    @BeforeEach
    public void dbSetup(){
        TaskManagerApp.database = databaseMock;
    }

    @Test
    public void checkSaveTasksInDatabase(){
        when(databaseMock.saveData(tasks)).thenReturn(true);

        TaskManagerApp.saveTasksInDatabase(tasks);

        verify(databaseMock).saveData(tasksCaptor.capture());
        assertEquals(tasks, tasksCaptor.getValue());
    }

    @Test
    public void checkLoadTasksFromDatabase(){
        when(databaseMock.loadData()).thenReturn(tasks);
        TaskManagerApp.loadTasksFromDatabase();
        verify(databaseMock).loadData();
    }
}
