package ua.bakhtin.metr2.enums;

import java.util.ArrayList;
import java.util.List;

public enum OperationType {

	SALE ("Продажа"),
	PURCHASE ("Покупка"),
	RENT ("Аренда")
	;

private final String text;
private OperationType(final String text) {
	this.text = text;
}

@Override
public String toString() {
	return text;
}

public static List <String> getAllOperationTypes() {
	List <String> allOperationTypes = new ArrayList<String>();
	for (OperationType type : OperationType.values()) {
		allOperationTypes.add(type.toString());
	}
	return allOperationTypes;
}
	
}