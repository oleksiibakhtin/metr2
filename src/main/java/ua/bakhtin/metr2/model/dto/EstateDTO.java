package ua.bakhtin.metr2.model.dto;

public class EstateDTO{
	
//	private Long id;
//	private Long usrId;
//	private LocalDateTime date;
//	private List<String> estateTypes;
//	private String estateType;
//	private String estateName;
//	private List <String> operationTypes;
//	private String operationType;
//	private String status;
//	private Integer roomsCount;		
//	private Float area;
//	private Float livingArea;
//	private Integer floorNum;
//	private Integer floorsCount;
//	private List<String> regionTypes;
//	private String region;
//	private List <String> cityTypes;
//	private String city;
//	private List<String> districtTypes;
//	private String district;
//	private String street;
//	private String apt;
//	private Integer metroDistance;
//	private Integer price;
//	private String description;
//	private Boolean inTop;
//	
//	public EstateDTO () {
//		estateTypes = EstateType.getAllEstateTypes();
//		operationTypes = OperationType.getAllOperationTypes();
//		regionTypes = RegionType.getAllRegionTypes();
//		cityTypes = CityType.getAllCityTypes();
//		districtTypes = DistrictType.getAllDistrictTypes();
//	}
//	
//	public EstateDTO (Estate estate) {
//		id = estate.getId();
//		this.usrId = estate.getUsrId();
//		this.date = estate.getDate();
//		this.estateType = estate.getEstateType();
//		this.estateName = estate.getEstateName();
//		this.operationType = estate.getOperationType();
//		this.status = estate.getStatus();
//		this.roomsCount = estate.getRoomsCount();
//		this.area = estate.getArea();
//		this.livingArea = estate.getLivingArea();
//		this.floorNum = estate.getFloorNum();
//		this.floorsCount = estate.getFloorsCount();
//		this.region = estate.getRegion();
//		this.city = estate.getCity();
//		this.district = estate.getDistrict();
//		this.street = estate.getStreet();
//		this.apt = estate.getApt();
//		this.metroDistance = estate.getMetroDistance();
//		this.price = estate.getPrice();
//		this.description = estate.getDescription();
//		this.inTop = estate.getInTop();
//		estateTypes = EstateType.getAllEstateTypes();
//		operationTypes = OperationType.getAllOperationTypes();
//		regionTypes = RegionType.getAllRegionTypes();
//		cityTypes = CityType.getAllCityTypes();
//		districtTypes = DistrictType.getAllDistrictTypes();
//	}
//
//	public EstateDTO(Long id, Long usrId, LocalDateTime date, String estateType, String estateName, String operationType, String status, Integer roomsCount, Float area, Float livingArea, Integer floorNum, Integer floorsCount, String region, String city,
//			String district, String street, String apt, Integer metroDistance, Integer price, String description, Boolean inTop) {
//		this.id = id;
//		this.usrId = usrId;
//		this.date = date;
//		this.estateType = estateType;
//		this.estateName = estateName;
//		this.operationType = operationType;
//		this.status = status;
//		this.roomsCount = roomsCount;
//		this.area = area;
//		this.livingArea = livingArea;
//		this.floorNum = floorNum;
//		this.floorsCount = floorsCount;
//		this.region = region;
//		this.city = city;
//		this.district = district;
//		this.street = street;
//		this.apt = apt;
//		this.metroDistance = metroDistance;
//		this.price = price;
//		this.description = description;
//		this.inTop = inTop;
//		estateTypes = EstateType.getAllEstateTypes();
//		operationTypes = OperationType.getAllOperationTypes();
//		regionTypes = RegionType.getAllRegionTypes();
//		cityTypes = CityType.getAllCityTypes();
//		districtTypes = DistrictType.getAllDistrictTypes();
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Long getUsrId() {
//		return usrId;
//	}
//
//	public void setUsrId(Long usrId) {
//		this.usrId = usrId;
//	}
//
//	public LocalDateTime getDate() {
//		return date;
//	}
//
//	public void setDate(LocalDateTime date) {
//		this.date = date;
//	}
//
//	public List<String> getEstateTypes() {
//		return estateTypes;
//	}
//
//	public void setEstateTypes(List<String> estateTypes) {
//		this.estateTypes = estateTypes;
//	}
//
//	public String getEstateType() {
//		return estateType;
//	}
//
//	public void setEstateType(String estateType) {
//		this.estateType = estateType;
//	}
//
//	public String getEstateName() {
//		return estateName;
//	}
//
//	public void setEstateName(String estateName) {
//		this.estateName = estateName;
//	}
//
//	public List<String> getOperationTypes() {
//		return operationTypes;
//	}
//
//	public void setOperationTypes(List<String> operationTypes) {
//		this.operationTypes = operationTypes;
//	}
//
//	public String getOperationType() {
//		return operationType;
//	}
//
//	public void setOperationType(String operationType) {
//		this.operationType = operationType;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public Integer getRoomsCount() {
//		return roomsCount;
//	}
//
//	public void setRoomsCount(Integer roomsCount) {
//		this.roomsCount = roomsCount;
//	}
//
//	public Float getArea() {
//		return area;
//	}
//
//	public void setArea(Float area) {
//		this.area = area;
//	}
//
//	public Float getLivingArea() {
//		return livingArea;
//	}
//
//	public void setLivingArea(Float livingArea) {
//		this.livingArea = livingArea;
//	}
//
//	public Integer getFloorNum() {
//		return floorNum;
//	}
//
//	public void setFloorNum(Integer floorNum) {
//		this.floorNum = floorNum;
//	}
//
//	public Integer getFloorsCount() {
//		return floorsCount;
//	}
//
//	public void setFloorsCount(Integer floorsCount) {
//		this.floorsCount = floorsCount;
//	}
//
//	public List<String> getRegionTypes() {
//		return regionTypes;
//	}
//
//	public void setRegionTypes(List<String> regionTypes) {
//		this.regionTypes = regionTypes;
//	}
//
//	public String getRegion() {
//		return region;
//	}
//
//	public void setRegion(String region) {
//		this.region = region;
//	}
//
//	public List<String> getCityTypes() {
//		return cityTypes;
//	}
//
//	public void setCityTypes(List<String> cityTypes) {
//		this.cityTypes = cityTypes;
//	}
//
//	public String getCity() {
//		return city;
//	}
//
//	public void setCity(String city) {
//		this.city = city;
//	}
//
//	public List<String> getDistrictTypes() {
//		return districtTypes;
//	}
//
//	public void setDistrictTypes(List<String> districtTypes) {
//		this.districtTypes = districtTypes;
//	}
//
//	public String getDistrict() {
//		return district;
//	}
//
//	public void setDistrict(String district) {
//		this.district = district;
//	}
//
//	public String getStreet() {
//		return street;
//	}
//
//	public void setStreet(String street) {
//		this.street = street;
//	}
//
//	public String getApt() {
//		return apt;
//	}
//
//	public void setApt(String apt) {
//		this.apt = apt;
//	}
//
//	public Integer getMetroDistance() {
//		return metroDistance;
//	}
//
//	public void setMetroDistance(Integer metroDistance) {
//		this.metroDistance = metroDistance;
//	}
//
//	public Integer getPrice() {
//		return price;
//	}
//
//	public void setPrice(Integer price) {
//		this.price = price;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public Boolean getInTop() {
//		return inTop;
//	}
//
//	public void setInTop(Boolean inTop) {
//		this.inTop = inTop;
//	}
	
	
}
