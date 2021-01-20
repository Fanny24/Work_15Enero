import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Copia_Eje_2 {

    public static void main(String[] args)
    //public static final SortOrder ASCENDING
    {
        String ContenedorLoguin ="/html/body/div[2]/div[1]";
        String Usuario= "//*[@id=\"user-name\"]";
        String Pass="//*[@id=\"password\"]";

        String Name="//*[@id=\"first-name\"]";
        String Lastname="//*[@id=\"last-name\"]";
        String Postal="//*[@id=\"postal-code\"]";

        String EtiquetaProducts = "//*[@id=\"inventory_filter_container\"]/div"; // verificando etiqueta products
        String VerifContenedor= "//*[@id=\"inventory_container\"]/div"; // verificando contenedor products

        String VerifEtiqCarrito="//*[@id=\"contents_wrapper\"]/div[2]";
        String EtiqCheckout="//*[@id=\"contents_wrapper\"]/div[2]";
        String ContCheckout="//*[@id=\"checkout_info_container\"]/div/form"; //Contenedor checkout
        String BtnCheckout="//*[@id=\"cart_contents_container\"]/div/div[2]/a[2]";

        String BtnContinue="//*[@id=\"checkout_info_container\"]/div/form/div[2]/input";
        String SubTot01="//*[@id=\"checkout_summary_container\"]/div/div[1]/div[3]/div[2]/div[2]";
        String SubTot02="//*[@id=\"checkout_summary_container\"]/div/div[1]/div[4]/div[2]/div[2]";
        String SubTot03="//*[@id=\"checkout_summary_container\"]/div/div[1]/div[5]/div[2]/div[2]";

        String ItemTotal="//*[@id=\"checkout_summary_container\"]/div/div[2]/div[5]";
        String BtnFinish="//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]/a[2]";
        String EtiqFinish="//*[@id=\"contents_wrapper\"]/div[2]";
        String MsjConfirm="//*[@id=\"checkout_complete_container\"]/div";
        String TitleFin="//*[@id=\"checkout_complete_container\"]/h2";

        WebElement inputUser = null;
        WebElement inputPass=null;

        WebElement inputName=null;
        WebElement inputLastname=null;
        WebElement inputPostal=null;

        WebDriver driver = null;
        WebDriverManager.chromedriver().version("87.0.4280.88").setup();
        driver = new ChromeDriver();

        //Abrimos el browser
        driver.get("https://www.saucedemo.com/");

        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContenedorLoguin))); //verificamos se visualice contenedor de loguin

        inputUser = driver.findElement(By.xpath(Usuario));
        inputPass = driver.findElement(By.xpath(Pass));

        inputUser.sendKeys("standard_user");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        inputPass.sendKeys("secret_sauce");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        inputPass.sendKeys(Keys.ENTER); //al terminar de ingresar pass "se presiona enter"

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EtiquetaProducts))); // se verifica se visualice etiq productos
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VerifContenedor))); // se verifica se visualice contenedor prod
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Se obtienen los nombres de los productos

        System.out.println("Iniciando test . . .");

        //driver.findElement(By.xpath("//*[@id=\"inventory_filter_container\"]/select")).click();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.findElement(By.xpath("//*[@id=\"inventory_filter_container\"]/select/option[2]")).click();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String[] prod = new String[6];
        prod[0] = driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).getText() ;
        prod[1] = driver.findElement(By.xpath("//*[@id=\"item_0_title_link\"]/div")).getText() ;
        prod[2] = driver.findElement(By.xpath("//*[@id=\"item_1_title_link\"]/div")).getText() ;
        prod[3] = driver.findElement(By.xpath("//*[@id=\"item_5_title_link\"]/div")).getText() ;
        prod[4] = driver.findElement(By.xpath("//*[@id=\"item_2_title_link\"]/div")).getText() ;
        prod[5] = driver.findElement(By.xpath("//*[@id=\"item_3_title_link\"]/div")).getText() ;

        System.out.println("\u001B[43;30m Orden Ascendente \u001B[0m");
        Arrays.sort(prod);
        for(String i: prod)
            System.out.println(i);

        System.out.println(" "); //dejar espacio entre resultados

        System.out.println("\u001B[43;30m Orden Descendente \u001B[0m");
        Arrays.sort(prod, Collections.reverseOrder());
        for(String i: prod)
            System.out.println(i);

        //Agregando productos al carrito
        driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[2]/div[3]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[3]/div[3]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[4]/div[3]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[5]/div[3]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[6]/div[3]/button")).click();

    //  ScrollBar vertical = new ScrollBar();
    //  vertical.setTranslateX(0);
    //    vertical.setTranslateX(0);
    //  vertical.relocate(0,0);

        // Ir al carrito de compras
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VerifEtiqCarrito)));

        driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[2]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[4]/div[2]/div[2]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[5]/div[2]/div[2]/button")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BtnCheckout)));
        driver.findElement(By.xpath(BtnCheckout)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EtiqCheckout)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContCheckout)));

        inputName = driver.findElement(By.xpath(Name));
        inputLastname = driver.findElement(By.xpath(Lastname));
        inputPostal = driver.findElement(By.xpath(Postal));

        inputName.sendKeys("Usuario A");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        inputLastname.sendKeys("Apellido UA");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        inputPostal.sendKeys("044");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath(BtnContinue)).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("\u001B[43;30m SubTotales \u001B[0m");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SubTot01)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SubTot02)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SubTot03)));

        System.out.println(driver.findElement(By.xpath("//*[@id=\"item_5_title_link\"]/div")).getText()+": "+driver.findElement(By.xpath(SubTot01)).getText().substring(1));
        System.out.println(driver.findElement(By.xpath("//*[@id=\"item_2_title_link\"]/div")).getText()+": "+driver.findElement(By.xpath(SubTot02)).getText().substring(1));
        System.out.println(driver.findElement(By.xpath("//*[@id=\"item_3_title_link\"]/div")).getText()+": "+driver.findElement(By.xpath(SubTot03)).getText().substring(1));

        float st01 = Float.parseFloat(driver.findElement(By.xpath(SubTot01)).getText().substring(1));   // Convertimos a entero el String
        float st02 = Float.parseFloat(driver.findElement(By.xpath(SubTot02)).getText().substring(1));
        float st03 = Float.parseFloat(driver.findElement(By.xpath(SubTot03)).getText().substring(1));
        float sum = st01 + st02 + st03;

        System.out.println("Subtotal(Esperado): " + sum);  // se imprime la suma de los items - valor esperado

        System.out.println("Subtotal(Obtenido): " + driver.findElement(By.xpath(ItemTotal)).getText().substring(13)); //valor obtenido
        float obt = Float.parseFloat(driver.findElement(By.xpath(ItemTotal)).getText().substring(13));

        if(sum==obt) {
            System.out.println("Resultado correcto");
        }
        else{
            System.out.println("Resultado incorrecto");
        }
        //Finalizando proceso
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BtnFinish)));
        driver.findElement(By.xpath(BtnFinish)).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EtiqFinish)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TitleFin)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MsjConfirm)));

        System.out.println("");
        System.out.println("\u001B[43;30m Mensaje Confirmación \u001B[0m");
        System.out.println("Msj Obtenido: " + driver.findElement(By.xpath(TitleFin)).getText());

        String txtObt = driver.findElement(By.xpath(TitleFin)).getText(); //Obtenido
        String txtEsp= "THANK YOU FOR YOUR ORDER";                        //Esperado

        if (txtObt.equals(txtEsp)){
            System.out.println("Confirmación exitosa");
        }
        else{
            System.out.println("Error en la confirmación");
        }

        driver.get("https://www.saucedemo.com/");
        System.out.println("Fin del test");
        driver.close();

    }
}
