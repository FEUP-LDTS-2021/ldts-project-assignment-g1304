package asteroids;

import asteroids.control.Controller;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;

public class ApplicationTest {

    @Test
    void runController() throws IOException {

        // given
        Controller controller = Mockito.mock(Controller.class);
        try (MockedStatic<Controller> theMock = Mockito.mockStatic(Controller.class)) {
            theMock.when(Controller::getInstance).thenReturn(controller);

            // when
            Application.main(null);

            // then
            Mockito.verify(controller).run();
        }

    }
}
