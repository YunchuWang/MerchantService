package com.project.passbook.merchantService.entities;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponTemplate {

  private Integer merchantId;

  private String title;

  private String summary;

  private String desc;

  private Long limit;

  private Boolean hasToken;

  private Integer background;

  private Date startTime;

  private Date endTime;
}
