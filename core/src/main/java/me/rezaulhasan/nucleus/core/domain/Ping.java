package me.rezaulhasan.nucleus.core.domain;

import lombok.Builder;
import lombok.Value;

/**
 * Date of creation 10-May-2020
 *
 * @author Md. Rezaul Hasan
 * @since 1.0
 */
@Value
@Builder
public class Ping {
  String message;
}
