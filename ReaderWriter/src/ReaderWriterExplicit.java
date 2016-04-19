import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReaderWriterExplicit {
	int readerCount;
	int writerCount;
	int writerSleeping;
	private final Lock lock = new ReentrantLock();
	
	private final Condition readerWait = lock.newCondition();
	private final Condition writerWait = lock.newCondition();
	
	public ReaderWriterExplicit(){
		readerCount = 0;
		writerCount = 0;
		writerSleeping = 0;
	}
	
	public void startRead(){
		if(writerCount != 0 || writerSleeping != 0){
			try {
				readerWait.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		readerCount += 1;
		readerWait.signalAll();
	}
	
	public void endRead(){
		readerCount -= 1;
		if(readerCount == 0){
			writerWait.signal();
		}
	}
	
	public void startWrite(){
		if(readerCount != 0 || writerCount != 0){
			try {
				writerSleeping += 1;
				writerWait.await();
				writerSleeping -= 1;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		writerCount = 1;
	}
	
	public void endWrite(){
		writerCount = 0;
		if(writerSleeping != 0){
			writerWait.signal();
		}
		else{
			readerWait.signalAll();
		}
	}
}
