
package com.ves.application.jms.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
public class RandomLongValue implements Serializable {
    private static final long serialVersionUID = 300002228391717363L;
    private String randomId;
}
