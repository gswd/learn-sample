package com.hm707;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by LH on 2017/11/9.
 */
public class TestUrlEncode {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String url1 = "http://dev-ckms.ncloud.com/v1/keys?pageNo=1&authToken=HDPgbWxUfWvT+B4rnMWEuUS2OHk0AGeEUUU46l0xFD7g+zBQG2vzpsFbIHE39cpa56pWYtyr4tB+i51tF0giwsQ/fF3usdWiaBDBsSgO2iMj4HNJKb71xcZLI97T7E5FYEhpWmQMEQAhzepu1LD5aTf+i8qylNrKh2++t4tMyQedBWg1qm0QP449nG9KU0BqP4tmkdmLS3O1j/OfyODu3gLuAks5NSnwkyf6hTVE1N29+IoTq1F9YpD01ucGzhE9UXhkqdk8xehF6zcF7ZOZKxTyvktXSGJkiv8ylcDErm0Q5GgrhJyBb09Huuyi2jk4HnLD5cYo/XScjFD9lZNv/6Y9V/6iYXTj6JuMIgzMl0+2UZqSI/zMcxjbHycgbOqqw6HsjmSm+dVeAUsCCqmj+lsW51BDDZOSEtNwZVlBSe0FIA8UCAja5AFhrELk6B1lJL2ukQBglItNAWjQzZtb4Q==";

		String decode1 = URLEncoder.encode(url1, "UTF-8");
		System.out.println(decode1);

		String url2 = "EHOQpL9sadPImQHnGihXF8pfefzWDcdi0oGqT/1SbNJnF3mG1DkLMQng+BHU2O8WonYJ9dT4WYW0VJPkqUqdDqJPClOplxxO6b9yj0UpokGQYThzfSLZAP6OMcknJdd9XI0rCeRy6YbeksAtIDRaAokLVcgutlhAjDSlpgI9Jsopya8toV1y3JpSPdFfQD/HsAAnRVgZJniEH2PNSpgL/tOIlFGySJXknKY0klqbcbvdeBdrXKUe/2ikeK4I/YlblWQ0s5VtvqarcAk1wquUdVDgk91yv9rBNsIihEDfL3GfspHV2qVASCDdLMRDcMC1kdrY4MSkoHJxB2dkB1G6SvPx3dTarO2drJ4Rl9hlt5aHk3wR1vrTI8HzyAeSh55LqKLIzGw+tFzOEKTHCpsdS4ckzs8Jr62+sQyyNnowPdvjIBl4Vot5oaEZkxP8tzwSxSvCJrgyrmo+ldRWnlAQ6A==";

		String decode2 = URLEncoder.encode(url2, "UTF-8");
		System.out.println(decode2);

	}
}
