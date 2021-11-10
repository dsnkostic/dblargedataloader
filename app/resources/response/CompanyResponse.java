package resources.response;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import resources.bulk.CompanyBulkResource;
import resources.regular.CompanyResource;

public class CompanyResponse {
  private String companyID;
  private Long numberOfRequests;
  private Integer numberOfDepartments;
  private List<DepartmentResponse> departments;

  public CompanyResponse(CompanyResource resource) {
    this.companyID = resource.getCompanyID();
    this.numberOfDepartments = resource.getDepartments().size();
    this.departments = resource.getDepartments().stream()
        .map(DepartmentResponse::new)
        .collect(Collectors.toList());
    this.numberOfRequests = this.departments.stream().mapToLong(DepartmentResponse::getNumberOfRequests).sum();
  }

  public CompanyResponse(CompanyBulkResource resource) {
    this.companyID = resource.getCompanyID();
    this.numberOfDepartments = resource.getDepartmentRequests().size();
    this.departments = IntStream.range(0, resource.getDepartmentRequests().size())
        .mapToObj(i -> new DepartmentResponse(resource.getDepartmentBase() + i, resource.getDepartmentRequests().get(i)))
        .collect(Collectors.toList());
    this.numberOfRequests = this.departments.stream().mapToLong(DepartmentResponse::getNumberOfRequests).sum();
  }

  public String getCompanyID() {
    return companyID;
  }

  public void setCompanyID(String companyID) {
    this.companyID = companyID;
  }

  public Long getNumberOfRequests() {
    return numberOfRequests;
  }

  public void setNumberOfRequests(Long numberOfRequests) {
    this.numberOfRequests = numberOfRequests;
  }

  public Integer getNumberOfDepartments() {
    return numberOfDepartments;
  }

  public void setNumberOfDepartments(Integer numberOfDepartments) {
    this.numberOfDepartments = numberOfDepartments;
  }

  public List<DepartmentResponse> getContacts() {
    return departments;
  }

  public void setContacts(List<DepartmentResponse> contacts) {
    this.departments = contacts;
  }
}
