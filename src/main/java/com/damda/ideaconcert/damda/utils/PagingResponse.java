package com.damda.ideaconcert.damda.utils;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class PagingResponse {
    private int page;
    private int perPage;
    private int totalPage;
    private int totalRecordCount;
    private Object data;
}
