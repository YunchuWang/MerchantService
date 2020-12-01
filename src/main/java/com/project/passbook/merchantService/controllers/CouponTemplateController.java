package com.project.passbook.merchantService.controllers;

import com.project.passbook.merchantService.model.responses.Response;
import com.project.passbook.merchantService.requests.AddCouponTemplateRequest;
import com.project.passbook.merchantService.service.CouponTemplateService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CouponTemplateController {

  private CouponTemplateService couponTemplateService;

  public CouponTemplateController(CouponTemplateService couponTemplateService) {
    this.couponTemplateService = couponTemplateService;
  }

  @PostMapping(value = "/merchants/coupontemplate")
  @ResponseBody
  public ResponseEntity<Response> createCouponTemplate(@Valid @RequestBody AddCouponTemplateRequest request) {
    log.info("CreatingCouponTemplate: {}", request);
    Response response = couponTemplateService.addCouponTemplate(request);

    return ResponseEntity.ok(response);
  }
}
