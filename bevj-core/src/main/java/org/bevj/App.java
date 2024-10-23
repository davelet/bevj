package org.bevj;

import javax.swing.*;

import org.bevj.toolkit.ScreenToolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {
    private Timer timer;
    private static final int interval = 1000 / 60;

    public void run() {
        setTitle("Swing Bevj");
        setSize(300 * 2, 200 * 2);
        setLocation(ScreenToolkit.offsetCenter(300, 200));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add a swing timer
        timer = new Timer(interval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 定时器触发时执行的代码
                System.out.println(System.currentTimeMillis() + ": Timer ticked!");
            }
        });

        // 启动定时器
        timer.start();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.run();
        });
    }
}
