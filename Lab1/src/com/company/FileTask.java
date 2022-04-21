package com.company;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class FileTask {
    private static String pathname="Math.txt";

    public static void createFile() {
        File newFile = new File(pathname);
        if (!newFile.exists()) {
            try {
                if (newFile.createNewFile()) {
                    System.out.println("File is created");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void writeFile(String str)
    {
        try(FileOutputStream fos=new FileOutputStream(pathname)){
            byte[] bytes=str.getBytes();
            fos.write(bytes,0, bytes.length);

        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static String readFile()
    {
        String str = "";
        try (FileReader fr=new FileReader(pathname)) {
            Scanner scan = new Scanner(fr);

            while (scan.hasNextLine()) {
                str = str + scan.nextLine();
            }
            fr.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return str;
    }

    public static void deleteFile()
    {
        File file=new File(pathname);
        file.delete();
    }
}
