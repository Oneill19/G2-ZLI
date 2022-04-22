package common;

public class ClientInfo {
	
	private String ipAddress;
	private String host;
	private String status;

	public ClientInfo(String ipAddress, String host, String status) {
		this.ipAddress = ipAddress;
		this.host = host;
		this.status = status;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getHostName() {
		return host;
	}

	public void setHostName(String host) {
		this.host = host;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ClientInfo) {
			ClientInfo temp = (ClientInfo) obj;
			if (temp.ipAddress.equals(ipAddress) && temp.host.equals(host) && temp.status.equals(status)) {
				return true;
			}
		}
		return false;
	}

}
