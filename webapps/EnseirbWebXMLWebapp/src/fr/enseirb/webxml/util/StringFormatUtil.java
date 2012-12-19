package fr.enseirb.webxml.util;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class StringFormatUtil {

	private static final long ONE_DAY_IN_MS = 1000 * 60 * 60 * 24;

	public static Date getNowDateWithDelay(String delay) {
		long delayedDate = System.currentTimeMillis() + StringFormatUtil.ONE_DAY_IN_MS * Integer.parseInt(delay);
		return new Date(delayedDate);
	}

	public static String sortAndFormat(List<String> stringList) {
		if (stringList == null) {
			return "";
		}
		String[] stringArray = stringList.toArray(new String[0]);
		Arrays.sort(stringArray);

		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < stringArray.length; i++) {
			if (i > 0) {
				buffer.append(";");
			}
			buffer.append(stringArray[i]);
		}

		return buffer.toString();
	}

}
