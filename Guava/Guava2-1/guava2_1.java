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
             String pathname = "access.log"; //���ļ�
             File filename = new File(pathname);  
             InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // ����һ������������reader  
             BufferedReader br = new BufferedReader(reader); 
             String line = "";  
             while ((line = br.readLine())!= null) {
            	 //ͳ��������
            	 countLine++;
            	 //ͳ��������Ƶ����10��HTTP�ӿڣ��Լ�����������
            	 if(conutHttpTop10.containsKey(line.trim())){
            		 conutHttpTop10.put(line.trim(), conutHttpTop10.get(line.trim())+1);
            	 }else{
            		 conutHttpTop10.put(line.trim(), 1);
            	 } 
            	 line.trim();
            	 //ͳ��Post��Get���������
            	 String [] arr = line.split("\\s+");
            	 if(arr[0].equals("GET")){
            		 numGet++;
            	 }else{
            		 numPost++;
            	 }
            	 //��AAA���࣬������������URL�����
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
             //���д���ļ�
             File writename = new File("output.txt"); // ���·�������û����Ҫ����һ���µ�output��txt�ļ�  
             BufferedWriter out = new BufferedWriter(new FileWriter(writename));
             out.write("��������Ϊ��"+countLine+"\r\n");
             out.write("������Ƶ����10��HTTP�ӿ��Լ������������£�\r\n");
             conutHttpTop10=Sortmap.sortMap(conutHttpTop10);
             int i=0;
        	 for (String key : conutHttpTop10.keySet()) {  
        		  i++;
        		  if(i==11)break;
        		  out.write("�ӿ�"+i+"��"+key+"-----"+"������"+conutHttpTop10.get(key)+"\r\n");
        		} 
        	 out.write("POST����������Ϊ��"+numPost+"\r\n");
        	 out.write("GET����������Ϊ��"+numGet+"\r\n");
        	 out.write("��AAA���࣬��������µ�URL�ֱ����£�"+"\r\n");
        	 for (String key : conutClass.keySet()) {
        		 out.write(key+"\r\n");
        		 for (String str : conutClass.get(key)) {  
        			 out.write("      ---->"+str+"\r\n");  
        		}  
       		} 
             out.flush(); // �ѻ���������ѹ���ļ�  
             out.close(); // ���ǵùر��ļ�
             System.out.println("����ִ����ϣ�");

         } catch (Exception e) {  
             e.printStackTrace();  
         }    
	} 
}
