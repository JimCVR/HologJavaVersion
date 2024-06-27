package com.jimcvr.HologJavaVersion.repository;

import com.jimcvr.HologJavaVersion.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository <Item, Long> {
}
