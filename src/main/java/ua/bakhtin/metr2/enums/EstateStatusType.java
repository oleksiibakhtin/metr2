package ua.bakhtin.metr2.enums;

import java.util.ArrayList;
import java.util.List;

public enum EstateStatusType {
	APPLICATION ("Заявка"),
	PUBLICATION ("Публикация"),
	REMOVED ("Удалено")
	;
	
private final String text;
private EstateStatusType(final String text) {
	this.text = text;
}

@Override
public String toString() {
	return text;
}

public static List <String> getAllEstateStatusTypes() {
	List <String> allEstateStatusTypes = new ArrayList<String>();
	for (EstateStatusType type : EstateStatusType.values()) {
		allEstateStatusTypes.add(type.toString());
	}
	return allEstateStatusTypes;
}
	
}