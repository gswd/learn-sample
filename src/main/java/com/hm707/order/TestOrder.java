package com.hm707.order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestOrder {

	public static void main(String[] args) {
		List<Person> personList = new ArrayList<>();

		personList.add(new Person(33, "tom"));
		personList.add(new Person(54, "jerry"));
		personList.add(new Person(19, "cat"));
		personList.add(new Person(33, "lucy"));
		personList.add(new Person(-10, "erick"));


		Comparator<Person> cmp = Comparator.comparing(Person :: getAge).thenComparing(Person::getName);

		Predicate<Person> rightAge = p -> p.getAge() > 0;

		System.out.println(personList.stream().filter(rightAge.and(p -> p.getName().length() > 3)).sorted(cmp).collect(Collectors.toList()));

		System.out.println(personList);

		Comparator.reverseOrder();

		personList.stream().collect(Collectors.mapping(p -> p.getName(), Collectors.toSet())).forEach(System.out::println);

	}
}
