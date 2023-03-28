package com.gb.pingpong;

public class Game {
    private final int PING = 1;
    private final int PONG = 2;
    public volatile int gamestate;
    Object mon;

    public Game() {
        this.gamestate = PING;
        this.mon = new Object();
    }

    public void ping() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (this.gamestate != PING) {
                        mon.wait();
                    }
                    System.out.print("Ping!");
                    this.gamestate = PONG;
                    mon.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void pong() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (this.gamestate != PONG) {
                        mon.wait();
                    }
                    System.out.print("Pong!");
                    this.gamestate = PING;
                    mon.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
