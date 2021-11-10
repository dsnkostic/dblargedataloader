package resources.regular;

import java.util.List;

/**
 * Main JSON request for request creations. For each company, we have departments and each department will have a number of requests
 * Request will have an array of companies
 */
public class CreateResource {
  private List<CompanyResource> companies;

  public List<CompanyResource> getCompanies() {
    return companies;
  }

  public void setCompanies(List<CompanyResource> companies) {
    this.companies = companies;
  }
}
