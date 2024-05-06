package com.abc.medicine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.abc.medicine.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // You can add custom query methods here if needed
}