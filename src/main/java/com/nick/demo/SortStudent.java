package com.nick.demo;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.nick.demo.model.Student;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class SortStudent {

    public void sort(String sourceFileName) {

        try {
            if (this.getClass().getClassLoader().getResource(sourceFileName) != null) {
                String sourcePath = this.getClass().getClassLoader().getResource(sourceFileName).getPath();
                String targetPath = this.getClass().getClassLoader().getResource("").getPath() + sourceFileName.split("\\.")[0] + CommonFileProcessor.TARGET_FILE_PREFIX + System.currentTimeMillis() + sourceFileName.split("\\.")[1];
                List<Student> source = FluentIterable.from(CommonFileProcessor.readFile(sourcePath)).transform(new Function<String, Student>() {
                    public Student apply(String s) {
                        Student student = new Student();
                        String[] arr = s.split("[ ,:/|]");
                        student.setId(Long.parseLong(arr[0]));
                        student.setName(arr[1]);
                        student.setGpa(Double.parseDouble(arr[2]));
                        return student;
                    }
                }).toSortedList(new Comparator<Student>() {
                    public int compare(Student o1, Student o2) {
                        int diff = 0;
                        if(o1.getGpa() != o2.getGpa()){
                            diff = o1.getGpa() > o2.getGpa() ? -1 : 1;
                        }else if(o1.getName().toCharArray()[0] != o2.getName().toCharArray()[1]){
                            diff = o1.getName().toCharArray()[0] - o2.getName().toCharArray()[0];
                        }else{
                            diff = o1.getId() > o2.getId() ? -1 : 1;
                        }
                        return diff;
                    }
                });

                CommonFileProcessor.writeOutputFile(source, targetPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
