package com.ppk.utilities;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

/**
 * Utility class for handling logging operations using SLF4J.
 * This class provides a centralized method to retrieve a logger instance
 * for a given class. It is designed to promote consistent logging
 * practices across the application.
 * 
 */
public class LogUtil {


    private LogUtil() {}


    public static Logger getLogger(Class<?> className) {
        return LoggerFactory.getLogger(className);
    }
}
