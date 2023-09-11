package gov.example.appDemo;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CrawlingStd {

	public static ArrayList<Student> SL = new ArrayList<Student>();
	private static Integer sid = 1;

	public static void crawling() throws ParseException {

		String URL = "https://apl.hongik.ac.kr/lecture/dbms";
		Document doc;

		try {
			doc = Jsoup.connect(URL).get();

			Element students = doc.getElementById("h.545448291ccf31dc_17");

			String[] tmp = students.text().split("PhD Students ");
			String phds = tmp[1].split("Master Students ")[0];
			String masters = tmp[1].split("Master Students ")[1].split("Undergraduate Students ")[0];
			String undergrads = tmp[1].split("Master Students ")[1].split("Undergraduate Students ")[1];

			collectInfo(phds, "phd");
			collectInfo(masters, "master");
			collectInfo(undergrads, "undergrad");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void collectInfo(String infos, String degree) {

		String[] info = infos.split(", ");
		
		String name, email, graduation = "";
		name = info[0];
		
		int i = 1;
		while (i < info.length) {
			email = info[i];
			graduation = info[++i].split(" ")[0];
			SL.add(new Student(sid, name, email, Integer.parseInt(graduation), degree));
			sid++;

			if (i < info.length - 1) {
				name = "";
				for (int j = 1; j < info[i].split(" ").length; j++) { // name
					name += info[i].split(" ")[j];
					name += " ";
				}
				name = name.substring(0, name.length() - 1); // name의 마지막 공백 제거
			}
			i++;
		}
	}
}