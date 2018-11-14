package com.example.demo.dao;

import com.example.demo.entity.jornal.JournalItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JournalRepository extends JpaRepository<JournalItem, Long>, JpaSpecificationExecutor<JournalItem> {
}
