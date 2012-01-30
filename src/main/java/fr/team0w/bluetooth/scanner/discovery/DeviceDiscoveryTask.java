/**
 * 
 */
package fr.team0w.bluetooth.scanner.discovery;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;

import com.google.common.util.concurrent.ListenableFuture;


/**
 * @author Nic0w
 *
 */
public class DeviceDiscoveryTask {

	
	private final DiscoveryAgent discoveryAgent;
	
	private InternalDiscoveryListener currentDiscovery;
	
	public DeviceDiscoveryTask(DiscoveryAgent agent) {
		
		this.discoveryAgent = agent;
		
		this.currentDiscovery = null;
		
	}

		
	public ListenableFuture<RemoteDevice[]> startDiscovery(DiscoveryListener userListener) {
	 
	 	if(this.currentDiscovery != null && this.currentDiscovery.isDone()) {
	 	 	return this.currentDiscovery;
	 	 
	 	}
	 	
	 	this.currentDiscovery = new InternalDiscoveryListener(this.discoveryAgent, userListener);
	 	
	 	try {
		  	this.discoveryAgent.startInquiry(DiscoveryAgent.GIAC, (DiscoveryListener) this.currentDiscovery);
		 }
		catch (BluetoothStateException e) {
		  	this.currentDiscovery.exceptionThrown(e);
		 }
	
	 	return this.currentDiscovery;
	}


}
