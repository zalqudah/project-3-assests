import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StockTest
{
	@Test
	void test()
	{
		Stock aapl = new Stock("AAPL", "Apple", 0.2320660183, 0.009271158436, 0.45152822);
		assertEquals(0.2313, aapl.getExpectedReturn(), 0.001);
		assertEquals("AAPL", aapl.getSymbol());
		assertEquals("Apple", aapl.getName());

		Stock baba = new Stock("BABA", "Ali Baba", Stock.NO_DATA, 0.1144187803, 0.2845702889);
		assertEquals(0.1824, baba.getExpectedReturn(), 0.001);
		
		assertEquals(53448, baba.invest(10000, 10));
	}
}
