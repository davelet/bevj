package org.bevj;

import javax.swing.SwingUtilities;

public class Example {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.run();
        });
    }
}
