package com.company;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.*;

import stuio.stuio;


public class ManageCourseFrame extends JFrame {
    /**
     * 面板
     */
    private JPanel panel;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel paneltitle;
    private JPanel panel3;
    private JPanel panelbtn;

    /**
     * 标签
     */
    private JLabel lblCourseID;
    private JLabel lblCourseName;
    private JLabel lblteacher;
    private JLabel lblCourseInfo;

    /**
     * 文本框
     */
    private JTextField txtCourseID;
    private JTextField txtCourseName;
    private JTextField txtteacher;
    private JTextArea txtCourseInfo;


    /**
     * 按钮
     */
    private JButton btnSave;
    private JButton btnExit;


    /**
     * 构造方法
     *
     * @param title
     */
    public ManageCourseFrame(String title) {
        super(title);
        initGUI();
    }

    /**
     * 初始化用户界面
     */
    private void initGUI() {
        // 创建组件
        panel = (JPanel) getContentPane();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        paneltitle = new JPanel();
        panelbtn = new JPanel();


        lblCourseID = new JLabel("CourseID：");
        lblCourseName = new JLabel(" CourseName：  ");
        lblteacher = new JLabel("teacher：");
        lblCourseInfo = new JLabel("<html>CourseInfo：<br><br>Collect Course information and store it <br>in a separate data file.<br>Each course is identified by an unique ID!</html>");

        txtCourseID = new JTextField(10);
        txtCourseName = new JTextField(24);
        txtCourseInfo = new JTextArea(5,30);
        txtCourseInfo.setLineWrap(true);
        txtteacher = new JTextField(12);


        btnSave = new JButton("Save[S]");
        btnSave.setMnemonic(KeyEvent.VK_S);
        btnExit = new JButton("Exit[X]");
        btnExit.setMnemonic(KeyEvent.VK_X);

        // 添加组件
        panel.setLayout(new GridLayout(3, 2));
        panel.add(panel1);
        panel.add(paneltitle);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panelbtn);

        panel1.add(lblCourseID);
        panel1.add(txtCourseID);
        panel1.add(lblteacher);
        panel1.add(txtteacher);
        panel2.add(lblCourseName);
        panel2.add(txtCourseName);

        paneltitle.add(lblCourseInfo);
        panel3.add(txtCourseInfo);
        panelbtn.add(btnSave);
        panelbtn.add(btnExit);

        // 设置窗口属性
       // setSize(300,200);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // 【关闭】按钮事件处理
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });

        // 【保存】按钮事件处理
        btnSave.addActionListener(new ActionListener() {
          @Override
            public void actionPerformed(ActionEvent e) {
               String sysmbol = ",";
                String info = txtCourseInfo.getText()+sysmbol;
                String tutor = txtteacher.getText()+sysmbol;
                String CourseID = txtCourseID.getText()+sysmbol;
                String CourseName = txtCourseName.getText();
                StringBuilder str = new StringBuilder();
                str.append(info).append(tutor).append(CourseID).append(CourseName);
                stuio input = new stuio();
              try {
                  input.WriteCSV(CourseName,str.toString(),4);
              } catch (IOException e1) {
                  e1.printStackTrace();
              }
                txtCourseName.setText("");
                txtCourseID.setText("");
                txtteacher.setText("");
                txtCourseInfo.setText("");

            }
        });

    }
}

