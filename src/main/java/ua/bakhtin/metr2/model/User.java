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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import ua.bakhtin.metr2.enums.UserType;
import ua.bakhtin.metr2.model.dto.UserDTO;
import ua.bakhtin.metr2.validator.UniqueEmail;
import ua.bakhtin.metr2.validator.UniquePhone;

@Component
@Entity
@Table (name = "user")
public class User implements Serializable{
	
	private static final long serialVersionUID = -615345085617737388L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column (name = "name")
	@Size(min=1, message="Поле обязательно к заполенению")
	private String name;
	
	@Column (name = "email") // email or phone number (validation!)
	@Size(min=1, message="Поле обязательно к заполенению")
	@Email (message="С E-mail явно что-то не так...")
	@UniqueEmail
	private String email;
	
	@Column (name = "phone") // email or phone number (validation!)
	@Size(min=1, message="Поле обязательно к заполенению")
	@Pattern(regexp="^\\+380\\(\\d{2}\\)\\d{7}$", message="Номер телефона должен соответствовать формату +380(**)*******")
	@UniquePhone
	private String phone;
	
	@Column (name = "password")
	@Size(min=5, message="Длина пароля должна составлять не менее 5 символов")
	private String password;
	
	@Column (name = "dispatch")
	private Boolean dispatch;
	
	// автогенерирующиеся поля
	@Column (name = "role")
	private String role = UserType.USER.toString();
	
	@Column (name = "reg_date") // дата регистрации
	@DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime regDate;
	
	@OneToMany (mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.EAGER)
	private Set <Estate> estates;
	
	public User () {}

	public User(String name, String email, String phone, String password, Boolean dispatch) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.dispatch = dispatch;
		this.regDate = LocalDateTime.now();
	}
	
	public User (UserDTO registeringUserDto) {
		this.name = registeringUserDto.getName();
		this.email = registeringUserDto.getEmail();
		this.phone = registeringUserDto.getPhone();
		this.password = registeringUserDto.getPassword();
		this.dispatch = registeringUserDto.isDispatch();
		this.regDate = LocalDateTime.now();
		this.role = "user";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getDispatch() {
		return dispatch;
	}

	public void setDispatch(Boolean dispatch) {
		this.dispatch = dispatch;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public Set<Estate> getEstates() {
		return estates;
	}

	public void setEstates(Set<Estate> estates) {
		this.estates = estates;
	}
	
	public void removeEstate (Estate estate) {
		estates.remove(estate);
		if (estate != null) {
			estate.setUser(null);
		}
	}
}