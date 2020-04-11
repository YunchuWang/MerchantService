package com.project.passbook.merchantService.controllers;

import com.project.passbook.merchantService.model.entities.Merchant;
import com.project.passbook.merchantService.model.responses.Response;
import com.project.passbook.merchantService.requests.CreateMerchantRequest;
import com.project.passbook.merchantService.requests.FindMerchantRequest;
import com.project.passbook.merchantService.service.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MerchantController {

  private MerchantService merchantService;

  public MerchantController(MerchantService merchantService) {
    this.merchantService = merchantService;
  }

  @PostMapping(value = "/merchant")
  @ResponseBody
  public ResponseEntity<Response<Merchant>> createMerchant(@RequestBody CreateMerchantRequest request) {
    log.info("CreatingMerchant: {}", request);
    Response<Merchant> response = merchantService.addMerchant(request);

    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "/merchant")
  @ResponseBody
  public ResponseEntity<Response<Merchant>> getMerchant(@RequestBody FindMerchantRequest request) {
    log.info("FindingMerchant: {}", request);
    Response<Merchant> response = merchantService.getMerchantById(request);

    return ResponseEntity.ok(response);
  }
}
