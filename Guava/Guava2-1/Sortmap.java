package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Sortmap {
	public static Map<String,Integer> sortMap(Map<String,Integer> oldMap) {  
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldMap.entrySet());  
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
  
        @Override  
        public int compare(Entry<java.lang.String, Integer> arg0,
        		Entry<java.lang.String, Integer> arg1) {  
            return arg1.getValue() - arg0.getValue();  
        	}  
        });  
        Map<String,Integer> newMap = new LinkedHashMap<String,Integer>();  
        for (int i = 0; i < list.size(); i++) {  
            newMap.put(list.get(i).getKey(), list.get(i).getValue());  
        }  
        return newMap;  
    }  

}
