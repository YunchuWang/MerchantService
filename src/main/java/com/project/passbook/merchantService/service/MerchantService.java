package com.project.passbook.merchantService.service;

import com.project.passbook.merchantService.model.responses.Response;
import com.project.passbook.merchantService.requests.CreateMerchantRequest;
import com.project.passbook.merchantService.requests.FindMerchantRequest;

public interface MerchantService {

  Response addMerchant(CreateMerchantRequest createMerchantRequest);

  Response getMerchantById(FindMerchantRequest findMerchantRequest);
}
