package com.jcs.malanka.coffee.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CategoryDto(UUID id, String name) {
}
