package com.epam.ht.entity.employee;

import static com.epam.ht.constant.HTConstant.CORRESPOND_EMPLOYEE_IDS;
import static com.epam.ht.constant.HTConstant.CORRESPOND_OFFICES;
import static com.epam.ht.constant.HTConstant.CORRESPOND_OFFICE_IDS;
import static com.epam.ht.constant.HTConstant.EMPLOYEES_NUMBER;
import static com.epam.ht.constant.HTConstant.EMPLOYEE_IDS_PARAM;
import static com.epam.ht.constant.HTConstant.EMPLOYEE_LIST;
import static com.epam.ht.constant.HTConstant.OFFICE_IDS_PARAM;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

import com.epam.ht.entity.address.Address;
import com.epam.ht.entity.office.Office;

@Entity
@NamedQueries({
		@NamedQuery(name = EMPLOYEE_LIST, query = "from Employee emp inner join fetch emp.jobs"
				+ " inner join fetch emp.address addr"
				+ " inner join fetch addr.city c"
				+ " inner join fetch c.country"
				+ " where emp.id in (:"	+ EMPLOYEE_IDS_PARAM + ")"),
		@NamedQuery(name = CORRESPOND_OFFICES, query = "from Office o inner join fetch o.company"
				+ " inner join fetch o.address addr"
				+ " inner join fetch addr.city c"
				+ " inner join fetch c.country"
				+ " where o.id in (:" + OFFICE_IDS_PARAM + ")") })
@NamedNativeQueries({
		@NamedNativeQuery(name = CORRESPOND_EMPLOYEE_IDS, query = "select employee_id from yra.employee", 
				resultClass = Long.class, resultSetMapping = "employeeIds"),
		@NamedNativeQuery(name = CORRESPOND_OFFICE_IDS, query = "select distinct office_id from yra.office_employee"
				+ " where employee_id in (:" + EMPLOYEE_IDS_PARAM + ")", 
				resultClass = Long.class,	resultSetMapping = "officeIds"),
		@NamedNativeQuery(name = EMPLOYEES_NUMBER, query = "select count(*) as employees_number"
				+ " from yra.employee")})
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "employeeIds", columns = @ColumnResult(name = "employee_id")),
		@SqlResultSetMapping(name = "officeIds", columns = @ColumnResult(name = "office_id")) })
public class Employee implements Serializable {
	private static final long serialVersionUID = -8246951586123338991L;

	@Id
	@Column(name = "EMPLOYEE_ID")
	private long id;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	// @JoinFetch(JoinFetchType.OUTER)
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EMPLOYEE_ID", insertable = false, updatable = false)
	private Address address;

	// @JoinFetch(JoinFetchType.OUTER)
	@ManyToMany
	// (fetch = FetchType.EAGER)
	@MapKeyJoinColumn(name = "OFFICE_ID")
	@JoinTable(name = "OFFICE_EMPLOYEE", joinColumns = @JoinColumn(name = "EMPLOYEE_ID"), inverseJoinColumns = @JoinColumn(name = "POSITION_ID"))
	private Map<Office, Position> jobs;

	public Employee() {
	}

	public Employee(long id) {
		setId(id);
	}

	public Map<Office, Position> getJobs() {
		return jobs;
	}

	public void setJobs(Map<Office, Position> jobs) {
		this.jobs = jobs;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long employeeId) {
		this.id = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		final int prime = 53;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((jobs == null) ? 0 : jobs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		Employee other = (Employee) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
			return false;
		}
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(other.lastName)) {
			return false;
		}
		if (jobs == null) {
			if (other.jobs != null) {
				return false;
			}
		} else if (!jobs.equals(other.jobs)) {
			return false;
		}
		return true;
	}
}
