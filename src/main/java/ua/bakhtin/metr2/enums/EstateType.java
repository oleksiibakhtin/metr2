package ua.bakhtin.metr2.enums;

import java.util.ArrayList;
import java.util.List;

public enum EstateType {

HOUSE ("Дом"),
LAND ("Земля"),
APARTMENT ("Квартира"), 
ROOM ("Комната"),
COTTAGE ("Коттедж"),
PARKING ("Паркинг"),
GARAGE ("Гараж"),
UNINHABITEDFUND ("Нежилой фонд")
;

private final String text;
private EstateType(final String text) {
	this.text = text;
}

@Override
public String toString() {
	return text;
}

public static List <String> getAllEstateTypes() {
	List <String> allEstateTypes = new ArrayList<String>();
	for (EstateType type : EstateType.values()) {
		allEstateTypes.add(type.toString());
	}
	return allEstateTypes;
}
	
}