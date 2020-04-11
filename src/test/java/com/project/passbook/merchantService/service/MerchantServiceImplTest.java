package com.project.passbook.merchantService.service;

import static org.testng.Assert.assertEquals;

import com.project.passbook.merchantService.dao.MerchantManager;
import com.project.passbook.merchantService.model.codes.ErrorCode;
import com.project.passbook.merchantService.model.responses.Response;
import com.project.passbook.merchantService.requests.CreateMerchantRequest;
import com.project.passbook.merchantService.requests.FindMerchantRequest;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MerchantServiceImplTest {

  @Mock
  MerchantManager merchantManager;

  MerchantService merchantService;
  CreateMerchantRequest TEST_CREATE_MERCHANT_REQ;
  FindMerchantRequest TEST_FIND_MERCHANT_REQ;

  @BeforeClass
  public void beforeClass() {
    MockitoAnnotations.initMocks(this);
    merchantService = new MerchantServiceImpl(merchantManager);
    TEST_CREATE_MERCHANT_REQ = new CreateMerchantRequest();
    TEST_FIND_MERCHANT_REQ = new FindMerchantRequest(1);
  }

  @Test
  public void givenRequest_whenAddMerchant_thenSucceed() {
    Response response = merchantService.addMerchant(TEST_CREATE_MERCHANT_REQ);
    assertEquals(response.getErrorCode(), ErrorCode.SUCCESS);
  }

  @Test
  public void givenRequest_whenFindMerchant_thenSucceed() {
    Response response = merchantService.getMerchantById(TEST_FIND_MERCHANT_REQ);
    assertEquals(response.getErrorCode(), ErrorCode.SUCCESS);
  }
}
