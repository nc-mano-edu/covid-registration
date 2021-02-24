package com.edu.mano.covidregistration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long requestId; //TODO: Добавить связь, когда появится сущность user_request

    @ManyToOne
    private PaymentData paymentData;

    @Min(0)
    private double amountUSD;

}
