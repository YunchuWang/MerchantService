package com.project.passbook.merchantService.dao;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.project.passbook.merchantService.MerchantServiceApplication;
import com.project.passbook.merchantService.entities.Merchant;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SpringBootTest(classes = MerchantServiceApplication.class)
public class MerchantDaoTest extends AbstractTestNGSpringContextTests {

  private final String NONEXISTENT_MERCHANT_NAME = "nonExistentMerchant";
  private final Integer NONEXISTENT_MERCHANT_ID = -1;

  @Resource
  private MerchantDao merchantDao;

  private Merchant TEST_MERCHANT_1;
  private Merchant TEST_MERCHANT_2;

  @BeforeClass
  public void setUp() throws Exception {
    // Create test data
    TEST_MERCHANT_1 = createTestMerchant();
    merchantDao.save(TEST_MERCHANT_1);
    TEST_MERCHANT_2 = createTestMerchant();
    merchantDao.save(TEST_MERCHANT_2);
  }

  @Test
  public void givenMerchantId_whenFindById_thenMerchantFound() {
    Optional<Merchant> merchant = merchantDao.findById(TEST_MERCHANT_1.getId());
    assertTrue(merchant.isPresent());
  }

  @Test
  public void givenNonExistentMerchantId_whenFindById_thenNotFound() {
    Optional<Merchant> merchant = merchantDao.findById(NONEXISTENT_MERCHANT_ID);

    assertFalse(merchant.isPresent());
  }

  @Test
  public void givenMerchantName_whenFindByName_thenMerchantFound() {
    Optional<Merchant> merchant = merchantDao.findByName(TEST_MERCHANT_1.getName());
    assertTrue(merchant.isPresent());
  }

  @Test
  public void givenNonExistentMerchantName_whenFindByName_thenNotFound() {
    Optional<Merchant> merchant = merchantDao.findByName(NONEXISTENT_MERCHANT_NAME);

    assertFalse(merchant.isPresent());
  }

  @Test
  public void givenMerchantInfo_whenCreateMerchant_thenSucceed() {
    Merchant merchant = merchantDao.save(createTestMerchant());
    assertNotNull(merchant);
  }

  @Test
  public void givenExistingMerchantIds_whenGetMerchants_thenSucceed() {
    List<Merchant> merchants = merchantDao.findAllById(List.of(1,2));

    assertEquals(merchants.size(), 2);

    assertEquals(merchants.get(0), TEST_MERCHANT_1);
    assertEquals(merchants.get(1), TEST_MERCHANT_2);
  }

  @Test
  public void givenNotAllExistingMerchantIds_whenGetMerchants_thenReturnExistingMerchants() {
    List<Merchant> merchants = merchantDao.findAllById(List.of(1,-1));

    assertEquals(merchants.size(), 1);

    assertEquals(merchants.get(0), TEST_MERCHANT_1);
  }

  @Test
  public void givenNonExistingMerchantIds_whenGetMerchants_thenReturnEmpty() {
    List<Merchant> merchants = merchantDao.findAllById(List.of(-1, -2));

    assertTrue(merchants.isEmpty());
  }

  private Merchant createTestMerchant() {
    return Merchant.builder()
                   .name(RandomStringUtils.randomAlphabetic(20))
                   .logoUrl("www.test.com")
                   .businessLicenseUrl("www.test.com/test.png")
                   .phone("9193457979")
                   .address("AddressA")
                   .build();
  }
}
