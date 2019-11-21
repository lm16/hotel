package com.hotel.image.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "image")
public class Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "i_id")
    private Long id;
    @Column(name = "i_room_type_id")
    private Long roomId;
    @Column(name = "i_recreation_id")
    private Long recreationId;
    @Column(name = "i_food_id")
    private Long foodId;
    @Column(name = "url")
    private String url;

}
