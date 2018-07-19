package com.company;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.*;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import stuio.stuio;
public class MainFrame extends JFrame {
    /**
     * 菜单部分
     */
    private JMenuBar mnbMain;
    /**
     * 设置菜单
     */
    private JMenu mnuSet;
    private JMenuItem mniExit;
    /**
     * 操作菜单
     */
    private JMenu mnuOperate;
    private JMenuItem mniAddStudent;
    private JMenuItem mniBrowseStudent;
    private JMenuItem mniCourse;


    /**
     * 面板
     */
    private JPanel panel;
    private JPanel  pnlMain;

    private JTable table;



    /**
     * 背景标签
     */
    private JLabel lblBackground;



    /**
     * 构造方法
     *
     * @param title
     */
    public MainFrame(String title) {
        super(title);
        try {
            initGUI();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化用户界面
     */
    private void initGUI() throws IOException {
        // 创建主菜单
        mnbMain = new JMenuBar();

//        JScrollPane scrollPane = new JScrollPane();
//        scrollPane.setBounds(0,0,700,250);

        // 创建【设置】菜单及其菜单项
        mnuSet = new JMenu("System Set[S]");
        mnuSet.setMnemonic(KeyEvent.VK_S);
        mniExit = new JMenuItem("Exit");

        // 创建【操作】菜单及其菜单项
        mnuOperate = new JMenu("Data Operate[O]");
        mnuOperate.setMnemonic(KeyEvent.VK_O);
        mniAddStudent = new JMenuItem("ManageGradeRecord");
        mniBrowseStudent = new JMenuItem("ManageStudentRecord");
        mniCourse = new JMenuItem("ManageCourse");

        // 创建面板
        panel = (JPanel) getContentPane();
        pnlMain = new JPanel();

        // 创建背景图片
        //imgBackground = new ImageIcon("images/background.jpg");

        // 创建背景标签
       // lblBackground = new JLabel(imgBackground);


        // 设置菜单栏
        setJMenuBar(mnbMain);

        // 添加【设置】菜单
        mnbMain.add(mnuSet);
        mnuSet.addSeparator();
        mnuSet.add(mniExit);



        // 添加【操作】菜单
        mnbMain.add(mnuOperate);
        mnuOperate.add(mniAddStudent);
        mnuOperate.add(mniBrowseStudent);
        mnuOperate.add(mniCourse);



        // 设置窗口属性
        setSize(800, 640);
        setVisible(true);
        setLocationRelativeTo(null);

        // 关闭窗口单击事件
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                exitSystem();
            }
        });


        // 【退出系统】菜单项单击事件
        mniExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitSystem();
            }
        });

        // ADD gradeinfo
        mniAddStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               new GradeManagementFrame("ManageGradeRecord");
//                try {
//                    new stuio().ReadCSV();
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
            }
        });

        // MANAGE student
        mniBrowseStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BrowseStudentsFrame("ManageStudentRecord");
            }
        });

        // MANAGE course
        mniCourse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ManageCourseFrame("ManageCourse");
            }
        });

    }

    /**
     * 退出系统
     */
    protected void exitSystem() {
        int choice = JOptionPane.showConfirmDialog(null, "Are You Sure to Exit？", "student information system", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

    }

}

