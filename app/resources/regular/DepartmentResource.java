package resources.regular;

/**
 * Each department has its own ID, and the number of requests that it had
 */
public class DepartmentResource {

  private String departmentID;
  private int numberOfRequests;

  public String getDepartmentID() {
    return departmentID;
  }

  public void setDepartmentID(String departmentID) {
    this.departmentID = departmentID;
  }

  public int getNumberOfRequests() {
    return numberOfRequests;
  }

  public void setNumberOfRequests(int numberOfRequests) {
    this.numberOfRequests = numberOfRequests;
  }
}
