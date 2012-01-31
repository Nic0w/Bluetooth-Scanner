/**
 * 
 */
package fr.team0w.bluetooth.scanner;

import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

import com.google.common.util.concurrent.ListenableFuture;

import fr.team0w.bluetooth.scanner.device.DiscoveredDevice;


/**
 * @author Nic0w
 *
 */
public class BluetoothScanner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Hello World with Maven and Java 7.0");
		
		//System.load("/home/nic0w/Devellopement/Java/bluetooth.scanner/lib/libbluecove_x64.so");
		
		/*for(Entry<Object, Object> property : System.getProperties().entrySet()) {
		 
		 System.out.println(property.getKey() + " => " + System.getProperty(property.getKey().toString()));
		 
		}*/
		
		
		LocalDevice localBTDevice = null;
		
		if(LocalDevice.isPowerOn()) {
			DiscoveryAgent discoveryAgent = null;
			try {
				localBTDevice = LocalDevice.getLocalDevice();
				
				discoveryAgent = localBTDevice.getDiscoveryAgent();
				
			} catch (BluetoothStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			DeviceDiscoveryTask discoveryTask = new DeviceDiscoveryTask(discoveryAgent);
			
			discoveryTask.start();
			

			DiscoveredDevice[] devices = null;
			
			try {
			  devices = discoveryTask.checkedGet(30, TimeUnit.SECONDS);
			 }
			catch (Exception e) {
			  e.printStackTrace();
			 }

			if(devices!=null)
			 for(DiscoveredDevice dev : devices) 
			  System.out.println("noob");
			
			
		}	
	}
}
