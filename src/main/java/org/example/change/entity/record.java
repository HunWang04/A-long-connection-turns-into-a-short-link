package org.example.change.entity;

import lombok.*;
import org.joda.time.DateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class record {
    private String client;
    private String Short;
    private String Long;
    private String code;
    private DateTime time;
    private String UA;
    private String cookie;
    private String browser;
    private String os;
    private String status;
}
