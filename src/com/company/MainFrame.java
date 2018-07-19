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
import javax.swing.table.TableColumn;
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
    private JMenuItem mniAddGrade;
    private JMenuItem mniBrowseStudent;
    private JMenuItem mniBrowseGrade;
    private JMenuItem mniCourse;


    /**
     * 面板
     */
    private JPanel panel;
    private JPanel  pnlMain;



    /**
     * 背景标签
     */
//    private JLabel lblBackground;
//    private ImageIcon imgBackground;


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

        // 创建【设置】菜单及其菜单项
        mnuSet = new JMenu("System Set[S]");
        mnuSet.setMnemonic(KeyEvent.VK_S);
        mniExit = new JMenuItem("Exit");

        // 创建【操作】菜单及其菜单项
        mnuOperate = new JMenu("Data Operate[O]");
        mnuOperate.setMnemonic(KeyEvent.VK_O);
        mniAddGrade = new JMenuItem("ManageGradeRecord");
        mniBrowseStudent = new JMenuItem("ManageStudentRecord");
        mniCourse = new JMenuItem("ManageCourse");
        mniBrowseGrade = new JMenuItem("BrowseGrade");

        // 创建面板
        panel = (JPanel) getContentPane();
        pnlMain = new JPanel();

        /*
         * 设置JTable的列名
         */
        String[] columnNames =
                { "CourseID", "CourseName", "Teacher", "CourseInfo"};

        stuio obj = new stuio();

        /*
         * JTable的其中一种构造方法
         */
        Object[][] data = obj.ReadCSVTbl(4);
        JTable table = new JTable(data, columnNames);

        add(table,BorderLayout.PAGE_END);
        TableColumn column = null;
        int colunms = table.getColumnCount();
        for(int i = 0; i < colunms; i++)
        {
            column = table.getColumnModel().getColumn(i);
            /*将每一列的默认宽度设置为150*/
            int colsize = 150;
            if(i==0)
                colsize = 120;
            if(i==colunms-1)
                colsize = 350;

            column.setPreferredWidth(colsize);
        }
        /*
         * 设置JTable自动调整列表的状态，此处设置为关闭
         */

        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        table.setGridColor(Color.darkGray);
        table.setFont(new Font("Dialog", 0, 18));
        JScrollPane scroll = new JScrollPane(table);
        panel.setLayout(new BorderLayout());
        panel.add(pnlMain,"North");
        pnlMain.add(scroll);
        // 创建背景图片

        //imgBackground = new ImageIcon("img/background.jpg");

        // 创建背景标签
//       lblBackground = new JLabel(imgBackground);
//        pnlMain.add(lblBackground);


        // 设置菜单栏
        setJMenuBar(mnbMain);

        // 添加【设置】菜单
        mnbMain.add(mnuSet);
        mnuSet.addSeparator();
        mnuSet.add(mniExit);



        // 添加【操作】菜单
        mnbMain.add(mnuOperate);
        mnuOperate.add(mniAddGrade);
        mnuOperate.add(mniBrowseStudent);
        mnuOperate.add(mniCourse);
        mnuOperate.add(mniBrowseGrade);



        // 设置窗口属性
        setSize(600, 500);
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
        mniAddGrade.addActionListener(new ActionListener() {
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

        // MANAGE gradeinfo
        mniBrowseGrade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BrowseGradeFrame("ManageGrade");
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

