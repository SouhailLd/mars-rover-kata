package com.mars.rover.kata.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Point {
    private int location;
    private int maxLocation;
}
