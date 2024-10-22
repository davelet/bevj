package org.bevj;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

class MyFirstJUnitJupiterTests {

    @Test
    void testNew() {
        assertDoesNotThrow(() -> {
            new App().run();
        });
    }

}