package com.hm707.parse.db;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.common.io.Files;

public class RestoreAB {

	private List<String> sqlLine = null;

	private Map<String, String> cache = new ConcurrentHashMap<>();

	private void loadSql(){
		URL url = ClassLoader.getSystemResource("sql");
		File file = new File(url.getPath());

		try {
			sqlLine = Files.readLines(file, Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public RestoreAB() {
		loadSql();
	}



	private Optional<String> requestAndCheck(String abWord){
		String abWordLower = abWord.toLowerCase();
		if(cache.containsKey(abWordLower)){

			return Optional.of(cache.get(abWordLower));
		}

		Map<String, String> params = new HashMap<>();
		params.put("abbreviation", abWord);
		params.put("currentPage", "1");


		//String responseMsg = requester.doPost("http://dbip2.navercorp.com/standardWordList.nhn", params);

		Map<String, String> cookie = new HashMap<>();
		cookie.put("id_login_rememberId", "uncheck");
		cookie.put("UserLocale", "zh-cn");
		cookie.put("language", "en_US");
		cookie.put("WORKS_USER_ID", "44235001");
		cookie.put("AR170814000921", "view");
		cookie.put("AR170814000926", "view");
		cookie.put("nssTok", "s89O3oePeYYfriRTZGEmkq6Wht6nHZMSk0u9OLaRwxAuYNlFGPpDLfg4giXFJHUBjW9lUxkBhcrQzhSrka6T9Pc3qi-U5oepM2Qj25Yjcey6E1E9ie8tvMi0YRyTNg7A");
		cookie.put("0eed9a19-7fe5-495f-9fa8-4d29d4d68c7b", "521c21791faa4a90ed0d309d49f81a2176042ac7ff242e76");
		cookie.put("a59223ed-a243-472e-bcbd-060ebf0d9c61", "521c21791faa4a90ed0d309d49f81a2176042ac7ff242e76");
		cookie.put("c6682783-84c2-492f-9a10-a03d3968bdd1", "2d80ef7c-7b43-4fee-9482-d03daf578ba9");
		cookie.put("myTicket", "Y");
		cookie.put("processRelatedTicket", "Y");

		Document doc = null;
		try {
			doc = Jsoup.connect("http://dbip2.navercorp.com/standardWordList.nhn")
				.cookies(cookie)
				.data(params).post();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Elements newsHeadlines = doc.select(".tabletype_list > tbody > tr:gt(0)");

		Optional<String> englishFull = newsHeadlines.stream()
			.filter(b -> abWord.equalsIgnoreCase(b.select("td:eq(2)").text()))
			.map(e -> e.select("td:eq(1) > a").text())
			.findFirst();

		englishFull.ifPresent(w -> cache.put(abWord.toLowerCase(), w.toLowerCase()));

		return englishFull;
	}

	public List<String> getSqlLine() {
		return sqlLine;
	}

	public String genericFullWord(String field) {
		Map<String, String> map = new HashMap<>();
		String[] worldAB = field.split("_");

		return Arrays.stream(worldAB).map(w -> requestAndCheck(w).orElse("xxx")).collect(Collectors.joining("_"));
	}


	public static void main(String[] args) throws Exception {
		RestoreAB restoreAB = new RestoreAB();

		List<String> fullWorldList = restoreAB.getSqlLine().stream().map(e -> e + "\t" + restoreAB.genericFullWord(e)).collect(Collectors.toList());
		fullWorldList.forEach(System.out::println);



	}
}
