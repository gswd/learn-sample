package com.hm707.opt;

public class Person {
	private String name;
	private Car car;

	public Person(String name) {
		this.name = name;

		Insurance insurance = new Insurance();
		insurance.setName("jqx");

		this.car = new Car();
		car.setInsurance(insurance);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Person{");
		sb.append("name='").append(name).append('\'');
		sb.append(", car=").append(car);
		sb.append('}');
		return sb.toString();
	}
}
