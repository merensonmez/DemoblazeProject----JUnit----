package demoblaze;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class DemoblazeTest {
    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.demoblaze.com/");

        // Check if it's the right page
        System.out.println("Page title : " +driver.getTitle());
        String actualTitle = driver.getTitle();
        String expectedTitle = "STORE";
        if (actualTitle.contains(expectedTitle)){
            System.out.println("Title test passed!!!");
        }else{
            System.out.println("Title test failed!!!");
        }

        WebElement signup = driver.findElement(By.id("signin2"));
        signup.click();

        WebElement signUsername = driver.findElement(By.id("sign-username"));
        Thread.sleep(1000);
        signUsername.sendKeys("eren44123");
        Thread.sleep(1000);
        driver.findElement(By.id("sign-password")).sendKeys("123456789Ee");
        Thread.sleep(1000);
        WebElement signupButton = driver.findElement(By.xpath("//button[@onclick=\"register()\"]"));
        signupButton.click();
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("div[id='signInModal'] div[class='modal-footer'] button:nth-child(1)")).click();

        WebElement login = driver.findElement(By.id("login2"));
        login.click();
        driver.findElement(By.id("loginusername")).sendKeys("eren44123");
        Thread.sleep(1000);
        driver.findElement(By.id("loginpassword")).sendKeys("123456789Ee");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@onclick=\"logIn()\"]")).click();
        Thread.sleep(2000);

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot,new File("Screenshot//loginnedpage.png"));

        driver.findElement(By.xpath("//a[normalize-space()='Iphone 6 32gb']")).click();
        WebElement verifiedSelectedTruePhone = driver.findElement(By.xpath("//h2[normalize-space()='Iphone 6 32gb']"));
        if (verifiedSelectedTruePhone.isDisplayed()){
            System.out.println("True phone is selected!!!");
        }else {
            System.out.println("True phone is NOT selected!!!!");
        }
        WebElement addToCart = driver.findElement(By.xpath("//a[@onclick=\"addToCart(5)\"]"));
        addToCart.click();
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
        Thread.sleep(1000);
        WebElement cart = driver.findElement(By.id("cartur"));
        cart.click();
        Thread.sleep(1000);
        WebElement totalPrice = driver.findElement(By.id("totalp"));
        String totalPricePage = totalPrice.getText();
        System.out.println("Total price: " + totalPricePage);

        Thread.sleep(1000);
        WebElement placeOrder = driver.findElement(By.xpath("//button[normalize-space()='Place Order']"));
        placeOrder.click();

        driver.findElement(By.id("name")).sendKeys("Eren");
        Thread.sleep(1000);
        driver.findElement(By.id("country")).sendKeys("Turkey");
        Thread.sleep(1000);
        driver.findElement(By.id("city")).sendKeys("Malatya");
        Thread.sleep(1000);
        driver.findElement(By.id("card")).sendKeys("550656179466150");
        Thread.sleep(1000);
        driver.findElement(By.id("month")).sendKeys("06");
        Thread.sleep(1000);
        driver.findElement(By.id("year")).sendKeys("2025");
        Thread.sleep(1000);
        WebElement purchase = driver.findElement(By.xpath("//button[@onclick=\"purchaseOrder()\"]"));
        purchase.click();
        Thread.sleep(1000);
        WebElement purchasedMessage = driver.findElement(By.xpath("//h2[normalize-space()='Thank you for your purchase!']"));
        if (purchasedMessage.isDisplayed()){
            System.out.println("Thank you for your purchase!");
        }else{
            System.out.println("Failed!!!!");
        }
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".confirm.btn.btn-lg.btn-primary")).click();
        Thread.sleep(1000);

        WebElement contact = driver.findElement(By.xpath("//a[normalize-space()='Contact']"));
        contact.click();
        Thread.sleep(1000);
        driver.findElement(By.id("recipient-email")).sendKeys("erensonmeztestselenium@gmail.com");
        Thread.sleep(1000);
        driver.findElement(By.id("recipient-name")).sendKeys("Eren");
        Thread.sleep(1000);
        driver.findElement(By.id("message-text")).sendKeys("I bought an iPhone 6 and now the icloud of the phone is not working. Can you please get back to me?");
        Thread.sleep(1000);

        WebElement sendMessage = driver.findElement(By.xpath("//button[@onclick=\"send()\"]"));
        sendMessage.click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
        driver.close();
    }
}
