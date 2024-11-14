package org.example;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SignInUser {

    /*
     * Creaación del usuario
     *
     * String mainNam = Nombre del usuario
     * String mainLastName = Apellido del usuario
     * String mainEmail = Email del usuario
     * String mainPassword = Contraseña del usuario
     * WebDriver driver = Instancia del WebDriver
     * WebDriverWait dvrWait Instancia del WebDriverWait
     *
     */
    public static void signUp(String name, String lastName, String email, String password, WebDriver driver, WebDriverWait dvrWait){
        driver.get("https://test-qa.inlaze.com/auth/sign-in");
        try{
            WebElement signUp= driver.findElement(By.xpath("//a[@class='font-bold text-primary']"));
            signUp.click();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            WebElement fillName = driver.findElement(By.xpath("//input[@id='full-name']"));
            fillName.sendKeys(name, lastName);
            WebElement fillEmail= driver.findElement(By.xpath("//input[@id='email']"));
            fillEmail.sendKeys(email);
            WebElement fillPassword= driver.findElement(By.xpath("//input[@id='password']"));
            fillPassword.sendKeys(password);
            WebElement passwordConfirm = driver.findElement(By.xpath("//input[@id='confirm-password']"));
            passwordConfirm.sendKeys(password);
            Boolean isMsgNotPresent = dvrWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[text()='Passwords do not match']")));
            Assert.assertFalse("Las contraseñas no son iguales",!isMsgNotPresent);
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            WebElement btnSignUp = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
            WebElement btnClick = dvrWait.until(ExpectedConditions.elementToBeClickable(btnSignUp));
            Assert.assertFalse("El boton no se puede seleccionar",btnClick==null);
            btnSignUp.click();
        }catch (Exception e){
            throw new AssertionError("No se pudo registrar el usuario",e);
        }
    }

    /*
     *Ingresa con el correo y contraseña creada por el usuario
     *
     * String mainNam = Nombre del usuario
     * String mainLastName = Apellido del usuario
     * String mainEmail = Email del usuario
     * String mainPassword = Contraseña del usuario
     * WebDriver driver = Instancia del WebDriver
     * WebDriverWait dvrWait Instancia del WebDriverWait
     *
     */
    public static void signIn(String mainName, String mainLastName,String mainEmail, String mainPassword, WebDriver driver, WebDriverWait dvrWait){
        try{
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            WebElement title = dvrWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='Sign in']")));
            Assert.assertFalse("El boton no se puede seleccionar",title==null);
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            WebElement userEmailSignIn= driver.findElement(By.xpath("//input[@id='email']"));
            userEmailSignIn.sendKeys(mainEmail);
            WebElement userPasswordSignIn= driver.findElement(By.xpath("//input[@id='password']"));
            userPasswordSignIn.sendKeys(mainPassword);
            WebElement frame= driver.findElement(By.xpath("//app-toast//button[@type='button']"));
            frame.click();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            WebElement btnSignIn = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
            btnSignIn.click();
            WebElement userCredential = dvrWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[text()='"+ mainName + mainLastName +"']"))));
            Assert.assertFalse("El nombre no se encontró",userCredential==null);
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            WebElement profile= driver.findElement(By.xpath("//img[@alt='Rengoku']"));
            profile.click();
            WebElement logOut = driver.findElement(By.xpath("//a[text()='Logout']"));
            logOut.click();
            driver.close();
        }catch (Exception e){
            throw new AssertionError("No se pudo iniciar sesión",e);
        }
    }
}
