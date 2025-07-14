package kjw.typing.m07.d10;
import java.io.*;

public class FileWriterTestFilter02 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = null;
        BufferedWriter bw = null;

        br = new BufferedReader(new InputStreamReader(System.in));

        bw = new BufferedWriter(new FileWriter("test.txt", true)); // append 모드

        System.out.println("화일에 저장하실 글을 입력하세요.");
        while (true) {
            String str = br.readLine();
            if (str.equals("끝")) {
                break;
            }
            bw.write(str, 0, str.length());
            bw.newLine();
        }
        bw.flush(); // flush를 잊지 말것

        bw.close();
        br.close();
    }
}

