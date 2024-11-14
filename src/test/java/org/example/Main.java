package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {
    public static void main(String[] args) {
        String userName="Jon ";
        String userLastName="Doe";
        String userEmail="jondoe1@gmail.com";
        String userPassword ="Forta1.";
        System.setProperty("webdriver.chrome.driver","C:\\Users\\andre\\Downloads\\ChromeDirver_131\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait dvrWait= new WebDriverWait(driver,10);
        SignInUser.signUp(userName,userLastName,userEmail,userPassword,driver,dvrWait);
        SignInUser.signIn(userName,userLastName,userEmail,userPassword,driver,dvrWait);
    }
}