package resources.bulk;

import java.util.List;

public class CompanyBulkResource {
  private String companyID;
  private String departmentBase;
  private List<Integer> departmentRequests;

  public String getCompanyID() {
    return companyID;
  }

  public void setCompanyID(String companyID) {
    this.companyID = companyID;
  }

  public String getDepartmentBase() {
    return departmentBase;
  }

  public void setDepartmentBase(String departmentBase) {
    this.departmentBase = departmentBase;
  }

  public List<Integer> getDepartmentRequests() {
    return departmentRequests;
  }

  public void setDepartmentRequests(List<Integer> departmentRequests) {
    this.departmentRequests = departmentRequests;
  }
}
