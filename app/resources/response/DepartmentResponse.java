package resources.response;

import resources.regular.DepartmentResource;

public class DepartmentResponse {
  private String departmentID;
  private long numberOfRequests;

  public DepartmentResponse(String contactID, long numberOfPR) {
    this.departmentID = contactID;
    this.numberOfRequests = numberOfPR;
  }

  public DepartmentResponse(DepartmentResource resource) {
    this.departmentID = resource.getDepartmentID();
    this.numberOfRequests = resource.getNumberOfRequests();
  }

  public String getDepartmentID() {
    return departmentID;
  }

  public void setDepartmentID(String departmentID) {
    this.departmentID = departmentID;
  }

  public long getNumberOfRequests() {
    return numberOfRequests;
  }

  public void setNumberOfRequests(long numberOfRequests) {
    this.numberOfRequests = numberOfRequests;
  }
}
