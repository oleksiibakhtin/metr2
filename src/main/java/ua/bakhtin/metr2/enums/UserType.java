package ua.bakhtin.metr2.enums;

import java.util.ArrayList;
import java.util.List;

public enum UserType {
	ADMIN ("admin"),
	USER ("user");
	
private final String text;
private UserType(final String text) {
	this.text = text;
}

@Override
public String toString() {
	return text;
}

public static List <String> getAllUserTypes() {
	List <String> allUserTypes = new ArrayList<String>();
	for (UserType type : UserType.values()) {
		allUserTypes.add(type.toString());
	}
	return allUserTypes;
}
	
}