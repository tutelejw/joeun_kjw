package typing.m07.d10;
import java.io.*;

public class InputTest{

    public static void main(String args[]){

    	System.out.println(args[0]);
    	
        InputStream inputStream = System.in;

        System.out.println("입력을 기다립니다.......");
                       
        try{
 
            while(true){
                // 1. java.io 는 지연(block)될 수 있다.
                int i = inputStream.read();
                char c = (char) i;

                System.out.println("입력하신 값: " + c);

                if(c=='x'){
                    inputStream.close();
                    break;
                }
                // 한글입력시 깨진
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
