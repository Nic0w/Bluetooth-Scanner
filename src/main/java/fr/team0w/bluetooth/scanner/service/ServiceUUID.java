package fr.team0w.bluetooth.scanner.service;

public enum ServiceUUID {
	
	/*Source : http://www.bluetooth.org/Technical/AssignedNumbers/service_discovery.htm */
	
	SDP							(0x0001,	"Bluetooth Core Specification"),
	UDP							(0x0002,	"[NO USE BY PROFILES]"),
	RFCOMM						(0x0003,	"RFCOMM with TS 07.10"),
	TCP							(0x0004,	"TCP [NO USE BY PROFILES]"),
	TCS_BIN						(0x0005,	"Telephony Control Specification / TCS Binary [DEPRECATED]"),
	TCS_AT						(0x0006,	"TCS_AT [NO USE BY PROFILES]"),
	ATT							(0x0007,	"Attribute Protocol"),
	OBEX						(0x0008,	"IrDA Interoperability"),
	IP							(0x0009,	"IP [NO USE BY PROFILES]"),
	FTP							(0x000A,	"FTP [NO USE BY PROFILES]"),
	HTTP						(0x000C,	"HTTP [NO USE BY PROFILES]"),
	WSP							(0x000E,	"WSP [NO USE BY PROFILES]"),
	BNEP						(0x000F,	"Bluetooth Network Encapsulation Protocol (BNEP)"),
	UPNP						(0x0010,	"Extended Service Discovery Profile (ESDP) [DEPRECATED]"),
	HIDP						(0x0011,	"Human Interface Device Profile (HID)"),
	HARDCOPY_CONTROL_CHANNEL	(0x0012,	"Hardcopy Cable Replacement Profile (HCRP)"),
	HARDCOPY_DATA_CHANNEL		(0x0014,	"See Hardcopy Cable Replacement Profile (HCRP)"),
	HARDCOPY_NOTIFICATION		(0x0016,	"Hardcopy Cable Replacement Profile (HCRP)"),
	AVCTP						(0x0017,	"Audio/Video Control Transport Protocol (AVCTP)"),
	AVDTP						(0x0019,	"Audio/Video Distribution Transport Protocol (AVDTP)"),
	CMTP						(0x001B,	"Common ISDN Access Profile (CIP) [DEPRECATED]"),
	MCAP_CONTROL_CHANNEL		(0x001E,	"Multi-Channel Adaptation Protocol (MCAP)"),
	MCAP_DATA_CHANNEL			(0x001F,	"Multi-Channel Adaptation Protocol (MCAP)"),
	L2CAP						(0x0100,	"Bluetooth Core Specification");
	
	
	private final String description;
	private final int uuid16Value;
	
	private ServiceUUID(int uuid16, String description) {
		this.uuid16Value = uuid16;
		this.description = description;
	}
}
