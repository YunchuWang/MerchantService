package com.project.passbook.merchantService.manager;

import com.project.passbook.merchantService.dao.MerchantDao;
import com.project.passbook.merchantService.dao.MerchantManager;
import com.project.passbook.merchantService.model.entities.Merchant;
import com.project.passbook.merchantService.model.exceptions.types.ConflictException;
import com.project.passbook.merchantService.model.exceptions.types.NotFoundException;
import java.util.Collections;
import java.util.List;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MerchantManagerTest {

  final Merchant TEST_MERCHANT_1 = Merchant.builder()
                                     .id(1)
                                     .name("testName1")
                                     .phone("231323111")
                                     .build();

  final Merchant TEST_MERCHANT_2 = Merchant.builder()
                                     .id(2)
                                     .name("testName2")
                                     .phone("231323112")
                                     .build();

  MerchantManager merchantManager;
  @Mock
  MerchantDao merchantDao;

  @BeforeClass
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    merchantManager = new MerchantManager(merchantDao);
  }

  @Test
  public void givenMerchantId_whenFindById_thenMerchantFound() {
    when(merchantDao.findById(any())).thenReturn(Optional.of(TEST_MERCHANT_1));

    Merchant merchant = merchantManager.findById(TEST_MERCHANT_1.getId());

    assertEquals(TEST_MERCHANT_1,
                 merchant);
  }

  @Test(expectedExceptions = NotFoundException.class)
  public void givenNonExistentMerchantId_whenFindById_thenThrowException() {
    when(merchantDao.findById(any())).thenReturn(Optional.empty());

    merchantManager.findById(TEST_MERCHANT_1.getId());
  }

  @Test
  public void givenMerchantName_whenFindByName_thenMerchantFound() {
    when(merchantDao.findByName(any())).thenReturn(Optional.of(TEST_MERCHANT_1));

    Merchant merchant = merchantManager.findByName(TEST_MERCHANT_1.getName());

    assertEquals(TEST_MERCHANT_1,
                 merchant);
  }

  @Test(expectedExceptions = NotFoundException.class)
  public void givenNonExistentMerchantName_whenFindByName_thenThrowException() {
    when(merchantDao.findByName(any())).thenReturn(Optional.empty());

    merchantManager.findByName(TEST_MERCHANT_1.getName());
  }

  @Test
  public void givenMerchant_whenAddMerchant_thenSucceed() {
    when(merchantDao.findByName(any())).thenReturn(Optional.empty());

    merchantManager.addMerchant(TEST_MERCHANT_1);
  }

  @Test(expectedExceptions = ConflictException.class)
  public void givenDuplicateMerchant_whenAddMerchant_thenThrowException() {
    when(merchantDao.findByName(any())).thenReturn(Optional.of(TEST_MERCHANT_1));

    merchantManager.addMerchant(TEST_MERCHANT_1);
  }

  @Test(expectedExceptions = NotFoundException.class)
  public void givenNonExistingMerchants_whenFindMerchants_thenThrowException() {
    when(merchantDao.findAllById(any())).thenReturn(Collections.emptyList());

    merchantManager.findByIds(List.of(-2, -3));
  }

  @Test
  public void givenNotAllExistingMerchants_whenFindMerchants_thenSucceed() {
    when(merchantDao.findAllById(any())).thenReturn(List.of(TEST_MERCHANT_1));

    List<Merchant> merchants = merchantManager.findByIds(List.of(1, -1));

    assertEquals(merchants.size(), 1);
    assertEquals(merchants.get(0), TEST_MERCHANT_1);
  }
}
