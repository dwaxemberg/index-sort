package com.indexsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class IndexSort {

	private static Random random = new Random();
	private static ArrayList<ArrayList<Integer>> sortedList;

	public static void main(String[] args) {
		System.out.println("(size, range)\tIndex\tMerge");
		int size = 1;
		int range = 1;
		for (int i = 0; i < 20; i++) {
			size = size << 1;
			range = 1;
			for (int j = 0; j < 20; j++) {
				range = range << 1;

				Integer[] nums = createArray(size, range);
				Integer[] nums2 = Arrays.copyOf(nums, nums.length);
				int maxValue = maxValue(nums) + 1;
				sortedList = new ArrayList<>(maxValue);
				for (int k = 0; k < maxValue; k++) {
					sortedList.add(new ArrayList<>(size / range));
				}

				long start1 = System.currentTimeMillis();
				sort(nums);
				long time1 = System.currentTimeMillis() - start1;
				long start2 = System.currentTimeMillis();
				Arrays.sort(nums2);
				long time2 = System.currentTimeMillis() - start2;
				System.out.println("(" + size + ", " + range + "), " + time1 + "ms, " + time2 + "ms");
			}
		}
	}

	public static void sort(Integer[] nums) {
		for (int i = 0; i < nums.length; i++) {
			ArrayList<Integer> valList = sortedList.get(nums[i]);
			valList.add(nums[i]);
		}

		int index = 0;
		for (int i = 0; i < sortedList.size(); i++) {
			ArrayList<Integer> valList = sortedList.get(i);
			Iterator<Integer> iterator = valList.iterator();
			while (iterator.hasNext()) {
				nums[index++] = iterator.next();
			}
		}
	}

	private static int maxValue(Integer[] nums) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			max = Math.max(max, nums[i]);
		}
		return max;
	}

	private static Integer[] createArray(int size, int range) {
		Integer[] nums = new Integer[size];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = random.nextInt(range);
		}
		return nums;
	}

}
