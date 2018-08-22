package ua.bakhtin.metr2.model;

import java.util.List;

import ua.bakhtin.metr2.enums.CityType;
import ua.bakhtin.metr2.enums.DistrictType;
import ua.bakhtin.metr2.enums.EstateStatusType;
import ua.bakhtin.metr2.enums.EstateType;
import ua.bakhtin.metr2.enums.OperationType;
import ua.bakhtin.metr2.enums.RegionType;

public class Types {
	private List <String> cityTypes;
	private List <String> districtTypes;
	private List <String> estateStatusTypes;
	private List <String> estateTypes;
	private List <String> operationTypes;
	private List <String> regionTypes;
	
	public Types () {
		cityTypes = CityType.getAllCityTypes();
		districtTypes = DistrictType.getAllDistrictTypes();
		estateStatusTypes = EstateStatusType.getAllEstateStatusTypes();
		estateTypes = EstateType.getAllEstateTypes();
		operationTypes = OperationType.getAllOperationTypes();
		regionTypes = RegionType.getAllRegionTypes();
	}

	public List<String> getCityTypes() {
		return cityTypes;
	}

	public void setCityTypes(List<String> cityTypes) {
		this.cityTypes = cityTypes;
	}

	public List<String> getDistrictTypes() {
		return districtTypes;
	}

	public void setDistrictTypes(List<String> districtTypes) {
		this.districtTypes = districtTypes;
	}

	public List<String> getEstateStatusTypes() {
		return estateStatusTypes;
	}

	public void setEstateStatusTypes(List<String> estateStatusTypes) {
		this.estateStatusTypes = estateStatusTypes;
	}

	public List<String> getEstateTypes() {
		return estateTypes;
	}

	public void setEstateTypes(List<String> estateTypes) {
		this.estateTypes = estateTypes;
	}

	public List<String> getOperationTypes() {
		return operationTypes;
	}

	public void setOperationTypes(List<String> operationTypes) {
		this.operationTypes = operationTypes;
	}

	public List<String> getRegionTypes() {
		return regionTypes;
	}

	public void setRegionTypes(List<String> regionTypes) {
		this.regionTypes = regionTypes;
	}
	
}
