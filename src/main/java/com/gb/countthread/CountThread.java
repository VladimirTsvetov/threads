package com.gb.countthread;

import com.gb.safecounter.Safecounter;

import java.util.concurrent.locks.ReentrantLock;

public class CountThread implements Runnable{

    Safecounter safecounter;
    ReentrantLock locker;
    public CountThread(Safecounter safecounter, ReentrantLock lock){
        this.safecounter=safecounter;
        locker = lock;
    }
    public void run(){

        locker.lock(); // устанавливаем блокировку
        try{
            for (int i = 1; i < 5; i++){

                safecounter.setCount();
                System.out.printf("%s %d \n", Thread.currentThread().getName(), safecounter.getCount());
                Thread.sleep(100);
            }
        }
        catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        finally{
            locker.unlock(); // снимаем блокировку
        }
    }
}
