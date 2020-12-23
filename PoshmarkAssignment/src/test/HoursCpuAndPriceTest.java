package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.poshmark.assignment.CpuInstanceAllocator;
import com.poshmark.assignment.models.ResultModel;

/**
 * This is the Test Case for get_cost method with 3 parameters Hours, CPU and Price
 * 
 * @author Aacshwin Ravichandran
 * @version 1.0
 * @since 2020-12-23
 */
public class HoursCpuAndPriceTest {

	@Test
	public void test() {
		CpuInstanceAllocator cia = new CpuInstanceAllocator();
		List<ResultModel> rmList = cia.get_cost(8, 100, 60F);

		// Building up result for Test data :
		List<ResultModel> expectedRmList = new ArrayList<>();
		ResultModel rmEast = new ResultModel("us-east");
		ResultModel rmWest = new ResultModel("us-west");
		ResultModel rmAsia = new ResultModel("asia");

		rmAsia.setTotal_cost(59.84F);
		Map<String, Integer> servers = rmAsia.getServers();
		servers.put("xlarge", 2);
		servers.put("8xlarge", 6);
		rmAsia.setServers(servers);
		expectedRmList.add(rmAsia);
		expectedRmList.add(rmWest);
		expectedRmList.add(rmEast);

		assertEquals(expectedRmList.toString(), rmList.toString());
	}

}
