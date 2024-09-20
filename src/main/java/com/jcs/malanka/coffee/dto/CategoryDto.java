package com.jcs.malanka.coffee.dto;

import com.jcs.malanka.coffee.validation.groups.UpdateGroup;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CategoryDto(
        @NotNull(groups = UpdateGroup.class) UUID id,
        @NotEmpty  String name
) {
}
