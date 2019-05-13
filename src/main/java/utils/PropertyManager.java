package utils;

public class PropertyManager {

    public static void setSystemWebDriverProperty(){
        if (System.getProperty("os.name").contains(Constants.MAC_OS)){
            System.setProperty(Constants.CHROME_DRIVER_PROPERTY, Constants.CHROME_DRIVER_PATH);
        } else if (System.getProperty("os.name").contains(Constants.WIN_OS)){
            System.setProperty(Constants.CHROME_DRIVER_PROPERTY, Constants.CHROME_DRIVER_PATH + ".exe");
        };
    }
}
