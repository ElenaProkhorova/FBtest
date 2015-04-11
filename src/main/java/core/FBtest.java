package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.Assert;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.Matchers.is;
import java.util.concurrent.TimeUnit;

public class FBtest {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.err.println("No arguments!");
            System.exit(1);
        }
        String userPassword = args[0];
        myLinearTest(userPassword);
    }
    private static void myLinearTest(String userPassword) {

        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String userName = "Elena";
        String friendsNum = "174";

        // welcome page
        driver.get("http://www.facebook.com");
        driver.manage().window().maximize();
        Assert.assertTrue(driver.getTitle().contains("Welcome to Facebook"));
        //wait for copyright loaded completely
        Assert.assertNotNull(driver.findElement(By.cssSelector(".mvl.copyright>div>span")));
        WebElement emailField = driver.findElement(By.id("email"));
        Assert.assertNotNull(emailField);
        WebElement passwordField = driver.findElement(By.id("pass"));
        Assert.assertNotNull(passwordField);

        emailField.sendKeys("elenap1285@gmail.com");
        passwordField.sendKeys(userPassword);
        WebElement loginButton = driver.findElement(By.id("u_0_n"));
        loginButton.click();

        // user home page
        WebElement timelineButton = driver.findElement(By.className("_2dpb"));
        Assert.assertTrue(timelineButton.getText().equals(userName));
        timelineButton.click();

        // user timeline page
        WebElement numberOfFriends = driver.findElement(By.cssSelector("._gs6"));
        Assert.assertThat(numberOfFriends.getText(), is(friendsNum));

        WebElement userNavigationButton = driver.findElement(By.id("userNavigationLabel"));
        userNavigationButton.click();

        WebElement logOutOption = driver.findElement(By.className("uiLinkButtonInput"));
        logOutOption.click();
        Assert.assertNotNull(loginButton);

        driver.close();
    }

}
