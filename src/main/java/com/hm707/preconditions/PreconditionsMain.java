package com.hm707.preconditions;

import static com.google.common.base.Preconditions.*;

/**
 * 1. 要静态导入
 * 2. 异常信息可以使用printf风格，即可以使用s%作为占位符，因为这个特性，所以推荐使用checkNotNull而不是使用JDK7中引入的Objects.requireNonNull
 * 3. 在编码时，如果某个值有多重的前置条件，建议把它们放到不同的行，这样有助于在调试时定位。
 *    此外，把每个前置条件放到不同的行，也可以帮助你编写清晰和有用的错误消息。
 */
public class PreconditionsMain {
	public static void main(String[] args) {
		PreconditionsMain testMain = new PreconditionsMain();
		System.out.println(testMain.sqrt(10));
		System.out.println(testMain.sum(10, 19));
		System.out.println(testMain.getValue(0));
		testMain.testState(1);

	}

	public double sqrt(double input) throws IllegalArgumentException {
		checkArgument(input > 0.0, "Illegal Argument passed: Negative value %s == %S.", input, input);
		return Math.sqrt(input);
	}

	public int sum(Integer a, Integer b) {

		//checkNotNull会返回传入的参数值，所以可以直接在行内参与运算
		//比如 checkNotNull(a) + checkNotNull(b)

		a = checkNotNull(a,
			"Illegal Argument passed: First parameter is Null.");
		b = checkNotNull(b,
			"Illegal Argument passed: Second parameter is Null.");
		return a + b;
	}

	public int getValue(int input) {
		int[] data = {1, 2, 3, 4, 5};
		checkElementIndex(input, data.length, "Illegal Argument passed: Invalid index.");
		return 0;
	}

	public String subStr(String str, int beginIndex, int endIndex) {
		checkNotNull(str);
		checkArgument(beginIndex <= endIndex);

		checkPositionIndex(beginIndex, str.length());

		return str.substring(beginIndex, endIndex);
	}

	public void testState(int state) {
		checkState(state == 0, "the status is [%s],it's fail. ", state);
	}
}
