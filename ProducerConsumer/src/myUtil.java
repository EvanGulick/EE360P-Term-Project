import java.util.*;
public class myUtil {

    public static void myWait(Object obj) {
        try {
            obj.wait();
        } catch (InterruptedException e) {
        }
    }

    public static void mySleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

}
