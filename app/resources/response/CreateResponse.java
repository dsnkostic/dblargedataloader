package resources.response;

import java.util.List;
import java.util.stream.Collectors;
import resources.bulk.CreateBulkResource;
import resources.regular.CreateResource;

/**
 * Response from the service that gives us some statistics about the number of requests that will be created
 * Note that getting the response from the application DOES NOT MEAN THAT DATABASE POPULATION IS COMPLETED!!
 * Observe console logs to determine when creation is completed
 */
public class CreateResponse {
  private Long totalNumberOfRequests;
  private Integer numberOfCompanies;
  private List<CompanyResponse> companies;

  public CreateResponse(CreateResource resource) {
    this.numberOfCompanies = resource.getCompanies().size();
    this.companies = resource.getCompanies().stream()
        .map(CompanyResponse::new)
        .collect(Collectors.toList());
    this.totalNumberOfRequests = this.companies.stream().mapToLong(CompanyResponse::getNumberOfRequests).sum();
  }

  public CreateResponse(CreateBulkResource resource) {
    this.numberOfCompanies = resource.getCompanies().size();
    this.companies = resource.getCompanies().stream()
        .map(CompanyResponse::new)
        .collect(Collectors.toList());
    this.totalNumberOfRequests = this.companies.stream().mapToLong(CompanyResponse::getNumberOfRequests).sum();
  }

  public Long getTotalNumberOfRequests() {
    return totalNumberOfRequests;
  }

  public void setTotalNumberOfRequests(Long totalNumberOfRequests) {
    this.totalNumberOfRequests = totalNumberOfRequests;
  }

  public List<CompanyResponse> getCompanies() {
    return companies;
  }

  public void setCompanies(List<CompanyResponse> companies) {
    this.companies = companies;
  }

  public Integer getNumberOfCompanies() {
    return numberOfCompanies;
  }

  public void setNumberOfCompanies(Integer numberOfCompanies) {
    this.numberOfCompanies = numberOfCompanies;
  }
}
