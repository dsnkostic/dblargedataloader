package resources.bulk;

import java.util.List;

/**
 * Same as a regular request creation, but here we can specify array requests for each department,
 * thus saving us time to create each individual JSON node for each department
 */
public class CreateBulkResource {
  private List<CompanyBulkResource> companies;

  public List<CompanyBulkResource> getCompanies() {
    return companies;
  }

  public void setCompanies(List<CompanyBulkResource> companies) {
    this.companies = companies;
  }
}
