package test;

import java.util.LinkedHashMap;
import java.util.Map;

public class CacheLRU<K,V> extends LinkedHashMap<K,V>{
	//class LRULinkedHashMap<K,V> extends LinkedHashMap<K,V>{  
	    //���建�������  
	    private int capacity;  
	    private static final long serialVersionUID = 1L;  
	    //�������Ĺ�����     
	    CacheLRU(int capacity){  
	        //����LinkedHashMap�Ĺ��������������²���,����1.5��������  
	        super(capacity+(capacity>>1),0.75f,true);  
	        //����ָ���Ļ����������  
	        this.capacity=capacity;  
	    }  
	    //ʵ��LRU�Ĺؼ����������map�����Ԫ�ظ��������˻��������������ɾ������Ķ���Ԫ��  
	    @Override  
	    public boolean removeEldestEntry(Map.Entry<K, V> eldest){     
	        return size()>capacity;  
	    }    
	//}  
}
