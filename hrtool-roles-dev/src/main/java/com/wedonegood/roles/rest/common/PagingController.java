package com.wedonegood.roles.rest.common;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class PagingController extends RestController {

    protected <T, R> ResponseEntity<List<R>> pageResponse(Page<T> page, Function<T, R> mapper) {
        List<R> result = page.getContent().stream().map(t -> mapper.apply(t)).collect(Collectors.toList());
        return ResponseEntity.ok()
                .header("X-Total-Elements-Count", Long.toString(page.getTotalElements()))
                .header("X-Total-Pages-Count", Long.toString(page.getTotalPages()))
                .body(result);
    }

}
