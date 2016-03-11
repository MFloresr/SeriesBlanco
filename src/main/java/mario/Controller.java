package mario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.Pane;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import java.util.ArrayList;
import java.util.List;
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
    private ArrayList<Temporada> temporadas = new ArrayList<Temporada>();
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
        conteocapitulos();
        ponerImagen();
    }
    private void ponerImagen(){
        WebElement img = driver.findElement(By.id("port_serie"));
        String url= img.getAttribute("src");
        Image image= new Image(url);
        imagen.setImage(image);
    }
    private void conteoTemporadas(){
        for (int i = 0;i<driver.findElements(By.xpath("//div[@id='post-body-8452518480913717526']/h2")).size();i++){
            combotemporada.getItems().add("Temporada " +(i+1));
        }
    }

    private void conteocapitulos(){
        List<WebElement> elementos = driver.findElements(By.className("zebra"));

        for (int i = 0;i<elementos.size();i++){

            Temporada temporada = new Temporada();
            List<WebElement> tr= elementos.get(i).findElements(By.tagName("a"));
            ArrayList<String> capitulos= new ArrayList<String>();
            for(int x =0;x<tr.size();x++){

                capitulos.add((i+1)+"x"+(x+1)+"- Capitulo " + (x+1));

            }
            temporada.setCapitulos(capitulos);
            List<WebElement> img= elementos.get(i).findElements(By.tagName("img"));
            ArrayList<String> idiomas= new ArrayList<String>();
            for(int x =0;x<img.size();x++){

                idiomas.add(img.get(x).getAttribute("src"));

            }
            temporada.setIdiomas(idiomas);
            temporadas.add(temporada);
        }

        for(int i =0;i<temporadas.size();i++){
            System.out.println(temporadas.get(i));
        }
    }

    @FXML
    public void seleccionarTemprada(Event event){
        listcapitulos.setItems(items);
        if(combotemporada != null && !combotemporada.getItems().isEmpty()){
            if(!listcapitulos.getItems().isEmpty()){
                listcapitulos.getItems().clear();
            }
            rellenarlista();
            llenarIdiomas();
        }
    }
    private void rellenarlista(){
        Temporada tempo = temporadas.get(combotemporada.getSelectionModel().getSelectedIndex());
        ArrayList<String> capitulos = tempo.getCapitulos();
        for(int i=0;i<capitulos.size();i++){
            items.add(capitulos.get(i));
        }
    }

    private void llenarIdiomas(){
        Temporada tempo = temporadas.get(combotemporada.getSelectionModel().getSelectedIndex());
        ArrayList<String> idiomas= tempo.getIdiomas();
        int espa = 0;
        int ing = 0;
        int sub = 0;
        for(int i=0;i<idiomas.size();i++){
            System.out.println(idiomas.get(i));
            if(idiomas.get(i).contains("/es.*")){
                espa=espa+1;
            }
            /*if(idiomas.get(i).contains("/es.*")){
                espa=espa+1;
            }*/
            //items.add(capitulos.get(i));
        }
        textcastella.setText(String.valueOf(espa));
    }
}