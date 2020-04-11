package com.project.passbook.merchantService.dao;

import com.project.passbook.merchantService.model.entities.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface MerchantDao extends JpaRepository<Merchant, Integer> {

  Optional<Merchant> findById(Integer id);

  Optional<Merchant> findByName(String merchantName);

  Merchant save(Merchant s);
}
