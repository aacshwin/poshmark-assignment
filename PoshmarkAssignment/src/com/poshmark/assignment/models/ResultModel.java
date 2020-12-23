package com.poshmark.assignment.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Result Model is the resultant output allocated server instances model
 * 
 * @author Aacshwin Ravichandran
 * @version 1.0
 * @since 2020-12-21
 */
public class ResultModel {

	private String region;
	private Float total_cost;
	private Map<String, Integer> servers;
	
	public ResultModel(String region) {
		this.region = region;
		this.total_cost = 0F;
		this.servers = new HashMap<>();
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Float getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(Float total_cost) {
		this.total_cost += total_cost;
	}

	public Map<String, Integer> getServers() {
		return servers;
	}

	public void setServers(Map<String, Integer> servers) {
		this.servers = servers;
	}

	@Override
	public String toString() {
		return "ResultModel [region=" + region + ", total_cost=" + total_cost + ", servers=" + servers + "]";
	}

}
