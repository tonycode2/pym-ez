package com.pymez.backend.pymez.Services;

import java.util.List;

public interface CRUDInterface<R, U, A> { // R:CreateDto, U:UpdateDto, A: ResponseDto
    public A getById(Long id);

    public List<A> getAll();

    public A save(R requestDto);

    public A update(U updateDto);

    public void delete(Long id);

}
