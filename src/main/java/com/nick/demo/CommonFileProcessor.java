package com.nick.demo;

import com.nick.demo.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CommonFileProcessor {

    public static final String TARGET_FILE_PREFIX = "_output_";

    public static List<String> readFile(String path) throws IOException {
        List<String> input = new ArrayList<String>();
        BufferedReader reader = null;
        try{
            File sourceFile = new File(path);
            reader = new BufferedReader(new FileReader(sourceFile));
            String line = "";
            while(line != null){
                line = reader.readLine();
                if(line != null){
                    input.add(line);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(reader != null){
                reader.close();
            }
        }
        return input;
    }

    public static void writeOutputFile(List<?> lines, String path) throws IOException {
        if(lines != null && lines.size() > 0){
            BufferedWriter writer = null;
            try{
                writer = new BufferedWriter(new FileWriter(path));
                for(Object s : lines){
                    if(s instanceof String){
                        writer.write((String) s);
                    }else{
                        writer.write(s.toString());
                    }
                    writer.newLine();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(writer != null){
                    writer.close();
                }
            }
        }
    }
}
