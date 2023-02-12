import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InvestmentTest
{
	@Test
	void test()
	{
		Investment baba = new Investment("BABA", 10000, 55448);
		assertEquals("BABA", baba.getSymbol());
		assertEquals(10000, baba.getAmountInvested());
		assertEquals(55448, baba.getFutureValue());
	}
}
