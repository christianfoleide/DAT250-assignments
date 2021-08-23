package no.hvl.dat250.herokudemo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Hello {

    private final String message;
    private final int statusCode;

}
