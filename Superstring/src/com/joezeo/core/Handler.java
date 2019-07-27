package com.joezeo.core;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

/**
 * 功能处理器
 */
public class Handler {
    /**
     * 文件对象
     */
    private File enFile = null;
    private File cnFile = null;

    /**
     * 加载英文歌词文件
     */
    public void loadEnFile(){
        enFile = doFileChooser();
    }

    /**
     * 加载中文歌词文件
     */
    public void loadCnFile(){
        cnFile = doFileChooser();
    }

    /**
     * 合并两个文件
     * 按照：一句英文歌词+\n+一句中文歌词的格式进行合并
     */
    public void merge(JFrame frame){
        /*
        如果没有加载完歌词文件则会弹出对话框提醒
        并结束此方法
         */
        if(enFile==null || cnFile==null){
            JDialog dialog = new JDialog(frame);
            dialog.setTitle("错误！！");

            JLabel label = new JLabel();
            label.setLayout(null);

            if(enFile == null && cnFile != null){
                label.setText("请加载英文歌词文件！");
            } else if(cnFile == null && enFile != null){
                label.setText("请加载中文歌词文件！");
            } else {
                label.setText("请加载歌词文件！");
            }

            dialog.add(label);
            dialog.setLocation(640, 320);
            dialog.setSize(250,170);
            dialog.setModal(true);
            dialog.setVisible(true);

            return;
        }
        
        try(BufferedReader enBr = new BufferedReader(new FileReader(enFile));
        BufferedReader cnBr = new BufferedReader(new FileReader(cnFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(enFile.getAbsolutePath()+"合并.txt")))) {
            String str1 = "";
            String str2 = "";
            String cotent = "";

            while(str1 != null && str2 != null){
                 str1 = delBlankLine(enBr.readLine());
                 str2 = delBlankLine(cnBr.readLine());
                 cotent = str1 + "\\n" +str2 + "\r\n";
                bw.write(cotent);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 选择文件框
     * @return 选择的文件
     */
    private File doFileChooser(){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Text file", "txt");
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.showDialog(new JLabel(), "选择");
        chooser.setCurrentDirectory(new File("E:/"));
        return chooser.getSelectedFile();
    }

    private String delBlankLine(String str){
        if(str == null){
            return null;
        }
        if(str.trim()!=null && !str.trim().equals("")){
            return str;
        } else {
            return "";
        }
    }
}
