package org.bevj.app;

import javax.swing.*;

import org.bevj.app.ecs.Plugin;
import org.bevj.app.toolkit.ScreenToolkit;

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

    public App addSystems() {
        return this;
    }

    // public App add_boxed_plugin(Plugin plugin) {
    //     if (plugin.is_unique() && this.main_mut().plugin_names.contains(plugin.name())) {
    //         throw new RuntimeException("duplicated plugin");
    //         // Err(AppError::DuplicatePlugin {
    //         //     plugin_name: plugin.name().to_string(),
    //         // })?;
    //     }

    //     // Reserve position in the plugin registry. If the plugin adds more plugins,
    //     // they'll all end up in insertion order.
    //     let index = self.main().plugin_registry.len();
    //     self.main_mut()
    //         .plugin_registry
    //         .push(Box::new(PlaceholderPlugin));

    //     self.main_mut().plugin_build_depth += 1;
    //     let result = catch_unwind(AssertUnwindSafe(|| plugin.build(self)));
    //     self.main_mut()
    //         .plugin_names
    //         .insert(plugin.name().to_string());
    //     self.main_mut().plugin_build_depth -= 1;

    //     if

    // let Err(payload) = result {
    //         resume_unwind(payload);
    //     }

    //     self.main_mut().plugin_registry[index] = plugin;
    //     Ok(self)
    // }
}
