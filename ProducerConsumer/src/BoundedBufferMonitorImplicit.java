
public class BoundedBufferMonitorImplicit {

    //final int size = 10;
	PredicateVariable size;
    PredicateVariable count;
    PredicateVariable zero;
    Object[] buffer;
    int inBuf;
    int outBuf;
    
    static Manager manager = new Manager();
    
    public BoundedBufferMonitorImplicit() {
    	this.size = new PredicateVariable(10);
        this.count = new PredicateVariable(0);
        this.zero = new PredicateVariable(0);
        this.buffer = new Object[size.get()];
        this.inBuf = 0;
        this.outBuf = 0;
    }
    
    public void deposit(Object value) {
    	Condition predicate = new Condition(count, "<", size);
    	manager.waituntil(predicate);

        buffer[inBuf] = value;
        inBuf = (inBuf + 1) % size.get();
        count.increment();
        
        manager.finishedCS();
    }
    
    public Object fetch() {
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
