package in.org.cris.cmm.lsin.config;

import org.springframework.security.core.GrantedAuthority;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;

public class CmmUserDetails{

	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;

	private String user_name;
	private String firstName;
	private String depot;
	private String division;
	private String zone;

	private String level;

	private String department;
	private String location;
	private String accessType;
	
	private String orgslno;

	private Collection<? extends GrantedAuthority> authorities;
	
	

	public CmmUserDetails() {
		
	}
	
	public CmmUserDetails(Map<String, Object> details) {
		Field[] fields = this.getClass().getDeclaredFields();
		for(Field field : fields) {
			try {
				if(!Modifier.isFinal(field.getModifiers()))
					field.set(this, details.get(field.getName()));;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		//Fields
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getDepot() {
		return depot;
	}

	public void setDepot(String depot) {
		this.depot = depot;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	
	public String getPassword() {
		return "[PROTECTED]";
	}

	public String getOrgslno() {
		return orgslno;
	}

	public void setOrgslno(String orgslno) {
		this.orgslno = orgslno;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	@Override
	public String toString() {
		return "CmmUserDetails{" +
				"username='" + user_name + '\'' +
				", firstName='" + firstName + '\'' +
				", depot='" + depot + '\'' +
				", division='" + division + '\'' +
				", zone='" + zone + '\'' +
				", level='" + level + '\'' +
				", department='" + department + '\'' +
				", location='" + location + '\'' +
				", accessType='" + accessType + '\'' +
				", orgslno='" + orgslno + '\'' +
				", authorities='" + authorities + '\'' +
				'}';
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
}
