package mario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Controller {
    @FXML
    private Button btnBuscar;

    @FXML
    private TextField textBuscar;
    @FXML
    private TextField textcastella;
    @FXML
    private TextField textsubtitulat;
    @FXML
    private TextField textangles;

    @FXML
    private ImageView imagen;

    @FXML
    private ComboBox combotemporada;

    @FXML
    private ListView listcapitulos;
    private ObservableList<String> items = FXCollections.observableArrayList ();
    private ObservableList<String> options = FXCollections.observableArrayList();
    WebDriver driver;
    Alert alert = new Alert(Alert.AlertType.WARNING);
    ArrayList<String> elements= new ArrayList<String>();
    @FXML
    public void buscarSerie(Event event){
        conectarPagina();
    }

    public void conectarPagina(){
        driver = new FirefoxDriver();
        driver.navigate().to("http://seriesblanco.com");

        WebElement we= driver.findElement(By.id("buscar-blanco"));
        we.sendKeys(textBuscar.getText());
        we.submit();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[@id='Blog1']/div[1]/div[1]/div/div[2]/div[1]/div/a")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.findElements(By.xpath("//div[@id='post-body-8452518480913717526']/h2")).size();
        conteoTemporadas();

       /* for (int i = 0;i<driver.findElements(By.xpath("//div[@id='post-body-8452518480913717526']/h2")).size();i++){
            //System.out.println(driver.findElements(By.xpath("//div[@id='post-body-8452518480913717526']/h2")).get(i));
            //options.add("Temporada "+(i+1));
            combotemporada.getItems().add("Temporada " +(i+1));
        }*/

        //combotemporada = new ComboBox();
        //combotemporada.setItems(options);
        //driver.findElement(By.id("buscar-blamco")).submit();
        //driver.findElement(By.id("loginbtn")).click();
        //driver.findElement(By.linkText("MÒDUL 3: PROGRAMACIÓ 2")).click();
        //System.out.println(driver.findElements(By.partialLinkText("Tasca")).size()+1+"chocolata");

        //$x("//div[contains(@id,'Blog1')]/div[1]/div[1]/div[2]/")
            WebElement img = driver.findElement(By.id("port_serie"));
            String url= img.getAttribute("src");
            System.out.println(url);

    }
    private void conteoTemporadas(){
        for (int i = 0;i<driver.findElements(By.xpath("//div[@id='post-body-8452518480913717526']/h2")).size();i++){
            combotemporada.getItems().add("Temporada " +(i+1));
        }
    }


}