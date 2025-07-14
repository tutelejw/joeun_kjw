package typing.m07.d10;

import java.io.*;

public class WriterTest{

    public static void main(String args[]){
        try{
            InputStream inputStream = System.in;
            Reader reader = new InputStreamReader(inputStream);

            OutputStream outputStream = System.out;
            Writer writer = new OutputStreamWriter(outputStream);

            System.out.println("입력을 기다립니다....");

            while(true){
                int i = reader.read();
                writer.write(i);
                //writer.flush();

                if( (char)i == 'x' ){
                    break;
                }
            }
            //writer.flush();
            
            reader.close();
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
