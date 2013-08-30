package com.epam.ht.entity.employee;

import java.io.Serializable;

import com.epam.ht.entity.office.Office;

// Job position
public class Position implements Serializable {
	private static final long serialVersionUID = -2082971136281218506L;
	
	private String position;
	
	private Office office;

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public String toString() {
		return position;
	}

	@Override
	public int hashCode() {
		final int prime = 101;
		int result = 1;
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
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
		Position other = (Position) obj;
		if (position == null) {
			if (other.position != null) {
				return false;
			}
		} else if (!position.equals(other.position)) {
			return false;
		}
		return true;
	}
	
}
