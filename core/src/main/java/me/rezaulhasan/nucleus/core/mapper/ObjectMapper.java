package me.rezaulhasan.nucleus.core.mapper;

/**
 * Date of creation 10-May-2020
 *
 * @author Md. Rezaul Hasan
 * @since 1.0
 */
@FunctionalInterface
public interface ObjectMapper<T, R> {
  R map(T object);
}
