package com.poshmark.assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * CpuInstanceAllocator contains the main logic for reading input as Server Model
 * and calculating number of servers to be allocated. 
 * 
 * @author Aacshwin Ravichandran
 * @version 1.0
 * @since 2020-12-21
 *
 */
public class CpuInstanceAllocator {

	private static List<ServerModel> smList = new ArrayList<>();

	/**
	 * The main method CpuInstanceAllocator.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		ServerModel sm = new ServerModel("us-east", "large", 0.12F);
		ServerModel sm1 = new ServerModel("us-east", "xlarge", 0.23F);
		ServerModel sm2 = new ServerModel("us-east", "2xlarge", 0.45F);
		ServerModel sm3 = new ServerModel("us-east", "4xlarge", 0.774F);
		ServerModel sm4 = new ServerModel("us-east", "8xlarge", 1.4F);
		ServerModel sm5 = new ServerModel("us-east", "10xlarge", 2.82F);
		ServerModel sm6 = new ServerModel("us-west", "large", 0.14F);
		ServerModel sm7 = new ServerModel("us-west", "2xlarge", 0.413F);
		ServerModel sm8 = new ServerModel("us-west", "4xlarge", 0.89F);
		ServerModel sm9 = new ServerModel("us-west", "8xlarge", 1.3F);
		ServerModel sm10 = new ServerModel("us-west", "10xlarge", 2.97F);
		ServerModel sm11 = new ServerModel("asia", "large", 0.11F);
		ServerModel sm12 = new ServerModel("asia", "xlarge", 0.20F);
		ServerModel sm13 = new ServerModel("asia", "4xlarge", 0.67F);
		ServerModel sm14 = new ServerModel("asia", "8xlarge", 1.18F);

		smList.add(sm);
		smList.add(sm1);
		smList.add(sm2);
		smList.add(sm3);
		smList.add(sm4);
		smList.add(sm5);
		smList.add(sm6);
		smList.add(sm7);
		smList.add(sm8);
		smList.add(sm9);
		smList.add(sm10);
		smList.add(sm11);
		smList.add(sm12);
		smList.add(sm13);
		smList.add(sm14);

		// Sorting the input server instances based of cost per hour per CPU.
		Collections.sort(smList);
		
		List<ResultModel> rmList = get_cost(8, 100F);
		Iterator<ResultModel> iter = rmList.iterator();

		while (iter.hasNext()) {
			System.out.println(iter.next());
		}

	}

	/**
	 * This is a overloaded method for the get_cost method with hours and CPUs as
	 * input.
	 * 
	 * @param hours This is the number of hours the user needs.
	 * @param cpus  This is the minimum number of CPUs the user needs.
	 * @return This method in turn calls the main get_cost method with 3 parameters.
	 */
	public static List<ResultModel> get_cost(int hours, int cpus) {
		return get_cost(hours, cpus, 0F);
	}

	/**
	 * This is a overloaded method for the get_cost method with hours and price as
	 * input.
	 * 
	 * @param hours This is the number of hours the user needs.
	 * @param price This is the maximum amount the user is willing to pay.
	 * @return This method in turn calls the main get_cost method with 3 parameters.
	 */
	public static List<ResultModel> get_cost(int hours, Float price) {
		return get_cost(hours, -1, price);
	}

	/**
	 * This is the main get_cost method which accepts all the 3 user constraints.
	 * 
	 * @param hours This is the number of hours the user needs.
	 * @param cpus  This is the minimum number of CPUs the user needs.
	 * @param price This is the maximum amount the user is willing to pay.
	 * @return This method returns the result model with total price and servers
	 *         allocated across the regions.
	 */
	public static List<ResultModel> get_cost(int hours, int cpus, Float price) {
		int serversAllotted = 0;
		List<ResultModel> rmList = new ArrayList<>();
		ResultModel rmEast = new ResultModel("us-east");
		ResultModel rmWest = new ResultModel("us-west");
		ResultModel rmAsia = new ResultModel("asia");
		Iterator<ServerModel> iter = smList.iterator();

		while (iter.hasNext()) {
			ServerModel serverModel = iter.next();

			// If CPUs is set as 0, the target number of CPUs is achieved, thus the break out of the loop.
			if (cpus == 0) {
				break;
			} else if (cpus == -1) {
				// If CPUs is set as -1, the user had only specified hours and price as target.
				// Thus servers allocations logic is optimized based on price
				serversAllotted = (int) (price / (serverModel.getCostPerHour() * hours));
			} else {
				// If user had specified cpus target, servers allocations logic is optimized based on CPUs.
				serversAllotted = cpus / serverModel.getNoOfCpu();
			}

			// If server allotted is 0 for current iteration, its okay to skip to next iteration.
			if (serversAllotted == 0) {
				continue;
			}
			switch (serverModel.getRegion()) {
			case "us-east":
				setResult(hours, serversAllotted, serverModel, rmEast);
				break;
			case "us-west":
				setResult(hours, serversAllotted, serverModel, rmWest);
				break;
			case "asia":
				setResult(hours, serversAllotted, serverModel, rmAsia);
				break;
			}

			if (cpus == -1) {
				// Calculation for remaining target price for next iteration.
				price = price % (serverModel.getCostPerHour() * hours);
			} else {
				// Calculation for remaining target CPUs for next iteration.
				cpus = cpus % serverModel.getNoOfCpu();
			}

		}
		rmList.add(rmAsia);
		rmList.add(rmWest);
		rmList.add(rmEast);
		return rmList;
	}

	/**
	 * This method contains the internal calculation logic for building the result model.
	 * 
	 * @param targetHours This is the number of hours the user needs.
	 * @param serversAllotted This the number of instances of server allotted in the result model.
	 * @param serverModel This is the model object for Input server instances.
	 * @param resultModel This is the result model object.
	 */
	public static void setResult(int targetHours, int serversAllotted, ServerModel serverModel,
			ResultModel resultModel) {
		resultModel.setTotal_cost(serverModel.getCostPerHour() * targetHours * serversAllotted);

		Map<String, Integer> servers = resultModel.getServers();
		if (servers.containsKey(serverModel.getType())) {
			int count = servers.get(serverModel.getType());
			count += serversAllotted;
			servers.put(serverModel.getType(), count);
		} else {
			servers.put(serverModel.getType(), serversAllotted);
		}
		resultModel.setServers(servers);
	}

}
