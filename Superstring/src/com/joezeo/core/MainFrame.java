package com.joezeo.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    public MainFrame(){
        handler = new Handler();
    }
    /**
     * 中英文歌词文件加载状态
     */
    private String eLoad = "未加载";
    private String cLoad = "未加载";

    /**
     * 显示中英文歌词加载状态的lable
     */
    private JLabel label;

    /**
     * 功能处理器
     */
    private Handler handler;

    /**
     * 加载窗口
     */
    public void launchFrame(){
        //设置宽、高
        setSize(500,200);
        //设置位置
        setLocation(300,400);
        setTitle("Superstring Pro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        createMenu();
        createButton();
        createLable();

        setVisible(true);
    }

    /**
     * 创建菜单栏
     */
    private void createMenu(){
        //创建菜单条
        JMenuBar jmb = new JMenuBar();


        JMenu menu_1 = new JMenu("选择文件");

        JMenuItem item_1 = new JMenuItem("加载英文歌词文件");
        JMenuItem item_2 = new JMenuItem("加载中文歌词文件");
        menu_1.add(item_1);
        menu_1.add(item_2);

        item_1.addActionListener(this);
        item_2.addActionListener(this);

        jmb.add(menu_1);

        add(jmb,BorderLayout.NORTH);
        //不能设定位置，会自动放在最上部
        setJMenuBar(jmb);
    }

    /**
     * 创建按钮
     */
    private void createButton(){
        JButton b = new JButton("Merge two file");
        b.setPreferredSize(new Dimension(30, 40));//优先的大小
        b.setFont(new Font("黑体", Font.PLAIN, 20));//文字字体
        b.setBounds(50, 90, 30, 40);//位置和大小
        b.addActionListener(this);
        add(b,BorderLayout.SOUTH);
    }

    /**
     * 创建文本框
     */
    private void createLable(){
        label = new JLabel();
        label.setLayout(null);

        label.setText(getLableTextContent());

        add(label);
    }


    /**
     * 获取中英文歌词加载状态字符串
     * @return 中文歌词加载状态字符串
     */
    private String getLableTextContent(){
        StringBuilder sb = new StringBuilder();

        sb.append("<html><body>");
        sb.append("英文歌词：").append(eLoad);
        sb.append("<br /><br />");
        sb.append("中文歌词：").append(cLoad);
        sb.append("</body></html>");
        return sb.toString();
    }

    /**
     * 刷新文本框
     */
    private void refleshLabel(){
        label.setText(getLableTextContent());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comd = e.getActionCommand();
        switch (comd){
            case "Merge two file":
                handler.merge(this);
                break;
            case "加载英文歌词文件":
                handler.loadEnFile();
                eLoad = "已加载";
                refleshLabel();
                break;
            case "加载中文歌词文件":
                handler.loadCnFile();
                cLoad = "已加载";
                refleshLabel();
                break;
        }
    }
}
