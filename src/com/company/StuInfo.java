package com.company;

class StuInfo{
    public static int RecordSize=72;// 总计8+30*2+4=72字节，算准总字节数对于连续读取很重要
    private long id;// long类型似占8字节
    private String name;
    public static int NameSize=30;// 预计名称最多30个字符，占30*2=60字节
    private int age;// int类型占4字节

    public StuInfo(long id,String name,int age){
        this.id=id;
        this.name=name;
        this.age=age;
    }

    public String toString(){
        return "ID="+id+" name:"+name+" age:"+age;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}