package testing;

import java.util.List;

import core.SortingAlgorithm;
import measuring.Timer;
import testing.generation.Generator;

public class Tester {
	
	public static <T> RunResult runOnce(SortingAlgorithm<MarkedValue<T>> algorithm, Generator<MarkedValue<T>> generator, int size) {
		algorithm.reset();
		
		List<MarkedValue<T>> list = generator.generate(size);
		Timer timer = new Timer();

		timer.start();
		algorithm.sort(list);
		timer.stop();
		
		return new RunResult(timer.durationMillis(), algorithm.comparisons(), algorithm.swaps(), algorithm.addFirstNumber(),
				algorithm.addLastNumber(), algorithm.removeFirstNumber(), algorithm.removeLastNumber(),
				ListChecker.isSorted(list, algorithm.baseComparator()), ListChecker.isStable(list, algorithm.baseComparator()));
	}
	
	public static <T> Result runNTimes(SortingAlgorithm<MarkedValue<T>> algorithm, Generator<MarkedValue<T>> generator, 
			int size, int repetitions) {
		double averageTime = 0.0;
		double averageTimeSquared = 0.0;
		
		double averageComparisons = 0.0;
		double averageComparisonsSquared = 0.0;
		
		double averageSwaps = 0.0;
		double averageSwapsSquared = 0.0;


		double averageAddF = 0.0;
		double averageAddFSquared = 0.0;

		double averageAddL = 0.0;
		double averageAddLSquared = 0.0;

		double averageRemoveF = 0.0;
		double averageRemoveFSquared = 0.0;

		double averageRemoveL = 0.0;
		double averageRemoveLSquared = 0.0;



		boolean sorted = true;
		boolean stable = true;
		
		for(int n = 1; n <= repetitions; ++n) {
			RunResult result = runOnce(algorithm, generator, size);
			
			averageTime = updatedAverage(averageTime, result.timeInMilliseconds(), n);
			averageTimeSquared = updatedAverage(averageTimeSquared, 
					(double)result.timeInMilliseconds() * (double)result.timeInMilliseconds(), n);
			
			averageComparisons = updatedAverage(averageComparisons, result.comparisons(), n);
			averageComparisonsSquared = updatedAverage(averageComparisonsSquared, 
					(double)result.comparisons() * (double)result.comparisons(), n);

			averageSwaps = updatedAverage(averageSwaps, result.swaps(), n);
			averageSwapsSquared = updatedAverage(averageSwapsSquared, (double)result.swaps() * (double)result.swaps(), n);


			averageAddF = updatedAverage(averageAddF, result.addFirstNumber(), n);
			averageAddFSquared = updatedAverage(averageAddFSquared, (double)result.addFirstNumber() * (double)result.addFirstNumber(), n);

			averageAddL = updatedAverage(averageAddL, result.addLastNumber(), n);
			averageAddLSquared = updatedAverage(averageAddLSquared, (double)result.addLastNumber() * (double)result.addLastNumber(), n);

			averageRemoveF = updatedAverage(averageRemoveF, result.removeFirstNumber(), n);
			averageRemoveFSquared = updatedAverage(averageRemoveFSquared, (double)result.removeFirstNumber() * (double)result.removeFirstNumber(), n);

			averageRemoveL = updatedAverage(averageRemoveL, result.removeLastNumber(), n);
			averageRemoveLSquared = updatedAverage(averageRemoveLSquared, (double)result.removeLastNumber() * (double)result.removeLastNumber(), n);
			
			sorted = sorted && result.sorted();
			stable = stable && result.stable();
		}
		
		return new Result(averageTime, calculateStdDev(averageTime, averageTimeSquared), 
				averageComparisons, calculateStdDev(averageComparisons, averageComparisonsSquared), 
				averageSwaps, calculateStdDev(averageSwaps, averageSwapsSquared),
				averageAddF, calculateStdDev(averageAddF, averageAddFSquared),
				averageAddL, calculateStdDev(averageAddL, averageAddLSquared),
				averageRemoveF, calculateStdDev(averageRemoveF, averageRemoveFSquared),
				averageRemoveL, calculateStdDev(averageRemoveL, averageRemoveLSquared),
				sorted, stable);
	}
	
	private static double updatedAverage(double average, double value, int n) {
		return average + (value - average) / n;
	}
	
	private static double calculateStdDev(double average, double averagedSquares) {
		return Math.sqrt(averagedSquares - (average * average));
	}
	
}
