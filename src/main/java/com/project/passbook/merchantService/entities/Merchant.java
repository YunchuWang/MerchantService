package com.project.passbook.merchantService.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "merchant")
public class Merchant {

  private static final String ID_COLUMN = "id";
  private static final String NAME_COLUMN = "name";
  private static final String LOGO_URL_COLUMN = "logo_url";
  private static final String BUSINESS_LICENSE_URL_COLUMN = "business_license_url";
  private static final String PHONE_COLUMN = "phone";
  private static final String ADDRESS_COLUMN = "address";
  private static final String IS_AUDIT_COLUMN = "is_audit";

  @Id
  @GeneratedValue
  @Column(name = ID_COLUMN, nullable = false)
  private Integer id;

  // TODO: Add ref to user id

  @Column(name = NAME_COLUMN, nullable = false, unique = true)
  private String name;

  @Column(name = LOGO_URL_COLUMN, nullable = false)
  private String logoUrl;

  @Column(name = BUSINESS_LICENSE_URL_COLUMN, nullable = false)
  private String businessLicenseUrl;

  @Column(name = PHONE_COLUMN, nullable = false)
  private String phone;

  @Column(name = ADDRESS_COLUMN, nullable = false)
  private String address;

  @Column(name = IS_AUDIT_COLUMN, nullable = false)
  private boolean isAudit;
}
