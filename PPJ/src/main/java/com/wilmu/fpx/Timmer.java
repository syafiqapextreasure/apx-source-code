/*
 * Decompiled with CFR 0.152.
 */
package com.wilmu.fpx;

import java.util.Timer;
import java.util.TimerTask;

public class Timmer {
    Timer timer = new Timer();

    Timmer(int seconds) {
        this.timer.schedule((TimerTask)new RemindTask(), seconds);
    }

    public static String testing(int oritime) {
        new Timmer(oritime);
        return "END 1234";
    }

    class RemindTask
    extends TimerTask {
        RemindTask() {
        }

        @Override
        public void run() {
            System.out.println("You have a notification!");
            Timmer.this.timer.cancel();
        }
    }
}
