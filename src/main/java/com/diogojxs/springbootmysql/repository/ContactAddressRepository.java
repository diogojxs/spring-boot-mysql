package com.diogojxs.springbootmysql.repository;

import com.diogojxs.springbootmysql.model.ContactAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactAddressRepository extends JpaRepository<ContactAddress, Long> {
}

