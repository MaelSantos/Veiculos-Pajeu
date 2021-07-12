package br.com.VeiculosPajeu.Util;

import java.time.Duration;
import java.time.LocalTime;

public class TimeUtil {

	private static TimeUtil instance;

	private TimeUtil() {
	}

	public static synchronized TimeUtil getInstance() {
		if (instance == null)
			instance = new TimeUtil();
		return instance;
	}

	public long Horas(LocalTime localTime) {

		long duration = Duration.between(localTime, LocalTime.now()).toHours();

		if (duration < 0)
			return duration *= -1;
		else
			return duration;
	}

	public LocalTime Timer(LocalTime localTime) {
		Duration duration = Duration.between(localTime, LocalTime.now());

		System.out.println(duration.toHours());

		LocalTime time;
		if (duration.toHours() < 0)
			time = LocalTime.of((int) duration.toHours() * -1, 00);
		else
			time = LocalTime.of((int) duration.toHours(), 00);

		return time;
	}
}
