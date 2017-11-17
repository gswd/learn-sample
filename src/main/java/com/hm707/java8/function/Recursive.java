package com.hm707.java8.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 递归
 * 给定一个列表 List<value> ，比如{1, 4, 9}，构造一个 List<List<Integer>> ，它的成员都是类表{1, 4, 9}的子集。
 * {1, 4, 9}的子集是{1, 4, 9}、{1, 4}、{1, 9}、{4, 9}、{1}、{4}、{9}以及{}。
 */
public class Recursive {
	public static void main(String[] args) {

		List<Integer> integerList = Arrays.asList(1, 4, 9);

		List<List<Integer>> subSet = subsets(integerList);

		subSet.forEach(System.out::println);

	}

	private static List<List<Integer>> subsets(List<Integer> list) {
		if (list.isEmpty()) {
			List<List<Integer>> ans = new ArrayList<>();
			ans.add(Collections.emptyList());
			return ans;
		}

		Integer first = list.get(0);
		List<Integer> rset = list.subList(1, list.size());

		List<List<Integer>> subans = subsets(rset);
		List<List<Integer>> subans2 = insertAll(first, subans);
		return concat(subans, subans2);
	}

	private static List<List<Integer>> insertAll(Integer first, List<List<Integer>> lists) {
		List<List<Integer>> result = new ArrayList<>();
		for (List<Integer> list : lists) {
			List<Integer> copyList = new ArrayList<>();
			copyList.add(first);
			copyList.addAll(list);
			result.add(copyList);
		}
		return result;
	}

	private static List<List<Integer>> concat(List<List<Integer>> a, List<List<Integer>> b) {
		List<List<Integer>> r = new ArrayList<>(a);
		r.addAll(b);
		return r;
	}
}
