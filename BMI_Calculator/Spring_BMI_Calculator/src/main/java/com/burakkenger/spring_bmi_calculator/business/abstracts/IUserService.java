package com.burakkenger.spring_bmi_calculator.business.abstracts;

import com.burakkenger.spring_bmi_calculator.data.entity.User;

public interface IUserService<D,E> {

    public E DtoToEntity(D d);
    public D EntityToDto(E e);

    public D findByID(Long key);

    public void Insert(D d);

    public D findByUsername(String username);
    public String Update(String value, User user);
}
