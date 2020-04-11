package com.project.passbook.merchantService.service;

import com.project.passbook.merchantService.model.responses.Response;
import com.project.passbook.merchantService.requests.AddCouponTemplateRequest;

public interface CouponTemplateService {
  Response addCouponTemplate(AddCouponTemplateRequest addCouponTemplateRequest);
}
