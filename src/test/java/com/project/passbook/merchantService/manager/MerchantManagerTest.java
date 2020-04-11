package com.project.passbook.merchantService.manager;

import com.project.passbook.merchantService.dao.MerchantDao;
import com.project.passbook.merchantService.dao.MerchantManager;
import com.project.passbook.merchantService.model.entities.Merchant;
import com.project.passbook.merchantService.model.exceptions.types.ConflictException;
import com.project.passbook.merchantService.model.exceptions.types.NotFoundException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MerchantManagerTest {

  Merchant TEST_MERCHANT = Merchant.builder()
                                   .id(2)
                                   .name("testName")
                                   .phone("9193457979")
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
    when(merchantDao.findById(any())).thenReturn(Optional.of(TEST_MERCHANT));

    Merchant merchant = merchantManager.findById(TEST_MERCHANT.getId());

    assertEquals(TEST_MERCHANT,
                 merchant);
  }

  @Test(expectedExceptions = NotFoundException.class)
  public void givenNonExistentMerchantId_whenFindById_thenThrowException() {
    when(merchantDao.findById(any())).thenReturn(Optional.empty());

    merchantManager.findById(TEST_MERCHANT.getId());
  }

  @Test
  public void givenMerchantName_whenFindByName_thenMerchantFound() {
    when(merchantDao.findByName(any())).thenReturn(Optional.of(TEST_MERCHANT));

    Merchant merchant = merchantManager.findByName(TEST_MERCHANT.getName());

    assertEquals(TEST_MERCHANT,
                 merchant);
  }

  @Test(expectedExceptions = NotFoundException.class)
  public void givenNonExistentMerchantName_whenFindByName_thenThrowException() {
    when(merchantDao.findByName(any())).thenReturn(Optional.empty());

    merchantManager.findByName(TEST_MERCHANT.getName());
  }

  @Test
  public void givenMerchant_whenAddMerchant_thenSucceed() {
    when(merchantDao.findByName(any())).thenReturn(Optional.empty());

    merchantManager.addMerchant(TEST_MERCHANT);
  }

  @Test(expectedExceptions = ConflictException.class)
  public void givenDuplicateMerchant_whenAddMerchant_thenThrowException() {
    when(merchantDao.findByName(any())).thenReturn(Optional.of(TEST_MERCHANT));

    merchantManager.addMerchant(TEST_MERCHANT);
  }
}
