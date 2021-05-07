package com.zjx.demo.fileHandle;

import java.io.*;

public class FileHandle {


    public static void main(String[] args) throws IOException {
        //定义输出目录
        String FileOut = "C:\\Users\\65454\\Desktop\\fsdownload\\KBank_Payout_20210421.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(FileOut));

        //读取目录下的每个文件或者文件夹，并读取文件的内容写到目标文字中去
        File[] list = new File("C:\\Users\\65454\\Desktop\\fsdownload\\files").listFiles();
        for (File file : list) {
            if (file.isFile()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    bw.write(line);
                    bw.newLine();
                }
                br.close();
            }
        }
        bw.close();

    }
}
