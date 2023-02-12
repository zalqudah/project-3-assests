import java.util.Scanner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class UITest
{
	private String AVAILABLE_ASSETS = "Available assets for investment\n" +
"-------------------------------\n" +
"\tVanguard stock index fund (VTSAX)\n" +
"\tTesla (TSLA)\n" +
"\tApple (AAPL)\n" +
"\tAmazon (AMZN)\n" +
"\tMicrosoft (MSFT)\n" +
"\tGoogle (GOOG)\n" +
"\tCertificate of Deposit (CD) (CD)\n" +
"\tFacebook (FB)\n" +
"\tAlibaba (BABA)\n" +
"\tCash (CASH)\n" +
"\tVisa (V)\n" +
"\tJP Morgan (JPM)\n" +
"\tWalmart (WMT)\n" +
"\tJohnson and Johnson (JNJ)\n" +
"\tMarcus Savings account (Savings)\n" +
"\tExxon (XOM)\n" +
"\tProctor and Gamble (PG)";

	private Scanner mockInput;

	@BeforeEach
	void init ()
	{
		this.mockInput = new Scanner("V -1 invalidAmountToInvest 12000 0");
	}

	@Test
	void testConstructor()
	{
		UI ui = new UI(mockInput);
	}

	@Test
	void testLoad ()
	{
		UI ui = new UI(mockInput);
		assertFalse(ui.loadFile("zzzz"));
		assertTrue(ui.loadFile("assetData.csv"));
	}

	@Test
	void testGetPrintableList()
	{
		UI ui = new UI(mockInput);
		ui.loadFile("assetData.csv");

		String printableList = ui.getAvailableAssetList();
		assertEquals(AVAILABLE_ASSETS, printableList);
	}

	@Test
	void testGetAsset ()
	{
		UI ui = new UI(mockInput);
		ui.loadFile("assetData.csv");

		assertNull(ui.getAsset("zzzzzzz"));
		assertEquals("Tesla", ui.getAsset("TSLA").getName());
	}

	@Test
	void testGetAssetFromUser ()
	{
		UI ui = new UI(mockInput);
		ui.loadFile("assetData.csv");

		// "V" comes from  mockInput string in BeforeEach init method
		assertEquals("Visa", ui.getAssetSelectionFromUser().getName());
	}

	@Test
	void testAmountFromUser ()
	{
		UI ui = new UI(mockInput);
		ui.loadFile("assetData.csv");

		// getAmountToInvestFromUser should keep grabbing input until a valid integer greater than 0 is found
		assertEquals(12000, ui.getAmountToInvestFromUser());

		// Will use 0 as the end of input signal, so 0 should be valid
		assertEquals(0, ui.getAmountToInvestFromUser());
	}
}
