package com.hm707.opt;

public class Car {

	private Insurance insurance;

	public Insurance getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Car{");
		sb.append("insurance=").append(insurance);
		sb.append('}');
		return sb.toString();
	}
}
