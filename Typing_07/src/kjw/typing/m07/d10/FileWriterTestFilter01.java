package kjw.typing.m07.d10;
import java.io.*;


public class FileWriterTestFilter01 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = null;
        BufferedWriter bw = null;
        String fileName = "";
        String copyFileName = "";

        int readCount = 0; 

        System.out.print("복사할 파일 이름을 입력하세요 : ");
        fileName = new BufferedReader(new InputStreamReader(System.in)).readLine();

        br = new BufferedReader(new FileReader(fileName));
        copyFileName = fileName + "_copy";
        bw = new BufferedWriter(new FileWriter(copyFileName));

        String source = null;
        while ((source = br.readLine()) != null) {
            bw.write(source);
            //bw.newLine();
            readCount++;
        }
        bw.flush(); // 잊지말것

        bw.close();
        br.close();

        System.out.println("============================================");
        System.out.println("===> read횟수 readCount : " + readCount);
        System.out.println("============================================");
    }
}

