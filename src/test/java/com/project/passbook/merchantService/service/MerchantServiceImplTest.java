package com.project.passbook.merchantService.service;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import com.project.passbook.merchantService.dao.MerchantManager;
import com.project.passbook.merchantService.model.codes.ErrorCode;
import com.project.passbook.merchantService.entities.Merchant;
import com.project.passbook.merchantService.model.responses.Response;
import com.project.passbook.merchantService.requests.CreateMerchantRequest;
import com.project.passbook.merchantService.requests.FindMerchantRequest;
import com.project.passbook.merchantService.requests.FindMerchantsRequest;
import java.util.List;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MerchantServiceImplTest {

  @Mock
  MerchantManager merchantManager;


  final Merchant TEST_MERCHANT_1 = Merchant.builder()
                                           .id(1)
                                           .name("testName1")
                                           .phone("231323111")
                                           .build();

  final Merchant TEST_MERCHANT_2 = Merchant.builder()
                                           .id(2)
                                           .name("testName2")
                                           .phone("231323111")
                                           .build();

  MerchantService merchantService;
  CreateMerchantRequest TEST_CREATE_MERCHANT_REQ;
  FindMerchantRequest TEST_FIND_MERCHANT_REQ;
  FindMerchantsRequest TEST_FIND_MERCHANTS_REQ;

  @BeforeClass
  public void beforeClass() {
    MockitoAnnotations.initMocks(this);
    merchantService = new MerchantServiceImpl(merchantManager);
    TEST_CREATE_MERCHANT_REQ = new CreateMerchantRequest();
    TEST_FIND_MERCHANT_REQ = new FindMerchantRequest(1);
    TEST_FIND_MERCHANTS_REQ = new FindMerchantsRequest(List.of(1, 2));
  }

  @Test
  public void givenRequest_whenAddMerchant_thenSucceed() {
    Response response = merchantService.addMerchant(TEST_CREATE_MERCHANT_REQ);
    assertEquals(response.getErrorCode(), ErrorCode.SUCCESS);
  }

  @Test
  public void givenRequest_whenFindMerchant_thenSucceed() {
    when(merchantManager.findById(TEST_MERCHANT_1.getId())).thenReturn(TEST_MERCHANT_1);
    Response response = merchantService.getMerchantById(TEST_FIND_MERCHANT_REQ);
    assertEquals(response.getErrorCode(), ErrorCode.SUCCESS);
    assertEquals((Merchant) response.getData(), TEST_MERCHANT_1);
  }

  @Test
  public void givenRequest_whenFindMerchants_thenSucceed() {
    when(merchantManager.findByIds(List.of(TEST_MERCHANT_1.getId(),
                                           TEST_MERCHANT_2.getId()))).thenReturn(List.of(TEST_MERCHANT_1, TEST_MERCHANT_2));
    Response response = merchantService.getMerchantsById(TEST_FIND_MERCHANTS_REQ);
    assertEquals(response.getErrorCode(), ErrorCode.SUCCESS);
  }
}
