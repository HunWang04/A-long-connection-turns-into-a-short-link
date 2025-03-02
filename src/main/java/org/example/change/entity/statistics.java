package org.example.change.entity;

import lombok.*;
import org.joda.time.DateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class statistics {
    private String effective;
    private String ineffective;
    private String code;
    private DateTime Date;
}
