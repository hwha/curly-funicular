package com.aw.boilerplate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SampleResponse {
    @Schema(description = "아이디", example = "33")
    private long id;
    @Schema(description = "샘플이름", example = "철수")
    private String name;
    @Schema(description = "샘플이메일", example = "sample@sample.com")
    private String email;

    @Builder
    public SampleResponse(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
