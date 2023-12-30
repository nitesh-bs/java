package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class GoogleKeywords {
    public static void main(String[] args){
                    List<String> searchTerms = Arrays.asList(
                    "allintitle:healthcare management software",
                    "allintitle:hospital mangement software",
                "allintitle:hospital database management system",
                "allintitle:hospital management system software",
                "allintitle:hospital patient management system",
                "allintitle:hospital management system",
                "allintitle:hospital manegement system",
                "allintitle:hospital management software",
                "allintitle:hospital information technology",
                "allintitle:project on hospital management system",
                "allintitle:hospital software",
                "allintitle:hms system",
                "allintitle:information systems healthcare",
                "allintitle:healthcare information systems definition",
                "allintitle:hospital management information system",
                "allintitle:hospital it systems",
                "allintitle:health information system",
                "allintitle:his healthcare",
                "allintitle:hospital management system project",
                "allintitle:hospital information management system",
                "allintitle:medical information system",
                "allintitle:examples of health information systems",
                "allintitle:hospital management systems",
                "allintitle:hospital information system software",
                "allintitle:hospital information systems",
                "allintitle:management information systems in healthcare",
                "allintitle:what is health information system",
                "allintitle:hospital computer systems",
                "allintitle:hospitals management system",
                "allintitle:use case diagram for hospital management system",
                "allintitle:his software",
                "allintitle:his system",
                "allintitle:it systems in healthcare",
                "allintitle:clinical information systems",
                "allintitle:hospital information system",
                "allintitle:hospital computers",
                "allintitle:examples of healthcare information systems",
                "allintitle:clinic information system",
                "allintitle:types of information systems in healthcare",
                "allintitle:patient information system",
                "allintitle:hospital computer system",
                "allintitle:hospital information",
                "allintitle:clinical information systems in healthcare",
                "allintitle:clinical information system examples",
                "allintitle:his health information system",
                "allintitle:examples of information systems in healthcare",
                "allintitle:clinical information systems examples",
                "allintitle:computer in hospital",
                "allintitle:hospital information system examples",
                "allintitle:what is a clinical information system",
                "allintitle:what are clinical information systems",
                "allintitle:what is clinical information systems",
                "allintitle:his is",
                "allintitle:his hospital",
                "allintitle:hospital information system definition",
                "allintitle:what does his stand for",
                "allintitle:clinical information systems definition"
            );
        // Set Chrome driver path
        System.setProperty("webdriver.chrome.driver","/home/peter/Downloads/chromedriver_linux64/chromedriver");

        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--lang=en-US"); // Set language to English
//        options.addArguments("--region=us"); // Set region to United States
        options.addArguments("--user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36"); // Set user agent
//        options.addArguments("--disable-extensions"); // Disable extensions
//        options.addArguments("--disable-gpu"); // Disable GPU acceleration
//        options.addArguments("--disable-dev-shm-usage"); // Disable /dev/shm usage
//        options.addArguments("--disable-browser-side-navigation"); // Disable browser-side navigation
        options.addArguments("--incognito");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .withLogOutput(System.out)
                .build();
        // Create a new ChromeDriver instance with the specified options
        WebDriver driver = new ChromeDriver(service,options);

        for (String searchTerm : searchTerms) {
            driver.get("https://www.google.com/");  

            System.out.println("Searching for: " + searchTerm);
            WebElement searchInput = driver.findElement(By.name("q"));
            searchInput.clear();
            searchInput.sendKeys(searchTerm);
            searchInput.submit();
// Wait for the search results to load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("result-stats")));

            WebElement resultStatsElement = driver.findElement(By.id("result-stats"));
            String resultStats = resultStatsElement.getText();

            System.out.println("Result Stats:");
            System.out.println(resultStats);

            System.out.println();
        }

            driver.quit();

    }
}
