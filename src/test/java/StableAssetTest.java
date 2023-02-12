import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StableAssetTest
{
	@Test
	void test()
	{
		StableAsset cash = new StableAsset("CASH", "Cash", 0.0);
		assertEquals(0.0, cash.getExpectedReturn(), 0.001);
		
		StableAsset savings = new StableAsset("SAV", "Marcus Savings", 0.02);
		assertEquals(0.02, savings.getExpectedReturn(), 0.001);
		
		assertEquals(12189, savings.invest(10000, 10));
	}
}
