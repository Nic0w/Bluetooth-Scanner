/**
 * 
 */
package fr.team0w.bluetooth.scanner;

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

import fr.team0w.bluetooth.scanner.discovery.DeviceDiscoveryTask;

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
			
			discoveryTask.startDiscovery(new DiscoveryListener()
			 {
			  
			  public void servicesDiscovered(int transID, ServiceRecord[] servRecord)
			   {
				// TODO Auto-generated method stub
				
			   }
			  
			  public void serviceSearchCompleted(int transID, int respCode)
			   {
				// TODO Auto-generated method stub
				
			   }
			  
			  public void inquiryCompleted(int discType)
			   {
				// TODO Auto-generated method stub
				
			   }
			  
			  public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod)
			   {
				System.out.println(btDevice);
				
			   }
			 });
		}
		
		
	}

}
