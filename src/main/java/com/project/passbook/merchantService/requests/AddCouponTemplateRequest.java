package com.project.passbook.merchantService.requests;

import com.project.passbook.merchantService.dao.MerchantManager;
import com.project.passbook.merchantService.entities.CouponTemplate;
import com.project.passbook.merchantService.model.exceptions.types.NotFoundException;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCouponTemplateRequest {

  @NonNull
  private Integer merchantId;

  @NonNull
  private String title;

  @NonNull
  private String summary;

  @NonNull
  private String desc;

  @NonNull
  private Long limit;

  @NonNull
  private Boolean hasToken;

  @NonNull
  private Integer background;

  @NonNull
  private Date startTime;

  @NonNull
  private Date endTime;

  // Validate whether
  public void validate(MerchantManager merchantManager) throws NotFoundException {
    merchantManager.findById(merchantId);
  }

  public CouponTemplate toDao() {
    return CouponTemplate.builder()
                         .background(background)
                         .desc(desc)
                         .endTime(endTime)
                         .startTime(startTime)
                         .hasToken(hasToken)
                         .limit(limit)
                         .merchantId(merchantId)
                         .summary(summary)
                         .title(title)
                         .build();
  }
}
