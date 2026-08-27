import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldTest {

    @Test
    void testMessage() {
        assertEquals(
            "Hello from Maven and Jenkins!",
            HelloWorld.message()
        );
    }
}