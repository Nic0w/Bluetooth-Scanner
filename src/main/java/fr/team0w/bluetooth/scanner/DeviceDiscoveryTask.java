/**
 * 
 */
package fr.team0w.bluetooth.scanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
import com.google.common.util.concurrent.ListenableFutureTask;

import fr.team0w.bluetooth.scanner.device.DiscoveredDevice;


/**
 * @author Nic0w
 *
 */
public class DeviceDiscoveryTask extends Thread implements DiscoveryListener, CheckedFuture<DiscoveredDevice[], BluetoothStateException> {

	
	private final DiscoveryAgent discoveryAgent;
	
	private final ArrayList<DiscoveredDevice> deviceList;
	private final Map<Runnable, Executor> listeners;
	
	private BluetoothStateException exception;
	
	private boolean isDone, isCancelled, isRunning;
	
	public DeviceDiscoveryTask(DiscoveryAgent agent) {
	
	 	this.deviceList = new ArrayList<DiscoveredDevice>();
	
	 	this.listeners = new HashMap<Runnable, Executor>();
	 	
		this.discoveryAgent = agent;
		this.exception = null;
	}

	@Override
	public void addListener(Runnable listener, Executor executor) {
	 this.listeners.put(listener, executor);
	}

	@Override
	public boolean cancel(boolean interruptIfRunning)
	 {
	  if(this.isRunning && interruptIfRunning) {
	   
	   	this.isCancelled = this.discoveryAgent.cancelInquiry(this);
	   
	   	return this.isCancelled;
	  }
	  
	  return false;
	 }

	@Override
	public DiscoveredDevice[] get() throws InterruptedException, ExecutionException {
	 
	  return this.deviceList.toArray(new DiscoveredDevice[] {});
	}

	@Override
	public DiscoveredDevice[] get(long time, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException
	 {
	  long stop = System.currentTimeMillis() + unit.toMillis(time);
	  
	  while(System.currentTimeMillis() <= stop) {
	   
	   if(this.isDone) 
		return this.deviceList.toArray(new DiscoveredDevice[] {});
	   
	   Thread.sleep(10);
	  }
	  
	  if(this.isDone) 
	   return this.deviceList.toArray(new DiscoveredDevice[] {});
	  
	  throw new TimeoutException();
	 }

	@Override
	public boolean isCancelled() {
	  return this.isCancelled;
	}

	@Override
	public boolean isDone() {
	  return this.isDone;
	}

	@Override
	public DiscoveredDevice[] checkedGet() throws BluetoothStateException
	 {
	  if(this.exception==null) {
	    return this.deviceList.toArray(new DiscoveredDevice[] {});
	  }
	  else
	    throw this.exception;
	 }

	@Override
	public DiscoveredDevice[] checkedGet(long timeout, TimeUnit unit) throws TimeoutException, BluetoothStateException
	 {
	  if(this.exception==null) {
	   
	   long stop = System.currentTimeMillis() + unit.toMillis(timeout);
		  
	   while(System.currentTimeMillis() <= stop) {
		   
		   if(this.isDone) 
			return this.deviceList.toArray(new DiscoveredDevice[] {});
		   
		   try { 
			Thread.sleep(10);
		   }
		   catch (InterruptedException e) {
			 break;
		   }
	   }
	   
	   if(this.isDone) 
		return this.deviceList.toArray(new DiscoveredDevice[] {});

	   throw new TimeoutException();
	   
	  }
	  else
	    throw this.exception;
	 }

	@Override
	public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
	  	this.deviceList.add(new DiscoveredDevice(btDevice, cod));
	 }

	@Override
	public void inquiryCompleted(int discType)
	 {
	  this.isDone = true;
	  
	  for(Map.Entry<Runnable, Executor> listener : this.listeners.entrySet())
	   listener.getValue().execute(listener.getKey());
	  
	  
	  System.out.println("Search finished !");
	 }

	@Override
	public void serviceSearchCompleted(int transID, int respCode)
	 {
	  // TODO Auto-generated method stub
	  
	 }

	@Override
	public void servicesDiscovered(int transID, ServiceRecord[] servRecord)
	 {
	  System.out.println("Service discovered ");
	  
	 }

	@Override
	public void run() {
	  this.isRunning = true;
	  try  {
		this.discoveryAgent.startInquiry(DiscoveryAgent.GIAC, this);
	  }
	  catch (BluetoothStateException e) {
		this.exception = e;
		this.isRunning = false;
		this.isCancelled = true;
	  }
	  
	  while(isRunning && !this.isDone) { 

	   	try {
			Thread.sleep(50);
	    }
	   	catch (InterruptedException e) {
		 	isRunning = false;
		 	this.cancel(true);
		 	e.printStackTrace();
	   	}
	   
	  }
	  
	 }


}
