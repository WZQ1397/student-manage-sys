package com.company;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import stuio.stuio;


public class GradeManagementFrame extends JFrame {
    /**
     * 面板
     */
    private JPanel panel;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel5;

    /**
     * 标签
     */
    private JLabel lblStuID;
    private JLabel lblYear;
    private JLabel lblSemester;
    private JLabel lblCourseID;
    private JLabel lblGrade;

    /**
     * 文本框
     */
    private JTextField txtStuID;
    private JTextField txtYear;
    private JTextField txtSemester;
    private JTextField txtCourseID;
    private JTextField txtGrade;


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
    public GradeManagementFrame(String title) {
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
        panel5 = new JPanel();

        lblStuID = new JLabel("StuID：");
        lblYear = new JLabel("       Year：");
        lblSemester = new JLabel("Semester：");
        lblCourseID = new JLabel("CourseID：");
        lblGrade = new JLabel("Grade：");

        txtStuID = new JTextField(12);
        txtYear = new JTextField(10);
        txtCourseID = new JTextField(10);
        txtGrade = new JTextField(12);
        txtSemester = new JTextField(27);

        btnSave = new JButton("Save[S]");
        btnSave.setMnemonic(KeyEvent.VK_S);
        btnExit = new JButton("Exit[X]");
        btnExit.setMnemonic(KeyEvent.VK_X);

        // 添加组件
        panel.setLayout(new GridLayout(4, 1));
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel5);

        panel1.add(lblStuID);
        panel1.add(txtStuID);
        panel1.add(lblYear);
        panel1.add(txtYear);


        panel2.add(lblGrade);
        panel2.add(txtGrade);
        panel2.add(lblCourseID);
        panel2.add(txtCourseID);

        panel3.add(lblSemester);
        panel3.add(txtSemester);


        panel5.add(btnSave);
        panel5.add(btnExit);
        // 设置窗口属性
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
                String cid = txtCourseID.getText()+sysmbol;
                String grade = txtGrade.getText()+sysmbol;
                String tutor = txtSemester.getText()+sysmbol;
                String stuid = txtStuID.getText()+sysmbol;
                String year = txtYear.getText();
                StringBuilder str = new StringBuilder();
                str.append(cid).append(grade).append(tutor).append(stuid).append(year);
                stuio input = new stuio();
              try {
                  String filename = "Grade";
                  input.WriteCSV(filename,str.toString(),4);
              } catch (IOException e1) {
                  e1.printStackTrace();
              }
                txtYear.setText("");
                txtStuID.setText("");
                txtSemester.setText("");
                txtCourseID.setText("");
                txtGrade.setText("");

            }
        });

    }
}

