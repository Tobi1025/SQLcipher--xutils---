package com.lidroid.xutils.task;

/**
 * Author: wyouflf
 * Date: 14-5-16
 * Time: 上午11:25
 */
public enum Priority {
    UI_TOP(0), UI_NORMAL(1), UI_LOW(2), DEFAULT(3), BG_TOP(4), BG_NORMAL(5), BG_LOW(6);
    private int value = 0;

    Priority(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    public static Priority valueOf(int value) {
        switch (value) {
            case 0:
                return UI_TOP;
            case 1:
                return UI_NORMAL;
            case 2:
                return UI_LOW;
            case 3:
                return DEFAULT;
            case 4:
                return BG_TOP;
            case 5:
                return BG_NORMAL;
            case 6:
                return BG_LOW;
            default:
                return DEFAULT;
        }
    }
}
