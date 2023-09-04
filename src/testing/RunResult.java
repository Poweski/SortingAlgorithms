package testing;

public class RunResult {
	private long timeMillis;
	
	private long comps;
	private long swps;


	private long addFirstNumber;
	private long addLastNumber;
	private long removeFirstNumber;
	private long removeLastNumber;

	private boolean srted;
	private boolean stble;
	
	public RunResult(long timeMillis, long comparisons, long swaps, long addFirstNumber, long addLastNumber,
					 long removeFirstNumber, long removeLastNumber, boolean sorted, boolean stable) {
		this.timeMillis = timeMillis;
		this.comps = comparisons;
		this.swps = swaps;
		this.addFirstNumber = addFirstNumber;
		this.addLastNumber = addLastNumber;
		this.removeFirstNumber = removeFirstNumber;
		this.removeLastNumber = removeLastNumber;
		this.srted = sorted;
		this.stble = stable;
	}
	
	public long timeInMilliseconds() {
		return timeMillis;
	}
	
	public long comparisons() {
		return comps;
	}
	
	public long swaps() {
		return swps;
	}

	public long addFirstNumber() { return addFirstNumber;}
	public long addLastNumber() { return addLastNumber;}
	public long removeLastNumber() { return removeLastNumber;}
	public long removeFirstNumber() { return removeFirstNumber;}

	public boolean sorted() {
		return srted;
	}
	
	public boolean stable() {
		return stble;
	}
}
