package com.ecommerce.cart.model;

import com.ecommerce.cart.enumeration.FormPayment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@Entity
@JsonIgnoreProperties
@NoArgsConstructor
public class Bag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private Client client;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itens;

    private  Double amounts;

    @Enumerated
    private FormPayment formPayment;

    private boolean closed;
}
