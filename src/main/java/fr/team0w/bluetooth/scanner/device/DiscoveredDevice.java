/**
 * 
 */
package fr.team0w.bluetooth.scanner.device;

import java.io.IOException;

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

	public String toString() {
		StringBuffer output = new StringBuffer();

		try {
			output.append("\"" + this.remoteBTDevice.getFriendlyName(true)
					+ "\" ");

			output.append("(" + this.remoteBTDevice.getBluetoothAddress() + ")");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return output.toString();
	}

}
