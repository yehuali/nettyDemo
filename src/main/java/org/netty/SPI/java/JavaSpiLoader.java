package org.netty.SPI.java;

import org.junit.Test;

import java.util.Iterator;
import java.util.ServiceLoader;

public class JavaSpiLoader {

    /**
     *
     */
    @Test
    public void loadInitialDrivers(){
        String drivers ="";
        ServiceLoader<Driver> loadedDrivers = ServiceLoader.load(Driver.class);
        Iterator<Driver> driversIterator = loadedDrivers.iterator();
        while(driversIterator.hasNext()) {
            //根据驱动名字具体实例化各个实现类
            Driver driver = driversIterator.next();
            System.out.println("DriverManager.initialize: jdbc.drivers = " + driver.getClass().getName());
        }

    }
}
