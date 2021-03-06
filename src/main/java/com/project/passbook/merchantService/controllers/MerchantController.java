package com.project.passbook.merchantService.controllers;

import com.project.passbook.merchantService.entities.Merchant;
import com.project.passbook.merchantService.model.responses.Response;
import com.project.passbook.merchantService.requests.CreateMerchantRequest;
import com.project.passbook.merchantService.requests.FindMerchantRequest;
import com.project.passbook.merchantService.requests.FindMerchantsRequest;
import com.project.passbook.merchantService.service.MerchantService;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class MerchantController {

  private MerchantService merchantService;

  @PostMapping(value = "/merchants")
  @ResponseBody
  public ResponseEntity<Response<Merchant>> createMerchant(@Valid @RequestBody CreateMerchantRequest request) {
    log.info("CreatingMerchant: {}", request);
    Response<Merchant> response = merchantService.addMerchant(request);

    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "/merchants/{merchantId}")
  @ResponseBody
  public ResponseEntity<Response<Merchant>> getMerchant(@RequestBody FindMerchantRequest request,
      @PathVariable int merchantId) {
    log.info("FindingMerchant: {}", request);
    Response<Merchant> response = merchantService.getMerchantById(request);

    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "/merchants")
  @ResponseBody
  public ResponseEntity<Response<List<Merchant>>> getMerchants(@RequestBody FindMerchantsRequest request) {
    log.info("FindingMerchants: {}", request);
    Response<List<Merchant>> response = merchantService.getMerchantsById(request);

    return ResponseEntity.ok(response);
  }
}
