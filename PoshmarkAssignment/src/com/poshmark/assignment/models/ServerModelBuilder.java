package com.poshmark.assignment.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ServerModelBuilder contains the main logic for building the sorted input ServerModel List.
 * 
 * @author Aacshwin Ravichandran
 * @version 1.0
 * @since 2020-12-21
 *
 */
public class ServerModelBuilder {

	public static List<ServerModel> buildServerModel() {
		List<ServerModel> smList = new ArrayList<>();

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

		// Sorting the input server instances based of Cost per Hour per CPU.
		Collections.sort(smList);

		return smList;
	}
}
