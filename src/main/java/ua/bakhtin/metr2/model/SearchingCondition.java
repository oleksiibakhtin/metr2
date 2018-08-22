package ua.bakhtin.metr2.model;

public class SearchingCondition {
	
	private String keyWords;
	private String operationType;
	private String estateType;
	private String regionType;
	private String cityType;
	private String districtType;
	private Integer fromPrice;
	private Integer toPrice;
	
	public SearchingCondition() {}

	public SearchingCondition(String keyWords, String operationType, String estateType, String regionType,
			String cityType, String districtType, Integer fromPrice, Integer toPrice) {
		this.keyWords = keyWords;
		this.operationType = operationType;
		this.estateType = estateType;
		this.regionType = regionType;
		this.cityType = cityType;
		this.districtType = districtType;
		this.fromPrice = fromPrice;
		this.toPrice = toPrice;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getEstateType() {
		return estateType;
	}

	public void setEstateType(String estateType) {
		this.estateType = estateType;
	}

	public String getRegionType() {
		return regionType;
	}

	public void setRegionType(String regionType) {
		this.regionType = regionType;
	}

	public String getCityType() {
		return cityType;
	}

	public void setCityType(String cityType) {
		this.cityType = cityType;
	}

	public String getDistrictType() {
		return districtType;
	}

	public void setDistrictType(String districtType) {
		this.districtType = districtType;
	}

	public Integer getFromPrice() {
		return fromPrice;
	}

	public void setFromPrice(Integer fromPrice) {
		this.fromPrice = fromPrice;
	}

	public Integer getToPrice() {
		return toPrice;
	}

	public void setToPrice(Integer toPrice) {
		this.toPrice = toPrice;
	}

	
}
