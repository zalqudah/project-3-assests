# Project 4 - Assets
[![Points badge](../../blob/badges/.github/badges/points.svg)](../../actions)

## Project Outcomes:
Develop a Java program that uses:
- Object-Oriented Programming principles of
	- Encapsulation
	- Inheritance, and
	- Polymorphism
- File input
- structured output
- Arrays
- Exception handling

## Preparatory Readings:
ZyBook chapters 1-10.

## Background Information:
### Project overview:


The purpose of this project is to create a tool for analyzing asset data to try to figure out how best to put money to work.
For the sake of simplicity, and also out of necessity since I'm not a finance person, we'll simplify things down to stocks and stable assets.

Stable assets are things that we'll assume have a highly predictable rate of return.
A good example is cash, which has a 0% rate of return, or a high-yield savings account.

Stocks are wildly variable assets that are connected to the successes and failures of a company or companies.
For the sake of this project, we'll assume that only the last five years of data matters, but that some extra consideration should be given to recent periods since a company might be on the way up, or the way out, irrespective of it's long history.

### Project Requirements:
Your application must function as described below:
1. Your program must satisfy all requirements in the test suites contained in [src/test/java](src/test/java).
	- Running all tests can be accomplished in the command line by running `gradle test`.
	- Individual tests can be run by running `gradle test_stock`, `gradle test_stable_asset`, `gradle test_investment`, `gradle test_portfolio`, and `gradle test_ui`, 
1. The class **Main** should run an interactive mode of your program.
	- The autograder will verify that your Main program works with [mainInput.txt](mainInput.txt) as the input to your program.
		- In the command line, this can be accomplished by running `cat mainInput | gradle run`.
1. Read in the contents of the file input by the user (autograding will use [assetData.csv](assetData.csv)) to create an array of potential assets for investing in.
_Note that this should only be read in at the start of the program and the data in the array should be used rather than the file after that._
	1. Your program must gracefully handle _bad_ data.
	For example, an interest rate that is non-numeric should cause that asset to not be added to the list of possible assets.
1. Prompt a user for a dollar amount to invest, as well as an asset from the list.
1. Print out the value for the asset at the end of 10 years, assuming interest compounds annually.
E.g. for an asset with 5% interest on 100 dollars, the result would be 100\*(1.05)<sup>10</sup> = $162.89.
1. Keep prompting the user for dollar amounts and the asset to invest in.
	1. When the user enters an invalid asset, meaning it isn't in the list - **FOO**, for example - prompt them again to enter an asset.
1. When the user enters a dollar value of zero, this is a signal to end the program.
	- Dollar values below zero should be ignored and the user should be asked for the amount to invest again.
	- Non-integer input when asking for an investment amount should be ignored.
1. Before closing the program, write out all of the assets chosen by the user for their investments to a file called portfolio.txt.
	1. The output format should be nicely tabulated data where all columns are well aligned.
	It should include, the following info for each asset selection the user made, and a summary row at the bottom:
	```
	+--------------+-----------------+--------------------+
	| ASSET SYMBOL | AMOUNT INVESTED | VALUE IN TEN YEARS |
	+==============+=================+====================+
	| FOO          | 1000            | 12000              |
	| BAR          | 2000            | 10                 |
	+--------------+-----------------+--------------------+
	| TOTAL        | 3000            | 12010              |
	+--------------+-----------------+--------------------+
	```

#### Weighting Scheme
For stable assets, only a single value will be given, such as 0.001 for a checking account.
For stocks, up to three values are given, 5-year returns, 1-year returns, and 90-day returns.
- Weighting scheme A:
	* Expected return = 0.6 * 5-year + 0.2 * 1-year + 0.2 * 90-day.
- Weighting scheme B:
	* Expected return = 0.6 * 1-year + 0.4 * 90-day.
- Weighting scheme C (stable assets):
	* Expected return = value given, unless it isn't numeric, then the asset shouldn't be available to choose.

#### Input Data

The input data will be formatted as such:
```
STOCK_TICKER,NAME_OF_STOCK,5_YEAR_RETURN,1_YEAR_RETURN,90_DAY_RETURN
STABLE_ASSET_TAG,NAME_OF_ASSET,EXPECTED_RETURN
```

Some stocks (such as BABA, below) are not five years old and as such only have newer periods for the return info.
This is fine, adjust the [weighting scheme](#weighting-scheme) to scheme B.

And here is a sample of actual input data from _**assetData.csv**_
```
UWF,ice-cream machines for all faculty!,foo
MSFT,Microsoft,0.3841045416,0.2157125022,0.01912856921
CD,Certificate of Deposit (CD),0.025
BABA,Alibaba,,0.1144187803,0.2845702889
JPM,JP Morgan,0.1886497065,0.0467322558,0.3413146109
Savings,Marcus Savings account,0.022
```

### UML - Code Structure
I am not requiring that you submit UML for this project.
1. It is probably a good exercise to look at the test suites and create the UML for the required classes.
1. Starting with a good UML diagram can save you a lot of time. The old joke isn't just funny, it's true
> "Days of programming can save you hours of planning"
1. A single array must be used to store all potential assets that a user can select.
Do not use one array for stable assets and another for stocks.
1. An available asset is not the same thing as an investment, for example, a user might have 1000 invested in an asset in a joint account and another 500 invested in a retirement account. Both are valid investments in one's portfolio.
See [the implementation notes](#implementation-notes) below for more ideas on what you might need.

### Sample Run
<pre><code>
THE DATA READ FOR ice-cream machines for all faculty! (UWF) WAS NOT VALID, SO IT WILL NOT BE AN AVAILBLE INVESTMENT!

Available assets for investment
-------------------------------
	Vanguard stock index fund (VTSAX)
	Tesla (TSLA)
	Apple (AAPL)
	Amazon (AMZN)
	Microsoft (MSFT)
	Google (GOOG)
	Certificate of Deposit (CD) (CD)
	Facebook (FB)
	Alibaba (BABA)
	Cash (CASH)
	Visa (V)
	JP Morgan (JPM)
	Walmart (WMT)
	Johnson and Johnson (JNJ)
	Marcus Savings account (Savings)
	Exxon (XOM)
	Proctor and Gamble (PG)
Enter the amount to invest in dollars: <b>1000</b>
Enter the asset symbol to invest in: <b>TSLAAAA</b>
TSLAAAA is not in the input, or had invalid input data. Choose something else to invest in.
<b>TSLA</b>
Investing 1000 in TSLA has an expected future value of: 1147
Enter the amount to invest in dollars: <b>-1</b>
Enter the amount to invest in dollars: <b>5000</b>
Enter the asset symbol to invest in: <b>VTSAX</b>
Investing 5000 in VTSAX has an expected future value of: 10574
Enter the amount to invest in dollars: <b>100</b>
Enter the asset symbol to invest in: <b>CASH</b>
Investing 100 in CASH has an expected future value of: 100
Enter the amount to invest in dollars: <b>1000</b>
Enter the asset symbol to invest in: <b>FOO</b>
FOO is not in the input, or had invalid input data. Choose something else to invest in.
<b>BAR</b>
BAR is not in the input, or had invalid input data. Choose something else to invest in.
<b>BABA</b>
Investing 1000 in BABA has an expected future value of: 5344
Enter the amount to invest in dollars: <b>0</b>
+--------------+-----------------+--------------------+
| ASSET SYMBOL | AMOUNT INVESTED | VALUE IN TEN YEARS |
+==============+=================+====================+
| TSLA         | 1000            | 1147               |
| VTSAX        | 5000            | 10574              |
| CASH         | 100             | 100                |
| BABA         | 1000            | 5344               |
+--------------+-----------------+--------------------+
| TOTAL        | 7100            | 17165              |
+--------------+-----------------+--------------------+
</code></pre>

### Implementation Notes:
1. Create a project that is object oriented, therefore there should be several classes, each with its own purpose.
1. The input file will match the exact format given above.
1. You will need additional classes that are not immediately obvious in order to have a clean solution.
It is important to note that:
	1. You'll not only be outputting data when the user makes an investment but also storing it so that a summary table can be printed on exit.
	1. A user should be able to choose the same asset more than once in a run, so storing the amount invested etc. inside the asset is not a good design.
	1. Additionally, the user can make several (some number less than 10) investments in a program run so storing those investments together for the final output is crucial.

### Submission Requirements:
1. All code must be added and committed to your local git repository.
1. All code must be pushed to the GitHub repository created when you "accepted" the assignment.
	1. After pushing, with `git push origin main`, visit the web URL of your repository to verify that your code is there.
	***If you don't see the code there, then we can't see it either.***
1. Verify your point score on the main page of the repository, or dig in to the build details for specific feedback.

## Important Notes:
- Projects will be graded on whether they correctly solve the problem, and whether they adhere to good programming practices.
- Projects must be received by the time specified on the due date. Projects received after that time will get a grade of zero.
- Please review the academic honesty policy.
	- Note that viewing another student's solution, whether in whole or in part, is considered academic dishonesty.
	- Also note that submitting code obtained through the Internet or other sources, whether in whole or in part, is considered academic dishonesty.
	- All programs submitted will be reviewed for evidence of academic dishonesty, and all violations will be handled accordingly.
