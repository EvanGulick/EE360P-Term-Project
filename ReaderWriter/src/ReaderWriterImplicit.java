
public class ReaderWriterImplicit {
	PredicateVariable readerCount;
	PredicateVariable writerCount;
	PredicateVariable zero;
	
	static Manager manager = new Manager();
	
	public ReaderWriterImplicit(){
		this.readerCount = new PredicateVariable(0);
		this.writerCount = new PredicateVariable(0);
		this.zero = new PredicateVariable(0);
	}
	
	public void startRead(){
		Condition predicate = new Condition(writerCount, "==", zero);
    	manager.waituntil(predicate);
		readerCount.increment();
	}
	
	public void endRead(){
		readerCount.decrement();
		manager.finishedCS();
	}
	
	public void startWrite(){
		Condition predicate = new Condition(readerCount, writerCount, "==", zero);
    	manager.waituntil(predicate);
		writerCount.set(1);
	}
	
	public void endWrite(){
		writerCount.set(0);
		manager.finishedCS();
	}
}
