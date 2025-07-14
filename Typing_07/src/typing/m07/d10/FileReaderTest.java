package typing.m07.d10;

import java.io.*;

public class FileReaderTest{

    ///main method
    public static void main(String[] args) {

        FileReader fr = null;
        int readCount=0;

        try{
            fr = new FileReader(args[0]);
            while(true){
                int i = fr.read();
                if(i == -1){ // i=-1의 의미는==> API확인할 것.
                    break;
                }
                char c = (char)i;
                System.out.print(c);

                readCount++;
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e1){
            e1.printStackTrace();
        }finally{
            System.out.println("\n\n===============================");
            System.out.println("===> read횟수 readCount : "+readCount);
            System.out.println("===============================");
            try {
                fr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
