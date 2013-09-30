package com.epam.ht.entity.employee;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

// Job position
@Entity
public class Position implements Serializable {
	private static final long serialVersionUID = -2082971136281218506L;

	@Id
	@SequenceGenerator(name="position_id_generator", sequenceName="position_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="position_id_generator")
	@Column(name="position_id")
	private long id;

	private String position;

	public Position() {
	}

	public Position(String position) {
		setPosition(position);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getPosition() {
		return position;
	}

	@Column(name = "POSITION")
	public void setPosition(String position) {
		this.position = position;
	}

	@Override
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
