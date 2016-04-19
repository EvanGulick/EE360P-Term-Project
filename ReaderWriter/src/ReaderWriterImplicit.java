
public class ReaderWriterImplicit {
	int readerCount;
	int writerCount;
	
	public ReaderWriterImplicit(){
		readerCount = 0;
		writerCount = 0;
	}
	
	public void startRead(){
		while(writerCount == 0){}
		readerCount += 1;
	}
	
	public void endRead(){
		readerCount -= 1;
	}
	
	public void startWrite(){
		while(readerCount == 0 && writerCount == 0){}
		writerCount = 1;
	}
	
	public void endWrite(){
		writerCount = 0;
	}
}
