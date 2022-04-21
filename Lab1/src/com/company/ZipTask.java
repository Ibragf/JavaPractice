package com.company;

import javax.naming.spi.DirectoryManager;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.zip.*;

public class ZipTask {
    private static String pathname="ZipTASK.zip";

    public static void createZip()
    {
        String pathfile=chooseFile();

        try(ZipOutputStream zip=new ZipOutputStream(new FileOutputStream(pathname));
            FileInputStream fis=new FileInputStream(pathfile);)
        {
            File file=new File(pathfile);
            ZipEntry entry=new ZipEntry(file.getName());
            zip.putNextEntry(entry);
            byte[] bytes=new byte[fis.available()];
            fis.read(bytes);
            zip.write(bytes);
            zip.closeEntry();
            System.out.println("DONE");


        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public static void Zip()
    {
        try(ZipInputStream zip=new ZipInputStream(new FileInputStream(pathname)))
        {
            ZipEntry entry;
            long size;
            String name;

            while((entry=zip.getNextEntry())!=null)
            {
                name=entry.getName();
                size=entry.getSize();

                FileOutputStream fos=new FileOutputStream(name);
                int x;
                while((x=zip.read())!=-1)
                {
                    fos.write(x);
                }
                fos.flush();
                zip.closeEntry();
                fos.close();

                File file=new File(name);

                System.out.printf("Название файла: %s \nРазмер файла: %d КБ\n", file.getName(), file.length());
                System.out.println("Удалить файл? 1-да");
                Scanner in=new Scanner(System.in);
                String check=in.nextLine();
                if(check.startsWith("1"))
                {
                    file.delete();
                    System.out.println("deleted");
                    System.out.println();
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteAll()
    {
        File file=new File(pathname);
        file.delete();
        System.out.println("deleted");

    }

    private static String chooseFile()
    {
        File file = null;
        JFileChooser chooser=new JFileChooser();
        int ret=chooser.showDialog(null,"Открыть файл");
        if(ret==JFileChooser.APPROVE_OPTION)
        {
            file=chooser.getSelectedFile();
        }
        String returnPath="";

        returnPath=file.getAbsolutePath();

        return returnPath;
    }
}
