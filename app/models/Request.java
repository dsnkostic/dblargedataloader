package models;

import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

/**
 * The request that was made by given department from the given company.
 * detail is just some random string and not that important.
 */
@Entity
@Table(name = "request")
public class Request {

  @Id
  @GeneratedValue
  private UUID id;

  private String companyID;

  private String departmentID;

  private String detail;

  @CreationTimestamp
  @Column(name = "created")
  private ZonedDateTime created;

  public Request() {
  }

  public Request(String companyID, String departmentID, String text) {
    this.companyID = companyID;
    this.departmentID = departmentID;
    this.detail = text;
  }

  public UUID getId() {
    return id;
  }

  public String getCompanyID() {
    return companyID;
  }

  public void setCompanyID(String companyID) {
    this.companyID = companyID;
  }

  public String getDepartmentID() {
    return departmentID;
  }

  public void setDepartmentID(String departmentID) {
    this.departmentID = departmentID;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String text) {
    this.detail = text;
  }
}
