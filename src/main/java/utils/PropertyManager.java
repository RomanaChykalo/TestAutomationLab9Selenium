package utils;

import data.Const;

public class PropertyManager {

    public static void setSystemWebDriverProperty(){
        if (System.getProperty("os.name").contains(Const.MAC_OS)){
            System.setProperty(Const.CHROME_DRIVER_PROPERTY, Const.CHROME_DRIVER_PATH);
        } else if (System.getProperty("os.name").contains(Const.WIN_OS)){
            System.setProperty(Const.CHROME_DRIVER_PROPERTY, Const.CHROME_DRIVER_PATH + ".exe");
        };
    }
}
