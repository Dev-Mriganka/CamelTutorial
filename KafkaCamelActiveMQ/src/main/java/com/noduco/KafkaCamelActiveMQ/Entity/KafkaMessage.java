package com.noduco.KafkaCamelActiveMQ.Entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KafkaMessage {

    private String employeeId;

    private boolean address;

    private boolean employment;

//    { "employeeId": "105", "address": true, "employment": true }


}
