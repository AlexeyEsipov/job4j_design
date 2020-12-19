package ru.job4j.io;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        BasicConfigurator.configure();
        boolean logic = false;
        String name = "Aleksey";
        char ch = 'f';
        byte b = 1;
        short s = 20;
        int a = 33;
        long lng = 21478L;
        float flt = 127.5f;
        double dbl = 1230.558;

        LOG.debug("logic: {}, String: {}, char: {}, byte: {}, "
                    + "short: {}, int: {}, long {},"
                    + " float: {}, double: {}", logic, name, ch, b, s, a, lng, flt, dbl);
    }
}
