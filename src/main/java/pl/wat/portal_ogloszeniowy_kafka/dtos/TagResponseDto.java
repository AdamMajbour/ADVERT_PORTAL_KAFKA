package pl.wat.portal_ogloszeniowy_kafka.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TagResponseDto {
    private Long id;
    private String name;
}
