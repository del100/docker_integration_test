package com.ves.application.jms.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RandomLongValue implements Serializable {
    private static final long serialVersionUID = 300002228391717363L;
    private String randomId;
}
