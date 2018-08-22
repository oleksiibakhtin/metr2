package ua.bakhtin.metr2.enums;

import java.util.ArrayList;
import java.util.List;

public enum DistrictType {
	GOLOSEEVSKIY ("Голосеевский"),
	SVYATOSHINSKIY ("Святошинский"),
	SOLOMENSKIY("Соломенский"),
	OBOLONSKIY("Оболонский"),
	PODOLSKIY("Подольский"),
	PECHERSKIY("Печерский"),
	SHEVCHENKOVSKIY("Шевченковский"),
	DARNITSKIY("Дарницкий"),
	DNEPROVSKIY("Днепровский"),
	DESNYANSKIY("Деснянский")	;
	
private final String text;
private DistrictType(final String text) {
	this.text = text;
}

@Override
public String toString() {
	return text;
}

public static List <String> getAllDistrictTypes() {
	List <String> allDistrictTypes = new ArrayList<String>();
	for (DistrictType type : DistrictType.values()) {
		allDistrictTypes.add(type.toString());
	}
	return allDistrictTypes;
}
	
}