package com.diogojxs.springbootmysql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact")
    private Long id;

    @Column(name = "name_contact")
    private String name;

    @Column(name = "email_contact")
    private String email;

    @Column(name = "phone_contact")
    private String phone;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_address", referencedColumnName = "id_contact")
//    private ContactAddress contactAddress;
}