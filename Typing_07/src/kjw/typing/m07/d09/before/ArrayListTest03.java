import java.util.*;

public class ArrayListTest03 {
    //Main Method
    public static void main(String[] args){

        ArrayList<String> arrayList = new ArrayList<String>(10);

        arrayList.add("1. 홍길동");
        arrayList.add(new String("2. 이순신"));
        arrayList.add("3. 강감찬");
        
        for(String s : arrayList){
            System.out.println(s);
        }

        System.out.println("==============JDK 1.5 추가기능 Generic, Enhanced For Loop 사용");
        for(String value : arrayList){
            System.out.println(value);
        }

        System.out.println("\n ==> API 확인");
        arrayList.add(1, "4. 주몽");
        for(int i=0; i<arrayList.size(); i++){
            System.out.println(arrayList.get(i));
        }

        System.out.println("\n ==> API 확인");
        arrayList.set(2, "5. 유관순");
        for(int i=0; i<arrayList.size(); i++){
            System.out.println(arrayList.get(i));
        }

        System.out.println("\n ==> API 확인");
        arrayList.remove(3);
        for(int i=0; i<arrayList.size(); i++){
            System.out.println(arrayList.get(i));
        }
    } //end of main
} //end of class