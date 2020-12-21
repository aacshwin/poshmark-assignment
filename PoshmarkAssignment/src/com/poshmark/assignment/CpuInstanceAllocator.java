package com.poshmark.assignment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CpuInstanceAllocator {

	private static List<ServerModel> smList = new ArrayList<>();

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

		Collections.sort(smList);

		List<ResultModel> rmList = get_cost(24, 500);
		Iterator<ResultModel> iter = rmList.iterator();

		while (iter.hasNext()) {
			System.out.println(iter.next());
		}

	}

	public static List<ResultModel> get_cost(int targetHours, int targetCpuCount) {
		List<ResultModel> rmList = new ArrayList<>();
		ResultModel rmEast = new ResultModel("us-east");
		ResultModel rmWest = new ResultModel("us-west");
		ResultModel rmAsia = new ResultModel("asia");
		Iterator<ServerModel> iter = smList.iterator();

		while (iter.hasNext() && targetCpuCount > 0) {
			ServerModel serverModel = iter.next();
			int serversAllotted = targetCpuCount / serverModel.getNoOfCpu();
			if(serversAllotted == 0) {
				continue;
			}
			switch (serverModel.getRegion()) {
			case "us-east":
				setResult(targetHours, serversAllotted, serverModel, rmEast);
				break;
			case "us-west":
				setResult(targetHours, serversAllotted, serverModel, rmWest);
				break;
			case "asia":
				setResult(targetHours, serversAllotted, serverModel, rmAsia);
				break;
			}

			targetCpuCount = targetCpuCount % serverModel.getNoOfCpu();
		}
		rmList.add(rmAsia);
		rmList.add(rmWest);
		rmList.add(rmEast);
		return rmList;
	}

	public static void setResult(int targetHours, int serversAllotted, ServerModel sm, ResultModel rm) {
		rm.setTotal_cost(sm.getCostPerHour() * targetHours * serversAllotted);

		Map<String, Integer> servers = rm.getServers();
		if (servers.containsKey(sm.getType())) {
			int count = servers.get(sm.getType());
			count += serversAllotted;
			servers.put(sm.getType(), count);
		} else {
			servers.put(sm.getType(), serversAllotted);
		}
		rm.setServers(servers);
	}

}
