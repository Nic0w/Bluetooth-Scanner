/**
 * 
 */
package fr.team0w.bluetooth.scanner.device;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.RemoteDevice;


/**
 * @author nic0w
 *
 */
public class DiscoveredDevice {
 
 	private final RemoteDevice remoteBTDevice;
 	private final DeviceType deviceType;
 
 	public DiscoveredDevice(RemoteDevice btDevice, DeviceClass devClass) {
 	
 	 	this.remoteBTDevice = btDevice;
 	 	this.deviceType = DeviceType.getTypeFromClass(devClass);
 	}

 	public RemoteDevice getRemoteDevice() {
 	 return this.remoteBTDevice;
 	}
 	
 	public DeviceType getDeviceType() {
 	 
 	 return this.deviceType;
 	 
 	}
 	
 	

}
