package 6wh;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class calcTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  
  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver","C:\\Users\\nicho\\AppData\\Local\\Temp\\Rar$EXa6916.32448\\chromedriver.exe");
    driver = new ChromeDriver();
    
    baseUrl = "http://webstrar99.fulton.asu.edu/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  //ADD TEST
  @Test
  public void testAdd() throws Exception {
    driver.get(baseUrl + "/page2/Default.aspx");
    driver.findElement(By.name("num1")).clear();
    driver.findElement(By.name("num1")).sendKeys("1");
    driver.findElement(By.name("num2")).clear();
    driver.findElement(By.name("num2")).sendKeys("4");
    WebElement ope = driver.findElement(By.id("add"));
    
    ope.click();
    
    driver.findElement(By.name("calc")).click();
    
    //driver.findElement(By.name("submit")).click();
    assertEquals("5", driver.findElement(By.name("res")).getAttribute("value"));
  }
  
  //SUB TEST
  @Test
  public void testSub() throws Exception {
    driver.get(baseUrl + "/page2/Default.aspx");
    driver.findElement(By.name("num1")).clear();
    driver.findElement(By.name("num1")).sendKeys("1");
    driver.findElement(By.name("num2")).clear();
    driver.findElement(By.name("num2")).sendKeys("5");
    WebElement ope = driver.findElement(By.id("sub"));
    
    ope.click();
    
    driver.findElement(By.name("calc")).click();
    
    //driver.findElement(By.name("submit")).click();
    assertEquals("-4", driver.findElement(By.name("res")).getAttribute("value"));
  }
  
  //MUL TEST
  @Test
  public void testMul() throws Exception {
    driver.get(baseUrl + "/page2/Default.aspx");
    driver.findElement(By.name("num1")).clear();
    driver.findElement(By.name("num1")).sendKeys("6");
    driver.findElement(By.name("num2")).clear();
    driver.findElement(By.name("num2")).sendKeys("7");
    WebElement ope = driver.findElement(By.id("mul"));
    
    ope.click();
    
    driver.findElement(By.name("calc")).click();
    
    //driver.findElement(By.name("submit")).click();
    assertEquals("42", driver.findElement(By.name("res")).getAttribute("value"));
  }
  
  //DIV TEST
  @Test
  public void testDiv() throws Exception {
    driver.get(baseUrl + "/page2/Default.aspx");
    driver.findElement(By.name("num1")).clear();
    driver.findElement(By.name("num1")).sendKeys("100");
    driver.findElement(By.name("num2")).clear();
    driver.findElement(By.name("num2")).sendKeys("10");
    WebElement ope = driver.findElement(By.id("div"));
    
    ope.click();
    
    driver.findElement(By.name("calc")).click();
    
    //driver.findElement(By.name("submit")).click();
    assertEquals("10", driver.findElement(By.name("res")).getAttribute("value"));
  }
}
