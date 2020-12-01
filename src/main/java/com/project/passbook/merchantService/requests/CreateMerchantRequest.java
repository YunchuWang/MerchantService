package com.project.passbook.merchantService.requests;

import com.project.passbook.merchantService.entities.Merchant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMerchantRequest {

  @NonNull
  private String merchantName;

  @NonNull
  private String logoUrl;

  @NonNull
  private String businessLicenseUrl;

  @NonNull
  private String phone;

  @NonNull
  private String address;

  public Merchant toDao() {
    return Merchant.builder()
                   .address(address)
                   .name(merchantName)
                   .businessLicenseUrl(businessLicenseUrl)
                   .logoUrl(logoUrl)
                   .phone(phone)
                   .build();
  }
}
