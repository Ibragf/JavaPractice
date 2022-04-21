package com.company;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.text.NumberFormat;
import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;
import com.company.FileTask;
import com.google.gson.Gson;
import org.xml.sax.SAXException;

public class Main {

    private String Name;
    private String DriveType;
    private String TotalSize;
    private String FreeSpace;
    private String LabelVolume;

    public static List<Main> fillDiskInfo()
    {
        double unit=1024*1024*1024;
        FileSystemView fsv= FileSystemView.getFileSystemView();
        List<Main> dlist=new ArrayList<Main>();
        File[] roots=File.listRoots();
        for (File file:roots)
        {
            Main diskInfo=new Main();
            try{
                diskInfo.Name=file.getCanonicalPath();
            }
            catch(IOException e){
                e.printStackTrace();
            }

            diskInfo.LabelVolume=fsv.getSystemDisplayName(file);
            diskInfo.TotalSize=num_to_str(file.getTotalSpace()/unit,2);
            diskInfo.FreeSpace=num_to_str(file.getFreeSpace()/unit, 2);
            diskInfo.DriveType=fsv.getSystemTypeDescription(file);
            dlist.add(diskInfo);
        }
        return dlist;
    }

    public static String num_to_str(double num,int length)
    {
        NumberFormat nf=NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(length);
        return nf.format(num);
    }

    public static void first_task()
    {
        List<Main> roots=fillDiskInfo();
        for (Main root: roots)
        {
            System.out.println("Название: " + root.Name);
            System.out.println("Метка: " + root.LabelVolume);
            System.out.println("Объем диска:" + root.TotalSize);
            System.out.println("Свободное место:" + root.FreeSpace);
            System.out.println("Тип:" + root.DriveType);
            System.out.println();
        }
    }
    public static void second_task()
    {
        System.out.println("Cоздание файла - 1");
        System.out.println("Запись в файл - 2");
        System.out.println("Чтение из файла - 3");
        System.out.println("Удаление файла - 4");

        Scanner in =new Scanner(System.in);
        String check=in.nextLine();


        if (check.startsWith("1")){
            FileTask.createFile();
        }
        if (check.startsWith("2")){
            FileTask.writeFile(in.nextLine());
            System.out.println("Written");
        }
        if(check.startsWith("3"))
        {
            System.out.println(FileTask.readFile());
        }
        if(check.startsWith("4"))
        {
            FileTask.deleteFile();
        }
    }
    public static void startMenu()
    {
        System.out.println("Вывод иноформации о дисках - 1");
        System.out.println("Работа с файлом - 2");
        System.out.println("Работа с файлом Json - 3");
        System.out.println("Работа с файлом XML - 4");
        System.out.println("Работа с zip-архивом - 5");
        System.out.println("Выход из программы - 0");
    }
    public static void third_task()
    {
        System.out.println("Запись в файл - 1");
        System.out.println("Чтение из файла - 2");
        System.out.println("Удаление файла - 3");

        Scanner in =new Scanner(System.in);
        String check=in.nextLine();

        if(check.startsWith("1"))
        {
            JsonTask.writeJson();
        }
        if(check.startsWith("2"))
        {
            JsonTask.readJson();
        }
        if(check.startsWith("3"))
        {
            JsonTask.deleteFile();
        }

    }
    public static void forth_task() throws ParserConfigurationException, IOException, SAXException, TransformerException {
        System.out.println();
        System.out.println("Возврат - 0");
        System.out.println("Добавление нового сотрудника - 1");
        System.out.println("Прочитать файл XML - 2");
        System.out.println("Удалить файл - 3");

        Scanner in =new Scanner(System.in);
        String check=in.nextLine();


        while(!check.startsWith("0"))
        {
            if(check.startsWith("1"))
            {
                XMLTask.addNewEmployee();
            }
            if(check.startsWith("2"))
            {
                XMLTask.readXML();
            }
            if(check.startsWith("3"))
            {
                XMLTask.deleteXML();
            }

            System.out.println();
            System.out.println("Возврат - 0");
            System.out.println("Добавление нового сотрудника - 1");
            System.out.println("Прочитать файл XML - 2");
            System.out.println("Удалить файл - 3");
            check=in.nextLine();
        }

    }
    public static void fiveth_task()
    {
        System.out.println();
        Scanner in=new Scanner(System.in);
        System.out.println("Создать архив и добавить файл - 1");
        System.out.println("Разархивировать и удалить файл - 2");
        System.out.println("Удалить архив - 3");
        String check= in.nextLine();

        if(check.startsWith("1"))
        {
            ZipTask.createZip();
        }
        if (check.startsWith("2"))
        {
            ZipTask.Zip();
        }
        if (check.startsWith("3"))
        {
            ZipTask.deleteAll();
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        startMenu();
        Scanner in = new Scanner(System.in);
        String check = in.nextLine();

        while(!check.startsWith("0")) {
            if (check.startsWith("1")) {
                first_task();
            }
            if (check.startsWith("2")) {
                second_task();
            }
            if (check.startsWith("3")) {
                third_task();
            }
            if (check.startsWith("4")) {
                forth_task();
            }
            if (check.startsWith("5")) {
                fiveth_task();
            }
            System.out.println();
            startMenu();
            check=in.nextLine();
        }

    }

}


