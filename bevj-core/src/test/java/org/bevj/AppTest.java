package org.bevj;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;

class MyFirstJUnitJupiterTests {

    @Test
    void testNew() {
        assertDoesNotThrow(() -> {
            new App().run();
        });
    }

}