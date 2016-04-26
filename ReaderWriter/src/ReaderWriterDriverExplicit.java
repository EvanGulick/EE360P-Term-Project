import org.apache.commons.lang3.time.StopWatch;	


public class ReaderWriterDriverExplicit {
    public static void main(String[] args) {
        ReaderWriterExplicit monitor = new ReaderWriterExplicit();
        Reader2 reader = new Reader2("1", monitor);
        Writer2 writer = new Writer2("1", monitor);
	}
}
class Reader2 implements Runnable {
    private ReaderWriterExplicit M;
    private String value;

    public Reader2(String name, ReaderWriterExplicit c) {
        M = c;
        new Thread(this).start();
    }

    public void run() {
    	StopWatch watch = new StopWatch();
    	watch.start();
        for (int i = 0; i < 1000; i++) {
            M.startRead();
         
            // System.out.println("Reader "+getName()+" is retreiving data...");
            M.endRead();
        }
        watch.stop();
        String result = watch.toString();
        System.out.println("Reader" + result);
    }
}

class Writer2 implements Runnable {
    private ReaderWriterExplicit M;
    private int value;

    public Writer2(String name, ReaderWriterExplicit d) {
        M = d;
        new Thread(this).start();
    }

    public void run() {
       	StopWatch watch2 = new StopWatch();
    	watch2.start();
        for (int j = 0; j < 1000; j++) {
            M.startWrite();
   
            // System.out.println("Writer "+getName()+" is writing data...");
            M.endWrite();
        }
        watch2.stop();
        String result = watch2.toString();
        System.out.println("Writer" + result);

    }
}