/**
 * 
 */
package fr.team0w.bluetooth.scanner.service;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

/**
 * @author Nic0w
 *
 */
public class ServicesDiscoverer implements DiscoveryListener {
	
	private final DiscoveryAgent discoveryAgent;
	
	
	
	public ServicesDiscoverer(DiscoveryAgent agent) {
		
		this.discoveryAgent = agent;
		
		
		
	}


	public void discoverServicesFor(RemoteDevice device) {
		
	}
	
	@Override
	public void deviceDiscovered(RemoteDevice arg0, DeviceClass arg1) { }


	@Override
	public void inquiryCompleted(int arg0) { }


	@Override
	public void serviceSearchCompleted(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void servicesDiscovered(int arg0, ServiceRecord[] arg1) {
		// TODO Auto-generated method stub
		
	}

}
