package kjw.typing.m07.d09.before;
import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {

    //Main Method
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();

        String value = new String("1: 홍길동");
        queue.offer(value);
        queue.offer(new String("2: 이순신"));
        queue.offer("3: 주몽");

        String str1 = (String)queue.poll();
        System.out.println(str1);

        String str2 = (String)queue.peek(); //===> poll(), peek()의 차이점은...
        System.out.println(str2);

        while( queue.peek() != null ){
            String str3 = (String)queue.poll();
            System.out.println(str3);
        }
    }
} //end of class