package com.hm707.opt;

import java.util.Optional;

public class TestOpt {

	public static void main(String[] args) {
		Person person = new Person("jerry");

		Optional<Person> personOptional = Optional.of(person);
		Optional<Car> carOptional = personOptional.map(Person::getCar);

		//carOptional.ifPresent(System.out::print);

		Optional<String> name = personOptional.map(Person::getCar).map(Car::getInsurance).map(Insurance::getName);


	}
}
