# Trading Platform

This is a simple command-line stock trading simulation program. It allows users to simulate buying and selling stocks, viewing the current market data, updating stock prices, and managing an investment portfolio.

## Features

- **Display Market Data**: View the current prices of various stocks in the market.
- **Buy Stock**: Purchase shares of a stock, which reduces your available cash.
- **Sell Stock**: Sell shares of a stock, which increases your available cash.
- **Display Portfolio**: View your current portfolio holdings and cash balance.
- **Update Market Prices**: Simulate the fluctuation of stock prices.
- **Add New Stock**: Add a new stock to the market with an initial price.

## Classes

### `Stock`
Represents a stock with a ticker symbol and a current price.

- **Attributes:**
  - `ticker`: The stock's ticker symbol.
  - `currentPrice`: The current price of the stock.

- **Methods:**
  - `getTicker()`: Returns the ticker symbol of the stock.
  - `getCurrentPrice()`: Returns the current price of the stock.
  - `setCurrentPrice(double price)`: Updates the current price of the stock.

### `StockExchange`
Represents a collection of stocks available in the market.

- **Attributes:**
  - `stockList`: A `HashMap` of stocks keyed by their ticker symbols.

- **Methods:**
  - `updateStockPrices()`: Randomly updates the prices of all stocks in the market.
  - `getStock(String ticker)`: Retrieves a stock by its ticker symbol.
  - `addNewStock(String ticker, double initialPrice)`: Adds a new stock to the market with an initial price.
  - `displayMarketData()`: Displays the current prices of all stocks in the market.

### `InvestorPortfolio`
Represents a user's investment portfolio, including cash and stock holdings.

- **Attributes:**
  - `stockHoldings`: A `HashMap` of stock ticker symbols and the corresponding quantities held.
  - `availableCash`: The amount of cash available in the portfolio.

- **Methods:**
  - `purchaseStock(String ticker, int quantity, double price)`: Purchases a specified quantity of a stock, reducing the available cash.
  - `sellStock(String ticker, int quantity, double price)`: Sells a specified quantity of a stock, increasing the available cash.
  - `displayPortfolio(StockExchange exchange)`: Displays the current portfolio, including cash balance and the value of all holdings.

### `TradingPlatform`
The main class that drives the application.

- **Methods:**
  - `main(String[] args)`: The entry point of the application. Provides a menu-driven interface for interacting with the stock market and portfolio.

EXAMPLE USAGE
Display Market Data
Buy Stock
Sell Stock
Display Portfolio
Update Market Prices
Add New Stock
Exit Choose an option: 1

Current Stock Market Data: 
AAPL: $149.45 
GOOGL: $2795.23 
AMZN: $3399.89 
MSFT: $291.12
