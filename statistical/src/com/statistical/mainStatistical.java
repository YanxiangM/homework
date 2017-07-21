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
		String path=JOptionPane.showInputDialog(null,"请输入路径（如：D:\\test）:");
		//System.out.println("循环文件的次数: "+path);
		File folder = new File(path);//要统计的源目录路径  
        String input="";
        recur(folder);//递归该目录，统计其中代码行数    
//        System.out.println("循环文件的次数: "+a);
//        System.out.println("数字: "+num); 
//        System.out.println("字母: "+letter); 
//        System.out.println("汉字: "+chinese); 
//        System.out.println("空格: "+blank); 
//        System.out.println("行数: "+linenum);
        Pattern p = Pattern.compile("\\d");
        
        input="数字: "+num+"个\r\n"+"字母: "+letter+"个\r\n"+"汉字: "+chinese+"个\r\n"+"空格: "+blank+"个\r\n"+"行数: "+linenum+"个\r\n\r\n";
        Iterator it = numAndLetter.keySet().iterator();  
        while(it.hasNext()){  
             String key;     
             int value;     
             key=it.next().toString();     
             value=numAndLetter.get(key);
             Matcher m = p.matcher(key);
             if(m.matches()) {
            	 input=input+"数字"+key+":"+value+"个\r\n";
             }else {
            	 input=input+"字母"+key+":"+value+"个\r\n";
             }
             //System.out.println(key+"--"+value);     
        }  
        white2file(input);
        System.out.println("成功执行！"); 
	}
	/**
	 * 将结果写入到一个文件
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
	 * 循环路径下的每一个文件
	 */
	public static void recur(File file)  
    {  
        File[] files = file.listFiles();  
        for(int i=0; i<files.length; i++)  
        {  
            //若源文件是目录，则递归  
            if(files[i].isDirectory() == true)  
            {  
                recur(files[i]);  
            }  
            //若源文件是普通文件且为java源文件，则逐行分析之  匹配文件名时用到了正则表达式  
            else if(files[i].isFile() == true)  
            {  
                //parse(files[i]);
            	statisticFile(files[i]);
            	a++;
            }  
        }  
    }  
	/**
	 * 循环每一个文件
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
     * 判断有多少数字
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
     * 判断有多少字母
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
     * 判断有多少中文
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
     * 判断有多少空格
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
