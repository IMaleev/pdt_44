package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class MantisUIHelper extends HelperBase {

    public MantisUIHelper(ApplicationManager app) {
        super(app);
    }

    public void startRegistration(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.xpath("//input[@value='Signup']"));
    }

    public void finishRegistration(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//button[@type='submit']"));
    }

    public void login(String username, String password) {
        type(By.id("username"), username);
        click(By.xpath("//input[@value='Login']"));
        type(By.id("password"), password);
        click(By.xpath("//input[@value='Login']"));
    }

    public void selectUserByName(String username) {
        wd.findElement(By.xpath("//tbody//a[text()='"+username+"']")).click();
    }

    public void selectUserByEmail(String email) {
        wd.findElement(By.xpath("//td[text()='"+email+"']/../td/a")).click();
    }

    public void startPasswordChange(String email) {
        app.goTo().loginPage();
        login("administrator", "root");
        app.goTo().manageUsersPage();
//        selectUserByName(username);
        selectUserByEmail(email);
        click(By.xpath("//input[@value='Reset Password']"));
    }

    public void finishPasswordChange(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//button[@type='submit']"));
    }
}
