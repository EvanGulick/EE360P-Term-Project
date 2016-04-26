public class ReaderWriterExplicit{

	  private int readers       = 0;
	  private int writers       = 0;
	  private int writeRequests = 0;

	  public synchronized void startRead(){
	    while(writers > 0 || writeRequests > 0){
	      try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	    readers++;
	  }

	  public synchronized void endRead(){
	    readers--;
	    notifyAll();
	  }

	  public synchronized void startWrite(){
	    writeRequests++;

	    while(readers > 0 || writers > 0){
	      try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	    writeRequests--;
	    writers++;
	  }

	  public synchronized void endWrite(){
	    writers--;
	    notifyAll();
	  }
	}