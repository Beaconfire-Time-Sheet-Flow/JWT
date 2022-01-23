package com.example.hrinterface.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Profile {
    @JsonProperty("ID")
    public int ID;
    public String name;
    @JsonProperty("SSN")
    public String SSN;
    public String date;
    public String visa;

}
