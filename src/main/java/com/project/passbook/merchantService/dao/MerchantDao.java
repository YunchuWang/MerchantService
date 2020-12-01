package com.project.passbook.merchantService.dao;

import com.project.passbook.merchantService.entities.Merchant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface MerchantDao extends JpaRepository<Merchant, Integer> {

  @Override
  Optional<Merchant> findById(Integer id);

  @Override
  List<Merchant> findAllById(Iterable<Integer> iterable);

  @Override
  Merchant save(Merchant s);

  Optional<Merchant> findByName(String merchantName);
}
