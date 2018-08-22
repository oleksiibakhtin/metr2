package ua.bakhtin.metr2.enums;

import java.util.ArrayList;
import java.util.List;

public enum RegionType {
	KIEV ("Киевcкая");
	
private final String text;
private RegionType(final String text) {
	this.text = text;
}

@Override
public String toString() {
	return text;
}

public static List <String> getAllRegionTypes() {
	List <String> allRegionTypes = new ArrayList<String>();
	for (RegionType type : RegionType.values()) {
		allRegionTypes.add(type.toString());
	}
	return allRegionTypes;
}
	
}