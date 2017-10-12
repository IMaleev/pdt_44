package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    private WebDriver wd;

    private String browser;
    private MantisUIHelper mantisUiHelper;
    private FtpHelper ftpHelper;
    private MailHelper mailHelper;
    private JamesHelper jamesHelper;
    private DbHelper dbHelper;
    private NavigationHelper navigationHelper;
    private UsersHelper usersHelper;
    private SoapHelper soapHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {
        if (null != wd) {
            wd.quit();
        }
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public MantisUIHelper mantisUI() {
        if (null == mantisUiHelper) {
            mantisUiHelper = new MantisUIHelper(this);
        }
        return mantisUiHelper;
    }

    public FtpHelper ftp() {
        if (null == ftpHelper) {
            ftpHelper = new FtpHelper(this);
        }
        return ftpHelper;
    }

    public WebDriver getDriver() {
        if (null == wd) {
            if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
            } else if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            }
            wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }

    public MailHelper mail() {
        if (null == mailHelper) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public JamesHelper james() {
        if (null == jamesHelper) {
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }

    public DbHelper db() {
        if (null == dbHelper) {
            dbHelper = new DbHelper(this);
        }
        return dbHelper;
    }

    public NavigationHelper goTo() {
        if (null == navigationHelper) {
            navigationHelper = new NavigationHelper(this);
        }
        return navigationHelper;
    }

    public UsersHelper users() {
        if (null == usersHelper) {
            usersHelper = new UsersHelper(this);
        }
        return usersHelper;
    }

    public SoapHelper soap() {
        if (null == soapHelper) {
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }
}
