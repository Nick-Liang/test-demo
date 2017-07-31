package com.nick.demo;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;

import java.io.IOException;
import java.util.regex.Pattern;

public class IPValidationRegex {
    private static final Pattern pattern = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");

    public void filterIPAddresses(String sourceFileName){
        try {
            if(this.getClass().getClassLoader().getResource(sourceFileName) != null){
                final IPValidationRegex ipValidationRegex = this;
                String sourcePath = this.getClass().getClassLoader().getResource(sourceFileName).getPath();
                String targetPath = this.getClass().getClassLoader().getResource("").getPath() + sourceFileName.split("\\.")[0] + CommonFileProcessor.TARGET_FILE_PREFIX + System.currentTimeMillis()+ sourceFileName.split("\\.")[1];
                CommonFileProcessor.writeOutputFile(FluentIterable.from(CommonFileProcessor.readFile(sourcePath)).filter(new Predicate<String>() {
                    public boolean apply(String ipAddress) {
                        return ipValidationRegex.isValidIPAddress(ipAddress);
                    }
                }).toList(), targetPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isValidIPAddress(String ipAddress){
        return pattern.matcher(ipAddress).find();
    }
}
