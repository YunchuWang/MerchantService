package com.project.passbook.merchantService.service;

import com.project.passbook.merchantService.dao.MerchantManager;
import com.project.passbook.merchantService.model.codes.ErrorCode;
import com.project.passbook.merchantService.model.entities.Merchant;
import com.project.passbook.merchantService.model.responses.Response;
import com.project.passbook.merchantService.requests.CreateMerchantRequest;
import com.project.passbook.merchantService.requests.FindMerchantRequest;
import com.project.passbook.merchantService.requests.FindMerchantsRequest;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class MerchantServiceImpl implements MerchantService {

  @Autowired
  private MerchantManager merchantManager;

  @Override
  public Response addMerchant(CreateMerchantRequest createMerchantRequest) {
    Merchant merchant = createMerchantRequest.toDao();

    merchantManager.addMerchant(merchant);

    return new Response(ErrorCode.SUCCESS,
                        null,
                        merchant);
  }

  @Override
  public Response getMerchantById(FindMerchantRequest findMerchantRequest) {

    Merchant merchant = merchantManager.findById(findMerchantRequest.getMerchantId());

    return new Response(ErrorCode.SUCCESS,
                        null,
                        merchant);
  }

  @Override
  public Response getMerchantsById(FindMerchantsRequest findMerchantsRequest) {
    List<Integer> merchantIds = findMerchantsRequest.getMerchantIds();
    List<Merchant> merchants = merchantManager.findByIds(merchantIds);

    return new Response(merchants.size() < merchantIds.size()? ErrorCode.NOT_FOUND : ErrorCode.SUCCESS, null, merchants);
  }
}
