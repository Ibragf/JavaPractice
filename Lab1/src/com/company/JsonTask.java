package com.company;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class JsonTask {

    private static String pathname="JsonStudent.json";

    public static void writeJson()
    {
        File file =new File(pathname);
        Gson gson=new Gson();
        Student student=new Student("Marl", "22");
        String jsonText=gson.toJson(student);
        System.out.println(jsonText);

        try(FileOutputStream fos=new FileOutputStream(pathname))
        {
            byte[] bytes=jsonText.getBytes();
            fos.write(bytes,0,bytes.length);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public static void readJson()
    {
        File file=new File(pathname);
        Gson gson=new Gson();
        String jsonRead="";

        try(FileReader fr=new FileReader(pathname))
        {
            Scanner scan=new Scanner(fr);

            if(scan.hasNextLine())
            {
                jsonRead+=scan.nextLine();
            }
            fr.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        Student st=gson.fromJson(jsonRead,Student.class);


        System.out.println("Имя:"+st.name);
        System.out.println("Возраст:"+st.age);
    }

    public static void deleteFile()
    {
        File file=new File(pathname);
        file.delete();
    }

}

class Student{
    public String name;
    public String age;

    public Student(String name,String age){
        this.name=name;
        this.age=age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}

