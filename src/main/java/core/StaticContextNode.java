package core;

public class StaticContextNode {

	private String androidSDK;
	private String manufacturer;
	
	public StaticContextNode(String sdk, String manufacturer){
		this.androidSDK = sdk;
		this.manufacturer = manufacturer;
	}
	
	public String getAndroidSDK() {
		return androidSDK;
	}
	public void setAndroidSDK(String androidSDK) {
		this.androidSDK = androidSDK;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
}
