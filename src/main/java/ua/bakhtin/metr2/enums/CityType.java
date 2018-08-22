package ua.bakhtin.metr2.enums;

import java.util.ArrayList;
import java.util.List;

public enum CityType {
	KIEV ("Киев");
	
private final String text;
private CityType(final String text) {
	this.text = text;
}

@Override
public String toString() {
	return text;
}

public static List <String> getAllCityTypes() {
	List <String> allCityTypes = new ArrayList<String>();
	for (CityType type : CityType.values()) {
		allCityTypes.add(type.toString());
	}
	return allCityTypes;
}
	
}