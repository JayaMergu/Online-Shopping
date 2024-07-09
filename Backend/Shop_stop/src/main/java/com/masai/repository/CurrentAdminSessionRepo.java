package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.CurrentAdminSession;

@Repository
public interface CurrentAdminSessionRepo extends JpaRepository<CurrentAdminSession, Integer> {

    Optional<CurrentAdminSession> findByKey(String key);

    Optional<CurrentAdminSession> findByAdminId(Integer adminId);

}
