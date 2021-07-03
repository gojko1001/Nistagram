package com.agent.webshop.repository;

import com.agent.webshop.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemRepository extends JpaRepository<Item, Long> {
    Item findItemById(Long id);
    List<Item> findItemsByUsername(String username);

}
