package test;

import java.util.Map;

public class testLRU {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ָ�������������Ϊ4  
		Map<Integer,Integer> map=new CacheLRU<Integer,Integer>(4);  
	    map.put(9,3);  
	    map.put(7,4);  
	    map.put(5,9);  
	    map.put(3,4);  
	    map.put(6,6);  
	    //�ܹ�put��5��Ԫ�أ�������ָ���Ļ����������  
	    //�������  
	    System.out.println(map);
	}

}
