
public class BoundedBufferMonitorImplicit {

    //final int size = 10;
	final PredicateVariable size = new PredicateVariable(10);
    PredicateVariable count = new PredicateVariable(0);
    PredicateVariable zero = new PredicateVariable(0);
    Object[] buffer = new Object[size.get()];
    int inBuf = 0, outBuf = 0;//, count = 0;
    
    Manager manager = new Manager();
    
    public synchronized void deposit(Object value) {
    	Condition predicate = new Condition(count, "<", size);
    	manager.waituntil(predicate);

        buffer[inBuf] = value;
        inBuf = (inBuf + 1) % size.get();
        count.increment();
        
        manager.finishedCS();
    }
    public synchronized Object fetch() {
        Object value;
        Condition predicate = new Condition(count, ">", zero);
    	manager.waituntil(predicate);

        value = buffer[outBuf];
        outBuf = (outBuf + 1) % size.get();
        count.decrement();
        
        manager.finishedCS();
        return value;
    }
}
