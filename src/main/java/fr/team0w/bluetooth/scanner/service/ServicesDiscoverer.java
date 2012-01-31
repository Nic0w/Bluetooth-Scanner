/**
 * 
 */
package fr.team0w.bluetooth.scanner.service;

import javax.bluetooth.DiscoveryAgent;

/**
 * @author Nic0w
 *
 */
public class ServicesDiscoverer {
	
	private final DiscoveryAgent discoveryAgent;
	
	
	public ServicesDiscoverer(DiscoveryAgent agent) {
		
		this.discoveryAgent = agent;
		
	}

}
