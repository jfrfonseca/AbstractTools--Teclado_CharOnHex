package MainProgram;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrinterThread extends Thread{
	
	// Default waiting time to re-check the tasks assigned (in ms)
	protected static final int DEFAULT_WAITING_TIME = 5;
	
	// Task list (i.e. "produce to be consumed")
	protected ArrayList<String> tasks;
	// Export tool (i.e. "consumption method")
	protected PrintWriter printWriter;
	// Synchronism lock
	protected Lock locker = new ReentrantLock();
	
	/**
	 * Builds the interruptible auto printer. Will print to the filed whose the name is specified as parameter
	 * @param fileName - destination file
	 */
	public PrinterThread(String fileName){
		try{	
	        printWriter = new PrintWriter(new FileWriter(new File(fileName)), true);
	        tasks = new ArrayList<String>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Assigns a string for this thread to print (producer). The thread needs to acquire its own lock to add a task
	 * @param data - string to be printed
	 */
	public void assign(String data){
		locker.lock();
		tasks.add(data);
		locker.unlock();
	}
	
	/**
	 * Only proper way to interrupt the execution of this object.
	 * Will first acquire the lock, never to release, preventing new prints to start.
	 * Then will interrupt the printer thread, breaking the "run" loop.
	 * If there are still tasks to be printed, will consume and print all of those.
	 * Then it closes the printer
	 * And returns true (it is used to be sure that the caller will have to wait completion of this method to move on).
	 * @return "true"
	 */
	public boolean shutdown(){
    	locker.lock();
    	this.interrupt();
		while(!tasks.isEmpty()){
			printAtask();
		}
		printWriter.close();
		return true;
	}
	
	/**
	 * prints the first task in the task list, also removing the first task from the list (consumer)
	 */
	@SuppressWarnings("static-access")
	private void printAtask() {
		locker.lock();
		printWriter.append(tasks.remove(0));
		locker.unlock();
		this.yield();	//avoid it to prevent the execution of other prior threads
	}

	/**
	 * Execution of the printer thread.
	 * it will start to be stopped only by interruption (the try clause will give the exception no treatment than just catch it and stop)
	 * it will print a task, yield execution to another thread (as an new task assignment or and interruption) and wait several milisseconds
	 * And keep running. If there are no tasks to be printed, it will just yield and wait.
	 */
	@SuppressWarnings("static-access")
	public void run(){
		try {
			while(true){
				if(!tasks.isEmpty()){// if there are strings to be print
					printAtask();	 // consumes a string
				} else { 			 // if the task list is empty
					this.yield(); 	 // allow other threads to execute
					this.sleep(DEFAULT_WAITING_TIME);	 // waits a moment
				}
			}
		} catch (InterruptedException e) {
			//e.printStackTrace(); 	 // If locked, it just stays locked
		}
	}
}
