package com.gb;

import com.gb.countthread.CountThread;
import com.gb.pingpong.Game;
import com.gb.safecounter.Safecounter;

import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Game game = new Game();
        Thread thread1 = new Thread(()->{
            game.ping();
        });
        Thread thread2 = new Thread(()->{
            game.pong();
        });
        System.out.println("Let's get ping-pong in this hole!");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("Game over!");
        // Тести Locker
        Safecounter safecounter = new Safecounter();
        ReentrantLock locker = new ReentrantLock(); // создаем заглушку
        for (int i = 1; i < 6; i++){

            Thread t = new Thread(new CountThread(safecounter, locker));
            t.setName("Thread "+ i);
            t.start();
        }


    }
}