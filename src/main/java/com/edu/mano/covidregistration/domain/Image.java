package com.edu.mano.covidregistration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private long roleId;




}
