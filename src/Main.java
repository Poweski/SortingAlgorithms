import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import core.SortingAlgorithm;
import pivotSelectors.FirstPivotSelector;
import pivotSelectors.RandomPivotSelector;
import testing.*;
import testing.comparators.*;
import testing.generation.*;
import testing.generation.conversion.*;

public class Main {

	public static void main(String[] args) throws IOException {


		Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<>(new IntegerComparator());

		ArrayList<Generator<MarkedValue<Integer>>> generators = getListOfGenerators();
		ArrayList<SortingAlgorithm<MarkedValue<Integer>>> algorithms = getListOfAlgorithms(markedComparator);

		String[] collectionArray = new String[]{"Table", "LinkedList"};
		int[] sizeArray = new int[]{ 10, 50, 100, 200, 500, 1000, 1400, 1600, 1800, 2000, 2200, 2400, 2600, 2800, 3000 };
		int[] sizeArray2 = new int[]{ 10, 100, 500, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000, 12000, 15000 };

		for (SortingAlgorithm<MarkedValue<Integer>> algorithm : algorithms) {
			for (Generator<MarkedValue<Integer>> generator : generators) {
				for (String collection : collectionArray) {

					int[] size = sizeArray2;
					if (generators.indexOf(generator) == 1 || generators.indexOf(generator) == 2)
						size = sizeArray;

					String generatorName = "random";
					if (generators.indexOf(generator) % 4 == 1) generatorName = "ordered";
					else if (generators.indexOf(generator) % 4 == 2) generatorName = "reversed";
					else if (generators.indexOf(generator) % 4 == 3) generatorName = "shuffled";

					String algorithmName = "MergeSort";
					if (algorithms.indexOf(algorithm) % 3 == 1) algorithmName = "QuickSortRandomPivot";
					else if (algorithms.indexOf(algorithm) % 3 == 2) algorithmName = "QuickSortFirstPivot";

					ArrayList<Result> results;
					if (collection.indexOf(collection) == 0)
						results = getResultsTable(algorithm, algorithmName, generator, generatorName, size);
					else
						results = getResultsLinkedList(algorithm, algorithmName, generator, generatorName, size);

					File file = new File(algorithmName + "_" + generatorName + "_" + collection + ".csv");
					BufferedWriter writer = new BufferedWriter(new FileWriter(file));
					writer.write("Size;Time;Deviation;Comparisons;Deviation;AddFirst;Deviation;" +
							"AddLast;Deviation;RemoveFirst;Deviation;RemoveLast;Deviation;isStable\n");

					int i = 0;
					for (Result result : results) {
						String str = size[i]
								+ "; " + result.averageTimeInMilliseconds()
								+ "; " + result.timeStandardDeviation()
								+ "; " + result.averageComparisons()
								+ "; " + result.comparisonsStandardDeviation()
								+ "; " + result.averageAddFirst()
								+ "; " + result.addFirstStandardDeviation()
								+ "; " + result.averageAddLast()
								+ "; " + result.addLastStandardDeviation()
								+ "; " + result.averageRemoveFirst()
								+ "; " + result.removeFirstStandardDeviation()
								+ "; " + result.averageRemoveLast()
								+ "; " + result.removeLastStandardDeviation()
								+ "; " + result.stable() + "\n";
						i++;
						writer.write(str.replace('.', ','));

						if (!result.sorted()) {
							System.out.println("NOT SORTED!");
							break;
						}
					}
					writer.close();
				}
			}
		}
	}

	private static ArrayList<SortingAlgorithm<MarkedValue<Integer>>> getListOfAlgorithms
			(Comparator<MarkedValue<Integer>> markedComparator) {

		ArrayList<SortingAlgorithm<MarkedValue<Integer>>> algorithms = new ArrayList<>();

		algorithms.add(new MergeSort<>(markedComparator));
		algorithms.add(new QuickSort<>(markedComparator, new RandomPivotSelector<>()));
		algorithms.add(new QuickSort<>(markedComparator, new FirstPivotSelector<>()));

		return algorithms;
	}

	private static ArrayList<Generator<MarkedValue<Integer>>> getListOfGenerators() {

		ArrayList<Generator<MarkedValue<Integer>>> generators = new ArrayList<>();

		generators.add(new MarkingGenerator<>(new RandomIntegerArrayGenerator(1000)));
		generators.add(new MarkingGenerator<>(new OrderedIntegerArrayGenerator()));
		generators.add(new MarkingGenerator<>(new ReversedIntegerArrayGenerator()));
		generators.add(new MarkingGenerator<>(new ShuffledIntegerArrayGenerator()));

		return generators;
	}

	private static ArrayList<Result> getResultsTable(SortingAlgorithm<MarkedValue<Integer>> algorithm,
													 String algorithmName, Generator<MarkedValue<Integer>> generator,
													 String generatorName, int[] sizeArray) {

		ArrayList<Result> results = new ArrayList<>(sizeArray.length);

		for (int index : sizeArray) {
			Result result = Tester.runNTimes(algorithm, generator, index, 20);
			results.add(result);
			printResults(result,algorithmName,generatorName,index);
		}

		return results;
	}

	private static ArrayList<Result> getResultsLinkedList(SortingAlgorithm<MarkedValue<Integer>> algorithm,
													 String algorithmName, Generator<MarkedValue<Integer>> generator,
													 String generatorName, int[] sizeArray) {

		ArrayList<Result> results = new ArrayList<>(sizeArray.length);

		for (int index : sizeArray) {
			Result result = Tester.runNTimes(algorithm, new LinkedListGenerator<>(generator), index, 20);
			results.add(result);
			printResults(result,algorithmName,generatorName,index);
		}

		return results;
	}

	private static void printResults(Result result, String algorithmName, String generatorName, int size) {
		System.out.println("\n" + algorithmName + " " + generatorName + " " + size);
		printStatistic("Time [ms]", result.averageTimeInMilliseconds(), result.timeStandardDeviation());
		printStatistic("Comparisons", result.averageComparisons(), result.comparisonsStandardDeviation());
		printStatistic("AddFirst", result.averageAddFirst(), result.addFirstStandardDeviation());
		printStatistic("AddLast", result.averageAddLast(), result.addLastStandardDeviation());
		printStatistic("RemoveFirst", result.averageRemoveFirst(), result.removeFirstStandardDeviation());
		printStatistic("RemoveLast", result.averageRemoveLast(), result.removeLastStandardDeviation());
		System.out.println("Always sorted: " + result.sorted());
		System.out.println("Always stable: " + result.stable());
	}

	private static void printStatistic(String label, double average, double stdDev) {
		System.out.println(label + ": " + double2String(average) + " +- " + double2String(stdDev));
	}

	private static String double2String(double value) {
		return String.format("%.12f", value);
	}
}