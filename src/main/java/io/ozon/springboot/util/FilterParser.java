package io.ozon.springboot.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * Parse string search from input to object
 * 
 * @author muhammad.ozon
 *
 */
public class FilterParser {

	public static List<SearchCriteria> paramParse(String search) {
		List<SearchCriteria> params = new ArrayList<>();
		if (search != null && !search.isEmpty()) {
			String[] splitted = search.split("&");
			for (String searchs : splitted) {
				String compileString = "(\\w+?)(:|<|>|~|!)(.+?),";
				if (searchs != null) {
					Pattern pattern = Pattern.compile(compileString);
					Matcher matcher = pattern.matcher(searchs + ",");
					while (matcher.find()) {
						params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
					}
				}
			}
		}
		return params;

	}

}
