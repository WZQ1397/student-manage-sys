package com.company;




import stuio.stuio;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class BrowseGradeFrame extends JFrame {
    /**
     * 声明面板
     */
    private JPanel panel;
    private JPanel pnlCenter;
    private JPanel pnlRow1;
    private JPanel pnlRow2;
    private JPanel pnlRow3;
    private JPanel pnlRow4;
    private JPanel pnlRow5;

    private JPanel pnlSouth;

    /**
     * 声明标签
     */
    private JLabel lblStuID;
    private JLabel lblYear;
    private JLabel lblSemester;
    private JLabel lblCourseID;
    private JLabel lblGrade;


    /**
     * 声明文本框
     */
    private JTextField txtStuID;
    private JTextField txtYear;
    private JTextField txtSemester;
    private JTextField txtCourseID;
    private JTextField txtGrade;

    /**
     * 声明按钮
     */
    private JButton btnTop;
    private JButton btnPrevious;
    private JButton btnNext;
    private JButton btnBottom;


    /**
     * 当前记录行号
     */
    private int currentRow;

    /**
     * 构造方法
     *
     * @param title
     */
    public BrowseGradeFrame(String title) {
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
    private void initGUI()  throws IOException{
        // 创建组件
        panel = (JPanel) getContentPane();
        pnlCenter = new JPanel();
        pnlSouth = new JPanel();

        pnlRow1 = new JPanel();
        pnlRow2 = new JPanel();
        pnlRow3 = new JPanel();
        pnlRow4 = new JPanel();
        pnlRow5 = new JPanel();

        pnlRow1.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlRow2.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlRow3.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlRow4.setLayout(new FlowLayout(FlowLayout.LEFT));

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

        txtStuID.setEditable(false);
        txtYear.setEditable(true);
        txtCourseID.setEditable(true);
        txtGrade.setEditable(true);
        txtSemester.setEditable(true);


        btnTop = new JButton("First[T]");
        btnPrevious = new JButton("Prev[P]");
        btnNext = new JButton("Next[N]");
        btnBottom = new JButton("Bottom[B]");
        btnTop.setMnemonic(KeyEvent.VK_T);
        btnPrevious.setMnemonic(KeyEvent.VK_P);
        btnNext.setMnemonic(KeyEvent.VK_N);
        btnBottom.setMnemonic(KeyEvent.VK_B);

        // 添加组件
        panel.setLayout(new BorderLayout());
        panel.add(pnlCenter, BorderLayout.CENTER);
        panel.add(pnlSouth, BorderLayout.SOUTH);
        pnlCenter.setLayout(new GridLayout(6, 1));

        pnlCenter.add(pnlRow1);
        pnlCenter.add(pnlRow2);
        pnlCenter.add(pnlRow3);
        pnlCenter.add(pnlRow4);


        pnlRow1.add(lblStuID);
        pnlRow1.add(txtStuID);
        pnlRow1.add(lblYear);
        pnlRow1.add(txtYear);


        pnlRow2.add(lblGrade);
        pnlRow2.add(txtGrade);
        pnlRow2.add(lblCourseID);
        pnlRow2.add(txtCourseID);

        pnlRow3.add(lblSemester);
        pnlRow3.add(txtSemester);


        pnlSouth.add(btnTop);
        pnlSouth.add(btnPrevious);
        pnlSouth.add(btnNext);
        pnlSouth.add(btnBottom);

        // 设置窗口属性
        setSize(500, 300);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        stuio ROWS = new stuio();
        int rows = ROWS.RowNum(false);

        // 【第一条】按钮单击事件
        btnTop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 设置当前记录号
                currentRow = 1;
                // 填充当前记录数据
                fillFrameData(currentRow);
            }
        });

        // 【上一条】按钮单击事件
        btnPrevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentRow > 1) {
                    // 设置当前记录号
                    currentRow--;
                    // 填充当前记录数据
                    fillFrameData(currentRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Last Record！", "BrowseGrade", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // 【下一条】按钮单击事件
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentRow <= rows) {
                    // 设置当前记录号
                    currentRow++;
                    // 填充当前记录数据
                    fillFrameData(currentRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Last Record！", "BrowseGrade", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // 【最后一条】按钮单击事件
        btnBottom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 设置当前记录号
                currentRow = rows+1;
                // 填充当前记录数据
                fillFrameData(currentRow);
            }
        });

    }

    /**
     * 将当前记录数据填充窗口各文本框
     *
     * @param currentRow
     */
    private void fillFrameData(int currentRow) {
        if (currentRow > 0) {
           stuio input = new stuio();
            setTitle("BrowseGradeRecord" + " && Current：" + currentRow);
            try {
                String CID = input.ReadCSV(currentRow,0,false);
                txtCourseID.setText(CID);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                String SID = input.ReadCSV(currentRow,3,false);
                txtStuID.setText(SID);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                String tutor = input.ReadCSV(currentRow,2,false);
                txtSemester.setText(tutor);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                String year = input.ReadCSV(currentRow,4,false);
                txtYear.setText(year);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                String grade = input.ReadCSV(currentRow,1,false);
                txtGrade.setText(grade);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

