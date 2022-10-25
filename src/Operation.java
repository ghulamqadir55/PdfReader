import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Operation {


    private AndroidDriver myDriver;
    private String url;
    private String pkg;
    private String app;


    public AndroidDriver getMyDriver() {
        return myDriver;
    }

    public String getDevice() {
        return device;
    }

    private String device;

    public String getUrl() {
        return url;
    }


    public String getPkg() {
        return pkg;
    }


    public String getApp() {
        return app;
    }


    public void setUpDevice(String name, String pkg, String app, String url) throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, name);          // Add Your Device
        dc.setCapability("appPackage", pkg);
        dc.setCapability("appActivity", app);
        myDriver = new AndroidDriver(new URL(url), dc);

        this.device = name;
        this.app = app;
        this.pkg = pkg;
        this.url = url;


    }


    public boolean checkItemDisplayed(String id){
        try {

            myDriver.findElement(By.id(id)).isDisplayed();

            return true;



        } catch (NoSuchElementException e) {



            return false;



        }

    }
    public void performClick(String clickId) {
        myDriver.findElement(By.id(clickId)).click();
    }

    public String getText(String clickId) {
        return myDriver.findElement(By.id(clickId)).getText();
    }


    public void swipeScreen(Direction dir) {
        System.out.println("swipeScreen(): dir: '" + dir + "'"); // always log your actions

        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // final value depends on your app and could be greater
        final int ANIMATION_TIME = 200; // ms

        final int PRESS_TIME = 200; // ms

        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;

        // init screen variables
        Dimension dims = myDriver.manage().window().getSize();

        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

        switch (dir) {
            case DOWN: // center of footer
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
                break;
            case UP: // center of header
                pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
                break;
            case LEFT: // center of left side
                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
                break;
            case RIGHT: // center of right side
                pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction(myDriver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public void performInput(String id, String input) {
        myDriver.findElement(By.id(id)).clear();
        myDriver.findElement(By.id(id)).sendKeys(input);

    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }
}
