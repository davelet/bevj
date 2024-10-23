package org.bevj.app.toolkit;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Point;

public class ScreenToolkit {
    public static Dimension getScreenSize() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        return screenSize;
    }

    public static Point getScreenCenter() {
        Dimension screenSize = getScreenSize();
        return new Point(screenSize.width / 2, screenSize.height / 2);
    }

    public static Point offsetCenter(int offsetX, int offsetY) {
        Point screenCenter = getScreenCenter();
        return new Point(screenCenter.x - offsetX, screenCenter.y - offsetY);
    }
}
