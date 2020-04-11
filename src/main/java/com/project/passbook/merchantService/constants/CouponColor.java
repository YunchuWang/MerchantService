package com.project.passbook.merchantService.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum CouponColor {
  RED(1, "Red"),
  GREEN(2, "Green"),
  Blue(3, "Blue");

  @Getter @Setter private Integer code;
  @Getter @Setter private String color;
}
