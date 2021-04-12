package com.company;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        ArrayList<Ffile> files = new ArrayList<>();
        ArrayList<Dir> dirs = new ArrayList<>();
        File path = new File(".\\srcc\\Utrom's secrets");
        File dest = new File("./dest/");
        dest.mkdirs();
        System.out.println("Исходная директория");
        tree(path);
        //Map<String, Set<Ffile>> equalFiles = new HashMap<>();
        //Map<String, Set<Dir>> equalDirs = new HashMap<>();
        //заводим коллекции с файлами и с папками
        for (File file : Objects.requireNonNull(path.listFiles())) {
            if (file.isDirectory()) dirs.add(new Dir(file));
            else files.add(new Ffile(file));
        }
        //сначала найдём файлы, которые точно нужно скопировать, заполнив сет (никаких проверок делать не нужно будет,
        //для этого перегружена equals.
        //аналогично для папок
        SortedSet<Ffile> difFiles = new TreeSet<>(files);
        //files = new ArrayList<>(Arrays.asList(difFiles.toArray(new Ffile[0])));
        Map<String, Integer> fileCount = new HashMap<>();


        SortedSet<Dir> difDirs = new TreeSet<>(dirs);
        Map<String, Integer> dirCount = new HashMap<>();
        //копируем файлы в нужное назначение и с нужным названием
        //в коллекции Map храним сколько раз встречался файл с таким названием для создания нужного названия
        //аналогично для папок, за исключением, что сначала создаём папку, потом копируем всё её содержимое
        for (var item : difFiles){
                if (!fileCount.containsKey(item.getName())) {
                    fileCount.put(item.getName(), 0);
                    try {
                        var to = dest.getPath()+"\\"+item.getName()+"."+item.getType();
                        copyFile(new File(item.getPath()), new File(to));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    fileCount.put(item.getName(),fileCount.get(item.getName())+1);
                    try {
                        copyFile(new File(item.getPath()),
                                new File(String.format("%s%s (%d)%s",
                                        dest.getPath()+"\\",
                                        item.getName(),
                                        fileCount.get(item.getName()),
                                        "."+item.getType())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }


        for (var item : difDirs){
            if (!dirCount.containsKey(item.getName())) {
                dirCount.put(item.getName(), 0);
                var to = dest.getPath()+"\\" + item.getName();
                new File(to).mkdir();
                to += "\\";
                for (var file : item.getFiles()){
                    try {
                        copyFile(new File(file.getPath()), new File(to+file.getName()+"."+file.getType()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }else{
                dirCount.put(item.getName(),dirCount.get(item.getName())+1);
                var to = String.format("%s%s (%d)",
                        dest.getPath()+"\\",
                        item.getName(),
                        dirCount.get(item.getName()));
                new File(to).mkdir();
                to += "\\";
                for (var file : item.getFiles()){
                    try {
                        copyFile(new File(file.getPath()), new File(to+file.getName()+"."+file.getType()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        tree(dest);


    }


    static void copyFile(File sourceFile, File destFile) throws IOException {
        //destFile.mkdirs();
        if(!destFile.exists()) destFile.createNewFile();


        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        }
        finally {
            if(source != null) {
                source.close();
            }
            if(destination != null) {
                destination.close();
            }
        }
    }

    static void toZip(String dir, String name) throws IOException {

        Path p = Files.createFile(Paths.get(dir+"\\"+name));

    }


//метод для печати дерева директории (специализированно для 1 уровня вложенности + размер файла == содержимое)
    static void tree(File dir){
        System.out.println(dir.getPath());
        ArrayList<File> dirs = new ArrayList<>();
        ArrayList<File> files = new ArrayList<>();
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) dirs.add(file);
            else files.add(file);
        }

        for (var item : dirs){
            System.out.println("\t|- " + item.getName() + "x/");

            for (var file : Objects.requireNonNull(item.listFiles())){
                Scanner reader = null;
                String data = "";
                try {
                    reader = new Scanner(file);
                    data = reader.nextLine();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                System.out.println("\t\t|- " + file.getName() + " " + data);
            }
        }
        for (var file : files){
            Scanner reader;
            String data = "";
            try {
                reader = new Scanner(file);
                data = reader.nextLine();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("\t|- " + file.getName()+ " " + data);
        }



    }

}
