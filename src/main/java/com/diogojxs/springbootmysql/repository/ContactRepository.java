package com.diogojxs.springbootmysql.repository;

import com.diogojxs.springbootmysql.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}

