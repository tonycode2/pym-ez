package com.pymez.backend.pymez.Services;

import org.springframework.data.domain.Page;

public interface CRUDInterface<R, U, A> { // R:CreateDto, U:UpdateDto, A: ResponseDto
    public A getById(Long id);

    public Page<A> getAll(int page, int size, String sortedBy, String sortDir);

    public A save(R requestDto);

    public A update(U updateDto);

    public void delete(Long id);

}
