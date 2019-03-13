package com.dhcc.ms.feign;

import java.io.IOException;
import java.lang.reflect.Type;

import feign.Response;
import feign.codec.Decoder;

public class FeignCustomDecoder implements Decoder {
    private final FeignCustomResponseMapper mapper;
    private final Decoder delegate;

    FeignCustomDecoder(FeignCustomResponseMapper mapper, Decoder decoder) {
      this.mapper = mapper;
      this.delegate = decoder;
    }

    public Object decode(Response response, Type type) throws IOException {
      return delegate.decode(mapper.map(response, type), type);
    }
}
