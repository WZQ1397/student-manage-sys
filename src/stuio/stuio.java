package stuio;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import javax.swing.*;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

import java.io.File;

public class stuio {
    String Base = "data/";
    public String inPath = Base + "stu.csv";
    public String outPath = Base + "Grade.csv";
    public ArrayList<String[]> List = new ArrayList<String[]>();
    private boolean CheckandCreateFile(String path) {

        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
                //WriteCSV(file.getName(),file.getName(),0);
                System.out.println("FILE NOT EXISTS，CREATE NEW！");
            } else {
                System.out.println("FILE NOT EXISTS！");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public void readBinary(int i) {
        try {
            DataInputStream in = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(
                            "E:\\data.dat")));
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Openfile(String path) throws IOException {
        CsvReader reader = new CsvReader(path, ',', Charset.forName("utf-8"));
        reader.readHeaders();
        while (reader.readRecord()) {
            List.add(reader.getValues());
        }
        //System.out.println(List.get(0)[1]);
        reader.close();

    }

    public int RowNum(boolean in) throws IOException {
        String path = inPath;
        if (in == false){
            path = outPath;
        }

        Openfile(path);
        return List.size();
    }


    public String ReadCSV(int selrow, int col, boolean in) throws IOException {
        for (int row = 0; row <= RowNum(in) && row <= selrow; row++) {
            int Length = List.get(row).length;
            if (Length > 0) {
                for (int i = 0; i < Length; i++) {
                    System.out.print(List.get(row)[i] + ",");
                }//for

            }//if
            System.out.println(List.size());
        }//for
        return List.get(selrow)[col];

    }

    public Object[][] ReadCSVTbl(int col) throws IOException {
        File[] lst = new File(Base).listFiles(new SuffixFilter(".class.csv"));
        int filenums = lst.length;
        int j = 0;
        Object[][] data = new Object[filenums][col];
        for (File file : lst) {
            for (int i = 0; i < col; i++) {
                Openfile(file.toString());

                data[j][i] = List.get(0)[i];
                System.out.println(data[j][i]);
            }
            j++;
            List.clear();
        }//for
        return data;

    }//class

    public boolean WriteCSV(String content, int cols) throws IOException {
        //stuio IO = new stuio();
        //IO.CheckandCreateFile(outPath);
        //CsvWriter wr = new CsvWriter(outPath,',', Charset.forName("utf-8"));
        //String[] header = {};
        //wr.writeRecord(header);
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPath, true), "utf-8"), 1024);
            bw.write(content);
            bw.newLine();
            bw.flush();
            bw.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        //wr.close();
    }

    public boolean WriteCSV(String filename, String content, int cols) throws IOException {
        stuio IO = new stuio();
        Boolean CHK = true;
        filename = Base + filename + ".csv";
        if (IO.CheckandCreateFile(filename)) {
            int choice = JOptionPane.showConfirmDialog(null, "Are You Sure to Save it？" + filename + " is Exist!", "WARNING : student information system", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION) {
                CHK = false;

            }
        }

        if (CHK) {
            try {
                boolean chk = true;
                if (filename.endsWith(".class"))
                    chk = false;
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename, chk), "utf-8"), 1024);
                bw.write(content);
                bw.newLine();
                bw.flush();
                bw.close();

                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }

            //wr.close();
        }
        wirteBinary();
        return false;
    }

    private String wirteBinary() {
        String encoding = "UTF-8";
        File file = new File(inPath);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }
}


