package com.project.passbook.merchantService.service;

import com.google.gson.Gson;
import com.project.passbook.merchantService.constants.Constants;
import com.project.passbook.merchantService.dao.MerchantManager;
import com.project.passbook.merchantService.model.codes.ErrorCode;
import com.project.passbook.merchantService.model.responses.Response;
import com.project.passbook.merchantService.requests.AddCouponTemplateRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class CouponTemplateServiceImpl implements CouponTemplateService {

  @Autowired
  private MerchantManager merchantManager;
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  private Gson gson = new Gson();

  @Override
  public Response addCouponTemplate(AddCouponTemplateRequest addCouponTemplateRequest) {
    log.info("Adding the coupon template {}",
             addCouponTemplateRequest);

    addCouponTemplateRequest.validate(merchantManager);

    kafkaTemplate.send(Constants.COUPON_TEMPLATE_TOPIC, gson.toJson(addCouponTemplateRequest.toDao()));

    return new Response(ErrorCode.SUCCESS, null, null);
  }
}
