package com.bumdori.spring.config;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HostAndPort {
    private String host;
    private Integer port;
}
