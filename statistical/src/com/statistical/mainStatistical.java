package com.statistical;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class mainStatistical {

	public static int a=0;
	static int num=0;
	static int letter=0;
	static int chinese=0;
	static int blank=0;
	static int linenum=0;
	static Map<String,Integer> numAndLetter=new TreeMap<String,Integer>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path=JOptionPane.showInputDialog(null,"������·�����磺D:\\test��:");
		//System.out.println("ѭ���ļ��Ĵ���: "+path);
		File folder = new File(path);//Ҫͳ�Ƶ�ԴĿ¼·��  
        String input="";
        recur(folder);//�ݹ��Ŀ¼��ͳ�����д�������    
//        System.out.println("ѭ���ļ��Ĵ���: "+a);
//        System.out.println("����: "+num); 
//        System.out.println("��ĸ: "+letter); 
//        System.out.println("����: "+chinese); 
//        System.out.println("�ո�: "+blank); 
//        System.out.println("����: "+linenum);
        Pattern p = Pattern.compile("\\d");
        
        input="����: "+num+"��\r\n"+"��ĸ: "+letter+"��\r\n"+"����: "+chinese+"��\r\n"+"�ո�: "+blank+"��\r\n"+"����: "+linenum+"��\r\n\r\n";
        Iterator it = numAndLetter.keySet().iterator();  
        while(it.hasNext()){  
             String key;     
             int value;     
             key=it.next().toString();     
             value=numAndLetter.get(key);
             Matcher m = p.matcher(key);
             if(m.matches()) {
            	 input=input+"����"+key+":"+value+"��\r\n";
             }else {
            	 input=input+"��ĸ"+key+":"+value+"��\r\n";
             }
             //System.out.println(key+"--"+value);     
        }  
        white2file(input);
        System.out.println("�ɹ�ִ�У�"); 
	}
	/**
	 * �����д�뵽һ���ļ�
	 */
	public static void white2file(String str)  
    {  
		
		try {
			//FileOutputStream out = new FileOutputStream(newFile);
			FileWriter newFile = new FileWriter("output.txt");
			newFile.write(str);
	        newFile.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
    }  
	/**
	 * ѭ��·���µ�ÿһ���ļ�
	 */
	public static void recur(File file)  
    {  
        File[] files = file.listFiles();  
        for(int i=0; i<files.length; i++)  
        {  
            //��Դ�ļ���Ŀ¼����ݹ�  
            if(files[i].isDirectory() == true)  
            {  
                recur(files[i]);  
            }  
            //��Դ�ļ�����ͨ�ļ���ΪjavaԴ�ļ��������з���֮  ƥ���ļ���ʱ�õ���������ʽ  
            else if(files[i].isFile() == true)  
            {  
                //parse(files[i]);
            	statisticFile(files[i]);
            	a++;
            }  
        }  
    }  
	/**
	 * ѭ��ÿһ���ļ�
	 */
	public static void statisticFile(File file)
    {  
        StringBuffer sb = new StringBuffer();
        String line = null;
        try{
        	BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                linenum++;
            }
            reader.close();
            num=countNumber(sb.toString());
            letter=countLetter(sb.toString());
            chinese=countChinese(sb.toString());
            blank=countBlank(sb.toString());
            //System.out.println(sb);
         }catch (UnsupportedEncodingException e){
             e.printStackTrace();
         }catch (IOException e) {
             e.printStackTrace();
         }
    }
	/**
     * �ж��ж�������
     */
    public static int countNumber(String str){
        int count = 0;
        Pattern p = Pattern.compile("\\d");
        Matcher m = p.matcher(str);
        while(m.find()){
        	//System.out.println("m de zhi : "+m.group(0));
        	if(numAndLetter.containsKey(m.group(0))) {
        		int k=numAndLetter.get(m.group(0))+1;
        		numAndLetter.put(m.group(0), k);
        	}else {
        		numAndLetter.put(m.group(0), 1);
        	}
            count++;
        }
        return count;
    };

    /**
     * �ж��ж�����ĸ
     */
    public static int countLetter(String str) {
        int count = 0;
        Pattern p = Pattern.compile("[a-zA-Z]");
        Matcher m = p.matcher(str);
        while(m.find()){
        	//System.out.println("m de zhi : "+m.group(0));
        	if(numAndLetter.containsKey(m.group(0))) {
        		int k=numAndLetter.get(m.group(0))+1;
        		numAndLetter.put(m.group(0), k);
        	}else {
        		numAndLetter.put(m.group(0), 1);
        	}
            count++;
        }
        return count;
    }

    /**
     * �ж��ж�������
     */
    public static int countChinese(String str) {
        int count = 0;
        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher m = p.matcher(str);
        while(m.find()){
            count++;
        }
        return count;
    }
    /**
     * �ж��ж��ٿո�
     */
    public static int countBlank(String str) {
        int count = 0;
        Pattern p = Pattern.compile("\\s");
        Matcher m = p.matcher(str);
        while(m.find()){
            count++;
        }
        return count;
    }

}
