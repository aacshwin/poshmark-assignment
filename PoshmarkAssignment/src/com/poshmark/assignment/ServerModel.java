package com.poshmark.assignment;

/**
 * Server Model is the input server instance model
 * 
 * @author Aacshwin Ravichandran
 * @version 1.0
 * @since 2020-12-21
 */
public class ServerModel implements Comparable<ServerModel> {

	private String region;
	private String type;
	private Float costPerHour;
	private int noOfCpu;
	private Float costPerHourPerCpu;

	public ServerModel(String region, String type, Float costPerHour) {
		this.region = region;
		this.type = type;
		this.costPerHour = costPerHour;
		calcNoOfCpu();
		calcCostPerCpu();
	}

	/**
	 * This method is used to determine the number of CPUs in each server instance based on its type.
	 * 
	 * @return number of CPUs
	 */
	public int calcNoOfCpu() {
		switch (this.type) {
		case "large":
			noOfCpu = 1;
			break;
		case "xlarge":
			noOfCpu = 2;
			break;
		case "2xlarge":
			noOfCpu = 4;
			break;
		case "4xlarge":
			noOfCpu = 8;
			break;
		case "8xlarge":
			noOfCpu = 16;
			break;
		case "10xlarge":
			noOfCpu = 32;
			break;
		default:
			noOfCpu = -1;
		}
		return noOfCpu;
	}

	public Float calcCostPerCpu() {
		return costPerHourPerCpu = this.costPerHour / noOfCpu;
	}

	public String getRegion() {
		return region;
	}

	public String getType() {
		return type;
	}

	public Float getCostPerHour() {
		return costPerHour;
	}

	public Float getCostPerHourPerCpu() {
		return costPerHourPerCpu;
	}

	public int getNoOfCpu() {
		return noOfCpu;
	}
	
	public void setNoOfCpu(int noOfCpu) {
		this.noOfCpu = noOfCpu;
	}
	
	@Override
	public int compareTo(ServerModel serverModel2) {
		return this.costPerHourPerCpu > serverModel2.getCostPerHourPerCpu() ? 1 : -1;
	}

	@Override
	public String toString() {
		return "ServerModel [region=" + region + ", type=" + type + ", costPerHour=" + costPerHour + ", noOfCpu="
				+ noOfCpu + ", costPerHourPerCpu=" + costPerHourPerCpu + "]";
	}
	
}
