import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class deneme_idea{
    public static void main(String[] args) throws InterruptedException{
        // WebDriver'ı kullanarak tarayıcıyı başlat
        WebDriver driver = new ChromeDriver();

        // Websiteye git
        driver.get("https://testcase.myideasoft.com/");

        // Arama kutusunu bul ve "ürün" yazarak arama yap
        WebElement searchBox = driver.findElement(By.xpath("//input[@name='q']"));
        searchBox.sendKeys("ürün"+ Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);


        // Arama sonuçlarından ilk ürüne tıkla
        WebElement firstProduct = driver.findElement(By.xpath("//img[@alt='Ürün']"));
        firstProduct.click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);


        // Üründen 5 adet sepete ekle
        WebElement quantityInput = driver.findElement(By.xpath("//select[@id='qty-input']"));
        quantityInput.sendKeys("5");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);


        WebElement addToCartButton = driver.findElement(By.xpath("/html/body/main/div/div/section/div/div[1]/div/div[2]/div/div[4]/div[2]/div/div/a"));
        addToCartButton.click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);


        // "SEPETİNİZE EKLENMİŞTİR" yazısını kontrol et


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));// Bekleme süresini 10 saniye olarak ayarla
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'SEPETİNİZE EKLENMİŞTİR')]")));
        // Yazının göründüğünün kontrolü
        if (element.isDisplayed()) {
            System.out.println("Sepetinize eklenmiştir yazısı göründü.");
        } else {
            System.out.println("Sepetinize eklenmiştir yazısı görünmedi.");
        }

        // Sepete git
        driver.get("https://testcase.myideasoft.com/sepet");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);


        // Sepet içeriğini al
        WebElement productQuantityElement = driver.findElement(By.xpath("/html/body/main/div/div/section/div/div/div[1]/div[1]/div[2]/div[1]/div/div/div[2]/div/div/div[2]/div/div/div/input"));
        String productQuantityText = productQuantityElement.getAttribute("value");
        int productQuantity = Integer.parseInt(productQuantityText);

        // Beklenen miktarı belirleme
        int expectedQuantity = 5; // Beklenen miktarı burada ayarlayın

        // Ürün miktarını kontrol edin
        if (productQuantity == expectedQuantity) {
            System.out.println("Ürün miktarı doğru: " + productQuantity);
        } else {
            System.out.println("Ürün miktarı beklenenden farklı: " + productQuantity);

        }
        // Tarayıcıyı kapat
        driver.quit();

    }
}

