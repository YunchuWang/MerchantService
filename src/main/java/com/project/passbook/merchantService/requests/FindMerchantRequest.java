package com.project.passbook.merchantService.requests;

import com.project.passbook.merchantService.dao.MerchantManager;
import com.project.passbook.merchantService.model.exceptions.types.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindMerchantRequest {

  @NonNull
  private Integer merchantId;


  // Validate whether
  public void validate(MerchantManager merchantManager) throws NotFoundException {
    merchantManager.findById(merchantId);
  }
}
