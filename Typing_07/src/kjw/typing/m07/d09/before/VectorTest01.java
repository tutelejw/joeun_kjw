import java.util.*;

public class VectorTest01 {
    
    //main method
    public static void main(String[] args){
        
        Vector vector = new Vector(10,10);
        
        String s1 = new String("1. 홍");
        vector.add(s1);
        vector.add(new String("2. 동"));
        vector.add("3.님 안녕하세요");
        
        for(int i=0; i<vector.size(); i++){
            Object obj = vector.elementAt(i);
            String s = (String)obj;
            //String s = (String)vector.elementAt(i); // 위의 두 line 과 비교
            System.out.println(s);
            //System.out.println((String)vector.elementAt(i));
        }
        
        System.out.println("\n ==> API 확인 ");
        vector.insertElementAt("4. 길", 1);
        for(int i=0; i<vector.size(); i++){
            System.out.println((String)vector.elementAt(i));
        }
        
        System.out.println("\n ==> API 확인 ");
        vector.setElementAt("5. 홍길순", 3);
        for(int i=0; i<vector.size(); i++){
            System.out.println((String)vector.elementAt(i));
        }
        
        System.out.println("\n ==> API 확인 ");
        vector.removeElementAt(3);
        for(int i=0; i<vector.size(); i++){
            System.out.println((String)vector.elementAt(i));
        }
        
    }//end of main
}//end of class