package com.burakkenger.spring_bmi_calculator.business.abstracts;

import java.util.List;

public interface IBmiService<D,E> {

    public E DtoToEntity(D d);
    public D EntityToDto(E e);

    public D findByID(Long key);
    public void Insert(D d);
    public D getByUserID(Long key);
    List<D> getByAllUserID(Long key);
    public void DeleteByID(Long key);
}
