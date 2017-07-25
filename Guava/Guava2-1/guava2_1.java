package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class guava2_1 {
	public static void main(String[] args){
		int countLine=0;
		int numGet=0;
		int numPost=0;
		Map<String,Integer> conutHttpTop10=new LinkedHashMap<String,Integer>();
		Map<String,Set<String>> conutClass=new HashMap<String,Set<String>>();
		 try {   
             String pathname = "access.log"; //读文件
             File filename = new File(pathname);  
             InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // 建立一个输入流对象reader  
             BufferedReader br = new BufferedReader(reader); 
             String line = "";  
             while ((line = br.readLine())!= null) {
            	 //统计总量；
            	 countLine++;
            	 //统计请求最频繁的10个HTTP接口，以及请求总数。
            	 if(conutHttpTop10.containsKey(line.trim())){
            		 conutHttpTop10.put(line.trim(), conutHttpTop10.get(line.trim())+1);
            	 }else{
            		 conutHttpTop10.put(line.trim(), 1);
            	 } 
            	 line.trim();
            	 //统计Post和Get请求个数。
            	 String [] arr = line.split("\\s+");
            	 if(arr[0].equals("GET")){
            		 numGet++;
            	 }else{
            		 numPost++;
            	 }
            	 //按AAA分类，输出各个类别下URL的类别。
            	 String[] arr1 = arr[1].split("/");
            	 if(conutClass.containsKey(arr1[1])){
            		 Set<String> temp=new HashSet<String>();
        			 temp=conutClass.get(arr1[1]);
        			 String linkStr="";
        			 for(int i=2;i<arr1.length;i++){
        				 linkStr+="/"+arr1[i];
        			 }
        			 if(!temp.contains(linkStr)){
        				 temp.add(linkStr);
            			 conutClass.put(arr1[1],temp);
        			 }
            	 }else{
            		 Set<String> temp=new HashSet<String>();
        			 String linkStr="";
        			 for(int i=2;i<arr1.length;i++){
        				 linkStr+=arr1[i];
        			 }
        			 temp.add(linkStr);
        			 conutClass.put(arr1[1],temp);
            	 } 
             }
             //结果写入文件
             File writename = new File("output.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件  
             BufferedWriter out = new BufferedWriter(new FileWriter(writename));
             out.write("请求总量为："+countLine+"\r\n");
             out.write("请求最频繁的10个HTTP接口以及请求总数如下：\r\n");
             conutHttpTop10=Sortmap.sortMap(conutHttpTop10);
             int i=0;
        	 for (String key : conutHttpTop10.keySet()) {  
        		  i++;
        		  if(i==11)break;
        		  out.write("接口"+i+"："+key+"-----"+"总数："+conutHttpTop10.get(key)+"\r\n");
        		} 
        	 out.write("POST的请求总数为："+numPost+"\r\n");
        	 out.write("GET的请求总数为："+numGet+"\r\n");
        	 out.write("按AAA分类，各个类别下的URL分别如下："+"\r\n");
        	 for (String key : conutClass.keySet()) {
        		 out.write(key+"\r\n");
        		 for (String str : conutClass.get(key)) {  
        			 out.write("      ---->"+str+"\r\n");  
        		}  
       		} 
             out.flush(); // 把缓存区内容压入文件  
             out.close(); // 最后记得关闭文件
             System.out.println("程序执行完毕！");

         } catch (Exception e) {  
             e.printStackTrace();  
         }    
	} 
}
