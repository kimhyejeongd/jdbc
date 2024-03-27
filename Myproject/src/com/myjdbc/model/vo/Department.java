package com.myjdbc.model.vo;

import java.util.Objects;

public class Department {
	private char DeptId;
	private String DeptTitle;
	private char LocationId;
public Department() {
	// TODO Auto-generated constructor stub
}
public Department(char deptId, String deptTitle, char locationId) {
	super();
	DeptId = deptId;
	DeptTitle = deptTitle;
	LocationId = locationId;
}
public char getDeptId() {
	return DeptId;
}
public void setDeptId(char deptId) {
	DeptId = deptId;
}
public String getDeptTitle() {
	return DeptTitle;
}
public void setDeptTitle(String deptTitle) {
	DeptTitle = deptTitle;
}
public char getLocationId() {
	return LocationId;
}
public void setLocationId(char locationId) {
	LocationId = locationId;
}
@Override
public String toString() {
	return "Department [DeptId=" + DeptId + ", DeptTitle=" + DeptTitle + ", LocationId=" + LocationId + "]";
}
@Override
public int hashCode() {
	return Objects.hash(DeptId, DeptTitle, LocationId);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Department other = (Department) obj;
	return DeptId == other.DeptId && Objects.equals(DeptTitle, other.DeptTitle) && LocationId == other.LocationId;
}

}