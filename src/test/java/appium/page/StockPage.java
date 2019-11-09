package appium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class StockPage extends BasePage{

    public StockPage deleteAll(){
        click(By.id("edit_group"));
        click(By.id("check_all"));
        click(By.id("cancel_follow"));
        click(By.id("md_buttonDefaultPositive"));
        click(By.id("action_close"));

        return this;
    }

    public List<Stock> getAllStocks() {
        try {
            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("add_to_portfolio_stock")));
            return null;
        } catch (Exception e) {

            List<Stock> stocks = new ArrayList<>();
            List<WebElement> results = driver.findElement(By.id("content_recycler")).findElements(By.id("row_layout"));

            results.forEach(element -> {
                Stock stock = new Stock();
                stock.setStockName(element.findElement(By.id("portfolio_stockName")).getText());
                List<WebElement> pricesAndLimits = element.findElement(By.id("row_recycler")).findElements(By.id("item_layout"));
                stock.setStockPrice(pricesAndLimits.get(0).getText());
                stock.setStockPriceLimit(pricesAndLimits.get(1).getText());
                stocks.add(stock);
            });
            System.out.println(stocks.toString());
            return stocks;
        }
    }

    public StockPage addDefaultStock(){
        click(By.id("add_to_portfolio_stock"));
        return this;
    }


    public class Stock{
        String stockName;
        String stockPrice;
        String stockPriceLimit;

        public String getStockPrice() {
            return stockPrice;
        }

        public void setStockPrice(String stockPrice) {
            this.stockPrice = stockPrice;
        }

        public String getStockName() {
            return stockName;
        }

        public void setStockName(String stockName) {
            this.stockName = stockName;
        }

        public String getStockPriceLimit() {
            return stockPriceLimit;
        }

        public void setStockPriceLimit(String stockPriceLimit) {
            this.stockPriceLimit = stockPriceLimit;
        }

        @Override
        public String toString() {
            return "股票名称：" + stockName +"，股票价格："+ stockPrice +"，股票涨跌" + stockPriceLimit;
        }
    }
}
