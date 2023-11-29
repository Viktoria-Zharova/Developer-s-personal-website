package org.example.model.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ImageType {
    USER("user"),
    PROJECT("project");

    public final String value;
}
