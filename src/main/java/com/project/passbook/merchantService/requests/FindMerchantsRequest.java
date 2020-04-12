package com.project.passbook.merchantService.requests;

import com.project.passbook.merchantService.dao.MerchantManager;
import com.project.passbook.merchantService.model.exceptions.types.NotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindMerchantsRequest {

  @NonNull
  private List<Integer> merchantIds;


  public void validate(MerchantManager merchantManager) throws NotFoundException {
    merchantManager.findByIds(merchantIds);
  }
}
