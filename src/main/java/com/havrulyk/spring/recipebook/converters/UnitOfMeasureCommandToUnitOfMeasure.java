package com.havrulyk.spring.recipebook.converters;

import com.havrulyk.spring.recipebook.commands.UnitOfMeasureCommand;
import com.havrulyk.spring.recipebook.domain.UnitOfMeasure;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if(source == null){
            return null;
        }
        final UnitOfMeasure target = new UnitOfMeasure();
        target.setId(source.getId());
        target.setDescription(source.getDescription());
        return target;
    }
}
