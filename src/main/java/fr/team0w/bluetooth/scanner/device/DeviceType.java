/**
 * 
 */
package fr.team0w.bluetooth.scanner.device;

import javax.bluetooth.DeviceClass;

/**
 * @author nic0w
 * 
 */
public enum DeviceType {
	MOBILE_PHONE, COMPUTER, LAPTOP, HEADPHONE, UNKNOWN;

	public static DeviceType getTypeFromClass(DeviceClass devClass) {

		return MOBILE_PHONE;
	}
}
