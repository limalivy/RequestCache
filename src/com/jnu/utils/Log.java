package com.jnu.utils;

import java.util.Date;

public class Log {
    public static void log(String tag, String format, Object... args) {
        System.out.println(String.format("%s => %s %s",new Date().toString(), tag, String.format(format, args)));
    }
}
