import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PortfolioTest
{
	@Test
	void test()
	{
		Asset[] portfolio;
		portfolio = new Asset[3];
		
		portfolio[0] = new Stock("AAPL", "Apple", 0.2320660183, 0.009271158436, 0.45152822);
		portfolio[1] = new StableAsset("SAV", "Marcus Savings", 0.02);
		portfolio[2] = new Stock("BABA", "Ali Baba", Stock.NO_DATA, 0.1144187803, 0.2845702889);
		
		Portfolio assets = new Portfolio();
		for (int i = 0; i < 3; ++i)
		{
			assets.addInvestment(portfolio[i].getSymbol(), i*1000, portfolio[i].invest(i*1000, 10));
		}
		
		assertEquals("+--------------+-----------------+--------------------+\n" + 
				"| ASSET SYMBOL | AMOUNT INVESTED | VALUE IN TEN YEARS |\n" + 
				"+==============+=================+====================+\n" + 
				"| AAPL         | 0               | 0                  |\n" + 
				"| SAV          | 1000            | 1218               |\n" + 
				"| BABA         | 2000            | 10689              |\n" + 
				"+--------------+-----------------+--------------------+\n" + 
				"| TOTAL        | 3000            | 11907              |\n" + 
				"+--------------+-----------------+--------------------+\n", assets.toString());
	}
}
