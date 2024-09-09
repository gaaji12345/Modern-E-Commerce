package com.gaaji.Gaaji.Eccomerce.repo;/*  gaajiCode
    99
    09/09/2024
    */

import com.gaaji.Gaaji.Eccomerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Long> {
}
