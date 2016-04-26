import org.apache.commons.lang3.time.StopWatch;

public class ReaderWriterDriver {
	

	    public static void main(String[] args) {
	        ReaderWriterImplicit monitor = new ReaderWriterImplicit();
	        Reader reader = new Reader("1", monitor);
	        Writer writer = new Writer("1", monitor);
	}
}
class Reader implements Runnable {
    private ReaderWriterImplicit M;
    private String value;

    public Reader(String name, ReaderWriterImplicit c) {
        M = c;
        new Thread(this).start();
    }

    public void run() {
    	StopWatch watch = new StopWatch();
    	watch.start();
        for (int i = 0; i < 1000; i++) {
            M.startRead();

            // System.out.println("Reader "+getName()+" is retreiving data...");
            //System.out.println("Reader is reading " + i);
            M.endRead();
        }
        watch.stop();
        String result = watch.toString();
        System.out.println("Reader" + result);
    }
}

class Writer implements Runnable {
    private ReaderWriterImplicit M;
    private int value;

    public Writer(String name, ReaderWriterImplicit d) {
        M = d;
        new Thread(this).start();
    }

    public void run() {
    	StopWatch watch2 = new StopWatch();
    	watch2.start();
        for (int j = 0; j < 1000; j++) {
            M.startWrite();

            // System.out.println("Writer "+getName()+" is writing data...");
            //System.out.println("Writer is writing " + j);
            M.endWrite();
        }
        watch2.stop();
        String result = watch2.toString();
        System.out.println("Writer" + result);

    }
}