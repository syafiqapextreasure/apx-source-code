/*
 * Decompiled with CFR 0.152.
 */
package com.wilmu.fpx;

import java.util.TimerTask;

class Timmer$RemindTask
extends TimerTask {
    Timmer$RemindTask() {
    }

    @Override
    public void run() {
        System.out.println("You have a notification!");
        Timmer.this.timer.cancel();
    }
}
