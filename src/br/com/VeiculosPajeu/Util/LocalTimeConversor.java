package br.com.VeiculosPajeu.Util;

import java.sql.Time;
import java.time.LocalTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalTimeConversor implements AttributeConverter<LocalTime, Time> {

	@Override
	public Time convertToDatabaseColumn(LocalTime localTime) {
		return (localTime == null ? null : Time.valueOf(localTime));
	}

	@Override
	public LocalTime convertToEntityAttribute(Time time) {
		return (time == null ? null : time.toLocalTime());
	}
}