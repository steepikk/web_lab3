package ru.itmo.web.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.web.valiadtion.ValidationR;
import ru.itmo.web.valiadtion.ValidationX;
import ru.itmo.web.valiadtion.ValidationY;

import java.beans.JavaBean;
import java.io.Serializable;

@Entity
@Table(name = "results")
@JavaBean
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ValidationX
    private Double x;

    @ValidationY
    private Double y;

    @ValidationR
    private Double r;

    private boolean result;
}
