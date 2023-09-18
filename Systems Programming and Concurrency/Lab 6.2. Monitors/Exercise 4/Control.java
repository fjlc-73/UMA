/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ex4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author galvez
 */
public class Control {
    public static final int NUM_RESOURCES=2;
    public static final int[] TOTAL_RESOURCES={10, 10};
    public static int[] resourcesAvailable={10, 10};
    private Queue<Integer> q = new LinkedList<>();
    public Control(){
    }
    
    public synchronized void provideRes(int processCode, int id, int num) throws InterruptedException {
    	q.add(processCode);
    	System.out.println(processCode + " trying to take " + num + " of type " + id);
    	while(q.peek() != processCode || resourcesAvailable[id]<num) {
    		wait();
    	}
    	q.remove();
    	resourcesAvailable[id] -= num;
    	System.out.println(processCode + " has taken " + num + " of type " + id);

    	notifyAll();
    }
    
    public synchronized void releaseRes(int processCode, int id, int num) {
    	resourcesAvailable[id]+=num;
    	System.out.println(processCode + " released " + num + " of type " + id);
    	notifyAll();
    }
    
}
