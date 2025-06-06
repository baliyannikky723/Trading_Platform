import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

class Stock {
    private String ticker;
    private double currentPrice;

    public Stock(String ticker, double currentPrice) {
        this.ticker = ticker;
        this.currentPrice = currentPrice;
    }

    public String getTicker() {
        return ticker;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }
}

class StockExchange {
    private HashMap<String, Stock> stockList;

    public StockExchange() {
        stockList = new HashMap<>();
        stockList.put("AAPL", new Stock("AAPL", 150.0));
        stockList.put("GOOGL", new Stock("GOOGL", 2800.0));
        stockList.put("AMZN", new Stock("AMZN", 3400.0));
        stockList.put("MSFT", new Stock("MSFT", 290.0));
    }

    public void updateStockPrices() {
        Random random = new Random();
        for (Stock stock : stockList.values()) {
            double priceChange = random.nextDouble() * 10 - 5;
            stock.setCurrentPrice(stock.getCurrentPrice() + priceChange);
        }
    }

    public Stock getStock(String ticker) {
        return stockList.get(ticker);
    }

    public void addNewStock(String ticker, double initialPrice) {
        stockList.put(ticker, new Stock(ticker, initialPrice));
        System.out.println("Added new stock: " + ticker + " at $" + initialPrice);
    }

    public void displayMarketData() {
        System.out.println("Current Stock Market Data:");
        for (Stock stock : stockList.values()) {
            System.out.printf("%s: $%.2f%n", stock.getTicker(), stock.getCurrentPrice());
        }
    }
}

class InvestorPortfolio {
    private HashMap<String, Integer> stockHoldings;
    private double availableCash;

    public InvestorPortfolio(double initialCash) {
        stockHoldings = new HashMap<>();
        availableCash = initialCash;
    }

    public void purchaseStock(String ticker, int quantity, double price) {
        if (quantity <= 0) {
            System.out.println("Quantity must be positive.");
            return;
        }
        if (availableCash < price * quantity) {
            System.out.println("Insufficient cash to purchase " + quantity + " shares of " + ticker);
            return;
        }

        stockHoldings.put(ticker, stockHoldings.getOrDefault(ticker, 0) + quantity);
        availableCash -= price * quantity;
        System.out.println("Purchased " + quantity + " shares of " + ticker);
    }

    public void sellStock(String ticker, int quantity, double price) {
        if (quantity <= 0) {
            System.out.println("Quantity must be positive.");
            return;
        }
        if (stockHoldings.getOrDefault(ticker, 0) < quantity) {
            System.out.println("Not enough shares to sell " + quantity + " shares of " + ticker);
            return;
        }

        stockHoldings.put(ticker, stockHoldings.get(ticker) - quantity);
        availableCash += price * quantity;
        System.out.println("Sold " + quantity + " shares of " + ticker);
    }

    public void displayPortfolio(StockExchange exchange) {
        System.out.println("Investor's Portfolio:");
        double totalValue = availableCash;
        for (String ticker : stockHoldings.keySet()) {
            int quantity = stockHoldings.get(ticker);
            double currentPrice = exchange.getStock(ticker).getCurrentPrice();
            double stockValue = quantity * currentPrice;
            totalValue += stockValue;
            System.out.printf("%s: %d shares @ $%.2f each = $%.2f%n", ticker, quantity, currentPrice, stockValue);
        }
        System.out.printf("Cash: $%.2f%n", availableCash);
        System.out.printf("Total Portfolio Value: $%.2f%n", totalValue);
    }
}

public class trading {
    public static void main(String[] args) {
        StockExchange exchange = new StockExchange();
        InvestorPortfolio portfolio = new InvestorPortfolio(10000.0);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Display Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. Display Portfolio");
            System.out.println("5. Update Market Prices");
            System.out.println("6. Add New Stock");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            
            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        exchange.displayMarketData();
                        break;
                    case 2:
                        System.out.print("Enter stock ticker to buy: ");
                        String buyTicker = scanner.next().toUpperCase();
                        System.out.print("Enter quantity to buy: ");
                        int buyQuantity = scanner.nextInt();
                        Stock stockToBuy = exchange.getStock(buyTicker);
                        if (stockToBuy != null) {
                            portfolio.purchaseStock(buyTicker, buyQuantity, stockToBuy.getCurrentPrice());
                        } else {
                            System.out.println("Stock ticker not found.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter stock ticker to sell: ");
                        String sellTicker = scanner.next().toUpperCase();
                        System.out.print("Enter quantity to sell: ");
                        int sellQuantity = scanner.nextInt();
                        Stock stockToSell = exchange.getStock(sellTicker);
                        if (stockToSell != null) {
                            portfolio.sellStock(sellTicker, sellQuantity, stockToSell.getCurrentPrice());
                        } else {
                            System.out.println("Stock ticker not found.");
                        }
                        break;
                    case 4:
                        portfolio.displayPortfolio(exchange);
                        break;
                    case 5:
                        exchange.updateStockPrices();
                        System.out.println("Market prices updated.");
                        break;
                    case 6:
                        System.out.print("Enter new stock ticker: ");
                        String newTicker = scanner.next().toUpperCase();
                        System.out.print("Enter initial price: ");
                        double initialPrice = scanner.nextDouble();
                        exchange.addNewStock(newTicker, initialPrice);
                        break;
                    case 7:
                        scanner.close();
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }
    }
}
