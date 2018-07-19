package stuio;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

import java.io.File;

public class stuio {
    public String inPath = "stu.csv";
    public String outPath = "stu.csv";
    public ArrayList<String[]> List = new ArrayList<String[]>();

    private void CheckandCreateFile() {

        File file = new File(outPath);
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("文件不存在，新建成功！");
            } else {
                System.out.println("文件存在！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int RowNum(boolean in) throws IOException {
        String path = inPath;
        if (in == false)
            path = outPath;
        CsvReader reader = new CsvReader(path, ',', Charset.forName("utf-8"));
        reader.readHeaders();
        while (reader.readRecord()) {
            List.add(reader.getValues());
        }
        reader.close();
        return List.size();
    }

    public String ReadCSV(int selrow, int col) throws IOException {

        for (int row = 0; row < RowNum(true) && row <= selrow; row++) {
            int Length = List.get(row).length;
            if (Length > 0) {
                for (int i = 0; i < Length; i++) {
                    System.out.print(List.get(row)[i] + ",");
                }//for

            }//if
            System.out.println(List.size());
        }//for
        return List.get(selrow)[col];

    }//class

    public Object[][] ReadCSVTbl(int col) throws IOException {
        File[] lst = new File(".").listFiles(new SuffixFilter(".csv"));
        int filenums = lst.length;
        int j = 0;
        Object[][] data=new Object[filenums][col];
            for (File file : lst){
                CsvReader reader = new CsvReader(file.toString(), ',', Charset.forName("utf-8"));
                reader.readHeaders();
                while (reader.readRecord()) {
                    List.add(reader.getValues());
                }
                reader.close();
                for (int i = 0; i < col; i++) {
                    data[j][i] = List.get(0)[i];
            }
            j++;
            //System.out.println(List.size());
        }//for
        return data;

    }//class

    public boolean WriteCSV(String content, int cols) throws IOException {
        stuio IO = new stuio();
        IO.CheckandCreateFile();
        //CsvWriter wr = new CsvWriter(outPath,',', Charset.forName("utf-8"));
        //String[] header = { "Name","Province","City","Address","Tel","Website","Server_content","Jigou_cengji","Type","Parent_level1","Parent_level2","Branch_level" };
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
        IO.CheckandCreateFile();
        //CsvWriter wr = new CsvWriter(outPath,',', Charset.forName("utf-8"));
        //String[] header = { "Name","Province","City","Address","Tel","Website","Server_content","Jigou_cengji","Type","Parent_level1","Parent_level2","Branch_level" };
        //wr.writeRecord(header);
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"), 1024);
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
//    public static void main( String args[]) throws IOException{
//        stuio IO=new stuio();
//        IO.ReadCSV();
//        IO.WriteCSV();
}

    /*File file = new File(".E:\\x.csv") ; // 建立文件
    public void readrecord()
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("E:\\x.csv"));//换成你的文件名
            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
            String line = null;
            while((line=reader.readLine())!=null){
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分

                String last = item[item.length-1];//这就是你要的数据了
                //int value = Integer.parseInt(last);//如果是数值，可以转化为数值
                System.out.println(last);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void record(){
        try{
            FileInputStream fis = new FileInputStream(file) ;
            DataInputStream ois = new DataInputStream(fis) ;

            System.out.println();
            ois.close();
            fis.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }*/

    /*
    public void wirteBinary(String lst) {
        try {
            DataOutputStream os = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream(
                            "E:\\data.dat",true)));
            os.writeUTF(lst);
            os.flush();
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
    }*/
