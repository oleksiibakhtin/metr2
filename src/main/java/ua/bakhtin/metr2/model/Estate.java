package ua.bakhtin.metr2.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import ua.bakhtin.metr2.enums.EstateStatusType;

@Entity
@Table (name="estate")
public class Estate implements Serializable{
	
	private static final long serialVersionUID = -8642045366459533249L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne (cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinColumn (name="user_id")
	private User user;
	
	@OneToMany (mappedBy="estate", orphanRemoval=true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<EstatePhoto> estatePhoto;
	
	@Column (name = "date") // дата создания
	@DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime date;
	
	@Column (name = "estate_type")
	private String estateType;
	
	@Column (name = "estate_name")
	@Size(min=1, message="Поле обязательно к заполенению (до 100 символов)")
	@Size(max=100, message="Масимальный размер поля - 100 символов")
	private String estateName;
	
	@Column (name = "operation_type") //тип операции
	private String operationType;
	
	@Column (name = "status_type") //тип операции
	private String statusType = EstateStatusType.APPLICATION.toString();
	
	//--- особенности-подробности ---
	
	
	@Column (name = "rooms_count")
	@Min(value=0, message = "Количество комнат - положительное число")
	@Max(value=50, message = "Количество комнат не может превышать 50")
	private Integer roomsCount;
		
	@Column (name = "area") // площадь
	@Min(value=1, message = "Площадь - положительное число")
	@Max(value=1000000000, message = "Что это за площадь такая?")
	private Float area;
	
	@Column (name = "living_area") // жилая площадь
	@Min(value=0, message = "Площадь - положительное число")
	private Float livingArea;
	
	@Column (name = "floor_num") // номер этажа
	@Min(value=1, message = "Номер этажа - положительное число")
	@Max(value=100, message = "Больше 100 этажей???")
	private Integer floorNum;
	
	@Column (name = "floors_count") // количество этажей
	@Min(value = 1, message = "Количество этажей - положительное число")
	@Max(value=100, message = "Больше 100 этажей???")
	private Integer floorsCount;
	
	//--- размещение ---
	
	@Column (name = "regionType")
	private String regionType;
	
	@Column (name = "cityType")
	private String cityType;
	
	@Column (name = "districtType") // район
	private String districtType;
	
	@Column (name = "street")
	@Size(max=50, message="Масимальный размер поля - 50 символов")
	private String street;
	
	@Column (name = "apt")
	@Size(max=50, message="Масимальный размер поля - 50 символов")
	private String apt;
	
	@Column (name = "metro_distance")
	@Min(value=0, message = "Расстояние - положительное число")
	@Max(value=5000, message = "Указывать более 5км не имеет смысла")
	private Integer metroDistance;
	
	@Column (name = "price") // цена
	@Min(value=0, message = "Цена - положительное число")
	@Max(value=1000000000, message = "Недвижимостью дороже 1 млрд не занимаемся")
	private Integer price;
	
	@Column (name = "description") // описание
	@Size(min=1, message="Поле обязательно к заполенению (до 1000 символов)")
	@Size(max=1000, message="Масимальный размер поля - 1000 символов")
	private String description;
	
	@Column (name = "intop") // поместить в ТОП
	private Boolean inTop;
	
	public Estate () {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getEstateType() {
		return estateType;
	}

	public void setEstateType(String estateType) {
			this.estateType = estateType;
	}

	public String getEstateName() {
		return estateName;
	}

	public void setEstateName(String estateName) {
		this.estateName = estateName;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public Integer getRoomsCount() {
		return roomsCount;
	}

	public void setRoomsCount(Integer roomsCount) {
		this.roomsCount = roomsCount;
	}

	public Float getArea() {
		return area;
	}

	public void setArea(Float area) {
		this.area = area;
	}

	public Float getLivingArea() {
		return livingArea;
	}

	public void setLivingArea(Float livingArea) {
		this.livingArea = livingArea;
	}

	public Integer getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(Integer floorNum) {
		this.floorNum = floorNum;
	}

	public Integer getFloorsCount() {
		return floorsCount;
	}

	public void setFloorsCount(Integer floorsCount) {
		this.floorsCount = floorsCount;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getApt() {
		return apt;
	}

	public void setApt(String apt) {
		this.apt = apt;
	}

	public Integer getMetroDistance() {
		return metroDistance;
	}

	public void setMetroDistance(Integer metroDistance) {
		this.metroDistance = metroDistance;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getInTop() {
		return inTop;
	}

	public void setInTop(Boolean inTop) {
		this.inTop = inTop;
	}

	public Set<EstatePhoto> getEstatePhoto() {
		return estatePhoto;
	}

	public void setEstatePhoto(Set<EstatePhoto> estatePhoto) {
		this.estatePhoto = estatePhoto;
	}
	
	public void setEstateChanges (Estate updatedEstate) {
		area = updatedEstate.getArea();
		cityType = updatedEstate.getCityType();
		estateType = updatedEstate.getEstateType();
		metroDistance = updatedEstate.getMetroDistance();
		apt = updatedEstate.getApt();
		districtType = updatedEstate.getDistrictType();
		estateName = updatedEstate.getEstateName();
		description = updatedEstate.getDescription();
		inTop = updatedEstate.getInTop();
		floorNum = updatedEstate.getFloorNum();
		livingArea = updatedEstate.getLivingArea();
		estateName = updatedEstate.getEstateName();
		regionType = updatedEstate.getRegionType();
		street = updatedEstate.getStreet();
		price = updatedEstate.getPrice();
		floorsCount = updatedEstate.getFloorsCount();
		operationType = updatedEstate.getOperationType();
		roomsCount = updatedEstate.getRoomsCount();
		statusType = updatedEstate.getStatusType();
	}
	
}
