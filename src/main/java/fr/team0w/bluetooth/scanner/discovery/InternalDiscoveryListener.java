/**
 * 
 */
package fr.team0w.bluetooth.scanner.discovery;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

import com.google.common.util.concurrent.CheckedFuture;
import com.google.common.util.concurrent.ListenableFuture;

/**
 * @author nic0w
 *
 */
public class InternalDiscoveryListener implements DiscoveryListener, CheckedFuture<RemoteDevice[], BluetoothStateException> {

 	private final DiscoveryListener externalListener;
 	private final DiscoveryAgent agent;
 
 	private BluetoothStateException exception;
 	
 	private boolean isDone, isCancelled, isRunning;
 	
    public InternalDiscoveryListener(DiscoveryAgent agent, DiscoveryListener userListener) {
     	
     	this.externalListener = userListener;
     	this.agent = agent;
     	
     	this.exception = null;
     	
     	this.isDone = false;
     	this.isCancelled = false;	
     	this.isRunning = true;
    }
    
    
    protected void exceptionThrown(BluetoothStateException e) {
         	this.exception = e;
    }

	public void addListener(Runnable listener, Executor executor)
	 {
	  // TODO Auto-generated method stub
	  
	 }

	public boolean cancel(boolean mayInterruptIfRunning) {
	 	
	 	this.isCancelled = this.agent.cancelInquiry(this);
	 	
	 	return this.isCancelled;
	}

	public boolean isCancelled() {
	    return this.isCancelled;
	}

	public boolean isDone() {
	    return this.isDone;
	}

	public RemoteDevice[] get() throws InterruptedException, ExecutionException {
	  	if(this.exception == null) 
	  	 	return this.agent.retrieveDevices(DiscoveryAgent.CACHED);
	  	else
	  	 	return null;
	}

	public RemoteDevice[] get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException
	 {
	  long stopTime  = System.currentTimeMillis() + unit.toMillis(timeout);
	  
	  while(System.currentTimeMillis() <= stopTime) {
	   
	   	if(this.isDone()) return this.get();
	   
	   	Thread.sleep(10);
	  }
	  
	  if(this.isDone()) 
	   return this.get(); 
	  else
	   throw new TimeoutException();
	 }

	public RemoteDevice[] checkedGet() throws BluetoothStateException {
	  	if(this.exception == null)
	  	 	return this.agent.retrieveDevices(DiscoveryAgent.CACHED);
	  	else
	  	 	throw this.exception;
	 }

	public RemoteDevice[] checkedGet(long timeout, TimeUnit unit) throws TimeoutException, BluetoothStateException {
	  	if(this.exception == null) {
	  	 	try {
			  return this.get(timeout, unit);
			 }
			catch (InterruptedException e) {
			  return null;
			 }
			catch (ExecutionException e) {
			  return null;
			 }
	  	}
	  	else
	  	 	throw this.exception;
	 }

	public void deviceDiscovered(RemoteDevice arg0, DeviceClass arg1) {
	
	 	if(this.externalListener != null) this.externalListener.deviceDiscovered(arg0, arg1);
	 
	}

	public void inquiryCompleted(int arg0) {
	 	
	 	if(this.externalListener != null) this.externalListener.inquiryCompleted(arg0);
	
	  	this.isDone = true;
	}

	public void serviceSearchCompleted(int arg0, int arg1) {
	  	
	 	if(this.externalListener != null) this.externalListener.serviceSearchCompleted(arg0, arg1);
	  
	}

	public void servicesDiscovered(int arg0, ServiceRecord[] arg1) {
	  	if(this.externalListener != null) this.externalListener.servicesDiscovered(arg0, arg1);
	  
	}


}
