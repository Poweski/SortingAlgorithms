package testing;

public class Result {
	private double avgTime;
	private double timeStdDev;
	
	private double avgComparisons;
	private double comparisonsStdDev;

	private double avgSwaps;
	private double swapsStdDev;



	private double avgAddFirst;
	private double addFirstStd;

	private double avgAddLast;
	private double addLastStd;

	private double avgRemoveFirst;
	private double removeFirstStd;

	private double avgRemoveLast;
	private double removeLastStd;


	private boolean srted;
	private boolean stble;
	
	public Result(double avgTime, double timeStdDev, double avgComparisons, double comparisonsStdDev, 
			double avgSwaps, double swapsStdDev, double avgAddF, double addFStdDev, double avgAddL, double addLStdDev,
				  double avgRemoveF, double removeFStdDev, double avgRemoveL, double removeLStdDev,  boolean sorted, boolean stable) {
		this.avgTime = avgTime;
		this.timeStdDev = timeStdDev;
		
		this.avgComparisons = avgComparisons;
		this.comparisonsStdDev = comparisonsStdDev;
		
		this.avgSwaps = avgSwaps;
		this.swapsStdDev = swapsStdDev;



		this.avgAddFirst = avgAddF;
		this.addFirstStd = addFStdDev;

		this.avgAddLast = avgAddL;
		this.addLastStd = addLStdDev;

		this.avgRemoveFirst = avgRemoveF;
		this.removeFirstStd = removeFStdDev;

		this.avgRemoveLast = avgRemoveL;
		this.removeLastStd = removeLStdDev;


		
		this.srted = sorted;
		this.stble = stable;
	}
	
	public double averageTimeInMilliseconds() {
		return avgTime;
	}
	
	public double timeStandardDeviation() {
		return timeStdDev;
	}
	
	public double averageComparisons() {
		return avgComparisons;
	}
	
	public double comparisonsStandardDeviation() {
		return comparisonsStdDev;
	}
	
	public double averageSwaps() {
		return avgSwaps;
	}
	
	public double swapsStandardDeviation() {
		return swapsStdDev;
	}


	public double averageAddFirst() {
		return avgAddFirst;
	}

	public double addFirstStandardDeviation() {
		return addFirstStd;
	}
	public double averageAddLast() {
		return avgAddLast;
	}

	public double addLastStandardDeviation() {
		return addLastStd;
	}
	public double averageRemoveFirst() {
		return avgRemoveFirst;
	}

	public double removeFirstStandardDeviation() {return removeFirstStd;}
	public double averageRemoveLast() {
		return avgRemoveLast;
	}

	public double removeLastStandardDeviation() {return removeLastStd;}


	
	public boolean sorted() {
		return srted;
	}
	
	public boolean stable() {
		return stble;
	}
}
