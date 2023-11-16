package com.photo.util;

import java.util.Map;

public interface IResultDto<T> {
    T getResult();

    AResponseDto getResponseData();

    Map<String, String> getMetaData();
}
