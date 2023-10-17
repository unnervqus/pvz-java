package com.rxnqst.pvz.utils;

public class Utils {
    public static boolean checkCollision(Rect rect, int x, int y) {
        return checkInterval(rect.y, rect.height, y) && checkInterval(rect.x, rect.width, x);
    }
    public static boolean checkInterval(int pos, int length, int src) {
        return pos <= src && src <= pos + length;
    }

    public static boolean checkBoxesOverlap(Rect rect1, Rect rect2) {
        if (rect1.x + rect1.width < rect2.x || rect2.x + rect2.width < rect1.x) return false;
        if (rect1.y + rect1.height < rect2.y || rect2.y + rect2.height < rect1.y) return false;
        return true;
    }

}
