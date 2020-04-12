package com.project.passbook.merchantService.dao;

import com.project.passbook.merchantService.model.entities.Merchant;
import com.project.passbook.merchantService.model.exceptions.types.ConflictException;
import com.project.passbook.merchantService.model.exceptions.types.NotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class MerchantManager {

  @Autowired private MerchantDao merchantDao;

  public Merchant findById(final int merchantId) throws NotFoundException {
    Optional<Merchant> merchant = merchantDao.findById(merchantId);
    if (merchant.isEmpty()) {
      throw new NotFoundException(String.format("Merchant with id %s does not exist.", merchantId));
    }

    return merchant.get();
  }

  public List<Merchant> findByIds(final List<Integer> merchantIds) throws NotFoundException {
    List<Merchant> merchants = merchantDao.findAllById(merchantIds);
    if (merchants.isEmpty()) {
      throw new NotFoundException(String.format("Merchants with ids %s do not exist.", merchantIds));
    }
    return merchants;
  }

  public Merchant findByName(final String merchantName) throws NotFoundException {
    Optional<Merchant> merchant = merchantDao.findByName(merchantName);
    if (merchant.isEmpty()) {
      throw new NotFoundException(
          String.format("Merchant with name %s does not exist.", merchantName));
    }

    return merchant.get();
  }

  public Merchant addMerchant(final Merchant merchant) throws ConflictException {
    log.info("Checking if merchant {} already exists", merchant.getName());
    Optional<Merchant> foundMerchant = merchantDao.findByName(merchant.getName());
    if (foundMerchant.isPresent()) {
      throw new ConflictException(String.format("Merchant %s already exist", merchant.getName()));
    }

    log.info("Saving the new merchant {}", merchant);
    return merchantDao.save(merchant);
  }
}
