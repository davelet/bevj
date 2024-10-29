package org.bevj.examples;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public MyFrame() {
        // 设置窗口标题
        setTitle("My Swing Application");
        // 设置窗口大小
        setSize(400, 300);
        // 设置窗口关闭操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置布局管理器，这里使用默认的BorderLayout
        setLayout(new BorderLayout());

        // 创建一个面板（Panel）作为容器
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        // 创建一些组件，并添加到面板中
        JButton button = new JButton("Click Me");
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 20)); // 创建一个线边框
        JLabel label = new JLabel("Hello, Swing!");
        label.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 50)); // 创建一个空边框
        JTextField textField = new JTextField("Enter text here", 20);
        textField.setPreferredSize(new Dimension(100, 50)); // 设置首选尺寸

        JButton button1 = new JButton("Click Me");
        JLabel label1 = new JLabel("Hello, Swing!");
        JTextField textField1 = new JTextField("Enter text here", 20);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        
        panel.add(button);
        panel.add(label);
        panel.add(textField);
        panel1.add(button1);
        panel1.add(label1);
        panel1.add(textField1);
        // 将面板添加到窗口中
        add(panel, BorderLayout.CENTER);
        add(panel1, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        // 在事件调度线程中创建和显示窗口
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MyFrame().setVisible(true);
            }
        });
    }
}
