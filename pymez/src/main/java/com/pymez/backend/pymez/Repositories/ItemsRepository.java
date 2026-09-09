package com.pymez.backend.pymez.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pymez.backend.pymez.Models.ItemModel;

public interface ItemsRepository extends JpaRepository<ItemModel, Long>{

}
