package com.burakkenger.spring_bmi_calculator.business.concretes;

import com.burakkenger.spring_bmi_calculator.business.dto.BmiDto;
import com.burakkenger.spring_bmi_calculator.business.abstracts.IBmiService;
import com.burakkenger.spring_bmi_calculator.data.entity.Bmi;
import com.burakkenger.spring_bmi_calculator.data.entity.User;
import com.burakkenger.spring_bmi_calculator.data.repository.IBmiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class BmiService implements IBmiService<BmiDto, Bmi> {

    private final IBmiRepository bmiRepository;
    @Override
    public Bmi DtoToEntity(BmiDto bmiDto) {
        return new ModelMapper().map(bmiDto, Bmi.class);
    }

    @Override
    public BmiDto EntityToDto(Bmi bmi) {
        return new ModelMapper().map(bmi, BmiDto.class);
    }

    @Override
    public BmiDto findByID(Long key) {
        return null;
    }

    @Override
    public void Insert(BmiDto bmiDto) {
        bmiRepository.save(DtoToEntity(bmiDto));
    }

    @Override
    public BmiDto getByUserID(Long key) {
        return EntityToDto(bmiRepository.findByUser_Id(key));
    }

    @Override
    public List<BmiDto> getByAllUserID(Long key) {
        Iterable<Bmi> bmiIterable = bmiRepository.findAllByUser_Id(key);
        List<BmiDto> bmiDtoList = new ArrayList<>();
        for (Bmi bmi : bmiIterable) {
            bmiDtoList.add(EntityToDto(bmi));
        }
        return bmiDtoList;
    }

    @Override
    public void DeleteByID(Long key) {
        bmiRepository.deleteById(key);
    }
}
