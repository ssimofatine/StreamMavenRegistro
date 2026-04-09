package App;


import Modeles.Registro;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class app {
    public static double generarNumero(int max, int min) {
        return (Math.random() * (max - min + 1)) + min;
    }

    private static int contadorMinutos = 0;

    static void main() {

        Supplier<Registro> generadorRegistros = () -> {
            int contadorMinutos1;
            contadorMinutos1 = contadorMinutos++;
            LocalDateTime fecha = LocalDateTime.now().minusHours(2).plusMinutes(contadorMinutos1);
            double temp = generarNumero(30, 10);
            double hum = generarNumero(60, 40);
            return new Registro(fecha, temp, hum);
        };

        List<Registro> registros = Stream.generate(generadorRegistros)
                .limit(100)
                .toList();

        IO.println("--- 1 ---");
        registros.stream()
                .filter(p -> p.getTemperatura() > 25.0)
                .filter(p -> p.getHumedad() < 70.0)
                .filter(p -> p.getFechaHora().isBefore(LocalDateTime.now()))
                .forEach(IO::println);

        IO.println("--- 2 ---");
        registros.stream()
                .max(Comparator.comparing(Registro::getTemperatura))
                .ifPresent(IO::println);

        IO.println("--- 3 ---");
        List<LocalDateTime> fechas = registros.stream()
                .map(Registro::getFechaHora)
                .toList();
        fechas.forEach(IO::println);

        IO.println("--- 4 ---");
        registros.stream()
                .map(p -> new Registro(p.getFechaHora(), p.getTemperatura(), p.getHumedad() + 5.0))
                .forEach(IO::println);

        IO.println("--- 5 ---");
        registros.stream()
                .filter(p -> p.getHumedad() > 80.0)
                .min(Comparator.comparing(Registro::getTemperatura))
                .ifPresent(IO::println);

        IO.println("--- 6 ---");
        boolean condicionCumplida;
        condicionCumplida = registros.stream()
                .anyMatch(p -> p.getTemperatura() > 30.0 &&
                        p.getHumedad() > 90.0 &&
                        p.getFechaHora().toLocalDate().isEqual(LocalDate.now()));
        String mensage1 , mensage2;
        mensage1 = "si hay algún registro que cumple esta condición ";
        mensage2 = "NO hay registros ";
        IO.println(condicionCumplida ? mensage1 : mensage2);


        IO.println("--- 7 ---");
        registros.stream()
                .skip(5)
                .limit(10)
                .forEach(IO::println);

        IO.println("--- 8 ---");

    }
}
