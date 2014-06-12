package de.gebauer.homematic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.supercsv.io.CsvListReader;
import org.supercsv.io.Tokenizer;
import org.supercsv.prefs.CsvPreference;

public class PeriodAnalyzer {

    public static void main(String[] args) throws Exception {

	CsvPreference preferences = new CsvPreference.Builder('\'', '\t', "\n").build();
	PeriodAnalyzer periodAnalyzer = new PeriodAnalyzer();

	periodAnalyzer.read(preferences, "/messages.log", "[1EA808->000000");
	// periodAnalyzer.read(preferences, "/openhab.log", "[206185->1C475A");
	// periodAnalyzer.read(preferences, intList, "/messages-2014-04-28.log");
	// periodAnalyzer.read(preferences, intList, "/206185_diff.properties");

	periodAnalyzer.print();

	// 204 bit
	// System.out.println(intList.size() * 3);

    }

    private Date lastTime;
    private int lastDiff;
    private Set<Integer> valueToChange = new TreeSet<Integer>();

    private void read(CsvPreference preferences, String file, String comm) throws IOException, ParseException {
	InputStream resourceAsStream = PeriodAnalyzer.class.getResourceAsStream(file);
	InputStreamReader ist = new InputStreamReader(resourceAsStream);
	Reader reader = new BufferedReader(ist);
	CsvListReader csvListReader = new CsvListReader(new Tokenizer(reader, preferences) {

	    @Override
	    public boolean readColumns(List<String> cols) throws IOException {
		cols.clear();

		String readLine = super.readLine();
		if (readLine == null) {
		    return false;
		}
		String[] split = readLine.split("[\\t\\s]");

		for (int i = 0; i < split.length; i++) {
		    if (split[i].isEmpty()) {
			continue;
		    }
		    cols.add(split[i]);
		}
		return true;
	    }
	}, preferences);

	if (this.lastTime == null) {
	    this.lastTime = new Date();
	}

	List<String> read;
	while ((read = csvListReader.read()) != null) {
	    if (!read.isEmpty() && !read.get(0).startsWith("#")) {
		if (read.size() > 1 && read.get(1).contains(comm)) {
		    List<Object> pretty = pretty(read);
		    // if (pretty.get(2).equals("-14250")) {
		    // if (pretty.get(2).equals("49750")) {

		    Object string = pretty.get(0);
		    Date curTime = new SimpleDateFormat("HH:mm:ss.SSS").parse(((String) string).split("\\s")[0]);

		    if (this.lastTime.after(curTime)) {
			this.lastTime.setTime(this.lastTime.getTime() - 24 * 60 * 60 * 1000);
		    }

		    int diff = round((int) (curTime.getTime() - this.lastTime.getTime()));

		    int diffDiff = round(diff - this.lastDiff);

		    if (diffDiff == 49750 || diffDiff == -14250) {
			valueToChange.add(Integer.parseInt((String) pretty.get(1), 16));
		    }

		    String sep = ";";

		    System.out.println(pretty.get(1) + sep + diff + sep + diffDiff + sep + curTime);

		    this.lastTime = curTime;
		    this.lastDiff = diff;
		}
	    }
	}

	csvListReader.close();
    }

    private void print() {
	int last = 0;
	int lastDiff = 0;
	for (Integer string : valueToChange) {
	    int cur = string - 1;
	    int diff = cur - last;
	    int diffDiff = diff - lastDiff;
	    String binary = String.format("%3s", Integer.toBinaryString(diff)).replace(' ', '0');
	    System.out.println(cur + "\t" + diff + "\t" + diffDiff);
	    last = cur;
	    lastDiff = diff;
	}
    }

    private List<Object> pretty(List<String> read) {
	List<Object> arrayList = new ArrayList<Object>();

	// time
	arrayList.add(read.get(0));
	// nr
	arrayList.add(read.get(2).substring(1, read.get(2).length() - 1));
	// arrayList.add(String.valueOf(getAbsDiff(read.get(read.size() - 2))));
	// arrayList.add(String.valueOf(getDiffDiff(read.get(read.size() - 1))));

	return arrayList;
    }

    private int getDiffDiff(String diffDiffStr) {
	int diffDiff = Integer.parseInt(diffDiffStr);
	return round(diffDiff);
    }

    private int getAbsDiff(String absDiffStr) {
	int absDiff = Integer.parseInt(absDiffStr);
	// if (absDiff >= 120000) {
	// absDiff -= 120000;
	// }
	return round(absDiff);
    }

    private int round(int diff) {
	double i = diff / 250.0;
	int multi = (int) Math.round(i);
	return 250 * multi;
    }

}
