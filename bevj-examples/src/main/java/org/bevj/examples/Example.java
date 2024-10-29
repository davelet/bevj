package org.bevj.examples;

import javax.swing.SwingUtilities;

import org.bevj.app.App;


public class Example {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.run();
        });
    }
}
