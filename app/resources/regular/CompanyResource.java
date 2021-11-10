package resources.regular;

import java.util.List;

/**
 * Each company has its own ID, and the array of department requests
 */
public class CompanyResource {
  private String companyID;
  private List<DepartmentResource> departments;

  public String getCompanyID() {
    return companyID;
  }

  public void setCompanyID(String companyID) {
    this.companyID = companyID;
  }

  public List<DepartmentResource> getDepartments() {
    return departments;
  }

  public void setDepartments(List<DepartmentResource> departments) {
    this.departments = departments;
  }
}
