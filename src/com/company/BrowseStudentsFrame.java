package com.company;

import stuio.stuio;
import stuio.CheckVaild;

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


public class BrowseStudentsFrame extends JFrame {
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
    private JLabel lblId;
    private JLabel lblName;
    private JLabel lblSex;
    private JLabel lblAge;

    /**
     * 声明文本框
     */
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtSex;
    private JTextField txtAge;

    /**
     * 声明按钮
     */
    private JButton btnTop;
    private JButton btnPrevious;
    private JButton btnNext;
    private JButton btnBottom;

    private JButton btnEdit;

    /**
     * 当前记录行号
     */
    private int currentRow;
    /**
     * 学生列表
     */


    /**
     * 构造方法
     *
     * @param title
     */
    public BrowseStudentsFrame(String title) {
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

        lblId = new JLabel("StuID：");
        lblName = new JLabel("Name：");
        lblSex = new JLabel("Sex ：");
        lblAge = new JLabel("Age ：");


        txtId = new JTextField(40);
        txtName = new JTextField(40);
        txtSex = new JTextField(40);
        txtAge = new JTextField(40);

        txtId.setEditable(false);
        txtName.setEditable(true);
        txtSex.setEditable(true);
        txtAge.setEditable(true);


        btnTop = new JButton("First[T]");
        btnPrevious = new JButton("Prev[P]");
        btnNext = new JButton("Next[N]");
        btnBottom = new JButton("Bottom[B]");
        btnEdit = new JButton("Edit[E]");
        btnTop.setMnemonic(KeyEvent.VK_T);
        btnPrevious.setMnemonic(KeyEvent.VK_P);
        btnNext.setMnemonic(KeyEvent.VK_N);
        btnBottom.setMnemonic(KeyEvent.VK_B);
        btnEdit.setMnemonic(KeyEvent.VK_E);

        // 添加组件
        panel.setLayout(new BorderLayout());
        panel.add(pnlCenter, BorderLayout.CENTER);
        panel.add(pnlSouth, BorderLayout.SOUTH);
        pnlCenter.setLayout(new GridLayout(6, 1));

        pnlCenter.add(pnlRow1);
        pnlCenter.add(pnlRow2);
        pnlCenter.add(pnlRow3);
        pnlCenter.add(pnlRow4);


        pnlRow1.add(lblId);
        pnlRow1.add(txtId);
        pnlRow2.add(lblName);
        pnlRow2.add(txtName);
        pnlRow3.add(lblSex);
        pnlRow3.add(txtSex);
        pnlRow4.add(lblAge);
        pnlRow4.add(txtAge);


        pnlSouth.add(btnTop);
        pnlSouth.add(btnPrevious);
        pnlSouth.add(btnNext);
        pnlSouth.add(btnBottom);
        pnlSouth.add(btnEdit);

        // 设置窗口属性
        setSize(500, 300);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        stuio ROWS = new stuio();
        int rows = ROWS.RowNum(true);

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
                    JOptionPane.showMessageDialog(null, "First Record！", "ManageStudentRecord", JOptionPane.WARNING_MESSAGE);
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
                    JOptionPane.showMessageDialog(null, "Last Record！", "ManageStudentRecord", JOptionPane.WARNING_MESSAGE);
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



        // 【保存】按钮事件处理
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sysmbol = ",";
                CheckVaild chk = new CheckVaild();
                int id_len = 8;
                int str_len= 20;
                boolean gogo = true;
                if (!chk.CheckIDVaild(txtId.getText(),id_len)){
                    JOptionPane.showConfirmDialog(null, "Input Value " + txtId.getText() + " is Invaild! At most " + id_len + "charaters!", "WARNING : student information system", JOptionPane.ERROR_MESSAGE);
                    dispose();
                    gogo = false;
                }
                if (!chk.CheckStrVaild(txtName.getText(),str_len)){
                    JOptionPane.showConfirmDialog(null, "Input Value " + txtName.getText() + " is Invaild! At most " + id_len + "charaters!", "WARNING : student information system", JOptionPane.ERROR_MESSAGE);
                    dispose();
                    gogo = false;
                }
                if (!chk.CheckStrVaild(txtSex.getText(),str_len)){
                    JOptionPane.showConfirmDialog(null, "Input Value " + txtSex.getText() + " is Invaild! At most " + str_len + "charaters!", "WARNING : student information system", JOptionPane.ERROR_MESSAGE);
                    dispose();
                    gogo = false;
                }
                if (!chk.CheckIDVaild(txtAge.getText(),id_len) || txtAge.getText().length() > 2 || Integer.parseInt(txtAge.getText()) < 1){
                    JOptionPane.showConfirmDialog(null, "Input Value " + txtAge.getText() + " is Invaild! ", "WARNING : student information system", JOptionPane.ERROR_MESSAGE);
                    dispose();
                    gogo = false;
                }

                if(gogo) {
                    String id = txtId.getText() + sysmbol;
                    String name = txtName.getText() + sysmbol;
                    String sex = txtSex.getText() + sysmbol;
                    String age = txtAge.getText();
                    StringBuilder str = new StringBuilder();
                    str.append(id).append(name).append(sex).append(age);
                    stuio input = new stuio();
                    try {
                        input.WriteCSV(str.toString(), 4);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
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
            //String str=input.readBinary(currentRow);
            setTitle("BrowseStudentsRecord" + " && Current：" + currentRow);
            try {
                String ID = input.ReadCSV(currentRow,0,true);
                txtId.setText(ID);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                String name = input.ReadCSV(currentRow,1,true);
                txtName.setText(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                String sex = input.ReadCSV(currentRow,2,true);
                txtSex.setText(sex);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                String age = input.ReadCSV(currentRow,3,true);
                txtAge.setText(age);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

