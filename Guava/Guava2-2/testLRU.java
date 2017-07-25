package test;

import java.util.Map;

public class testLRU {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//指定缓存最大容量为4  
		Map<Integer,Integer> map=new CacheLRU<Integer,Integer>(4);  
	    map.put(9,3);  
	    map.put(7,4);  
	    map.put(5,9);  
	    map.put(3,4);  
	    map.put(6,6);  
	    //总共put了5个元素，超过了指定的缓存最大容量  
	    //遍历结果  
	    System.out.println(map);
	}

}
