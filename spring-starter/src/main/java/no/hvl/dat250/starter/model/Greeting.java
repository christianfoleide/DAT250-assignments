package no.hvl.dat250.starter.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Greeting {

    private final Long id;
    private final String content;

}
