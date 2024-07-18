package com.dong.base.test.aa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class TestUser {
    private Integer id;
    private String regionCode;
    private String regionName;
    private String adId;
    private Integer value;
    private Integer value2;
}
