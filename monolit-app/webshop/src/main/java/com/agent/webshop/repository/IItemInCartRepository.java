package com.agent.webshop.repository;

import com.agent.webshop.domain.ItemInCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemInCartRepository extends JpaRepository<ItemInCart, Long> {
    ItemInCart findItemInCartById(Long id);
}
