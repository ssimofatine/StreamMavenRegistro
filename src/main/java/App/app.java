package App;

import Modeles.Registro;

import java.time.LocalDateTime;
import java.util.*;

public static double generarNumero(int max, int min) {
    return (Math.random() * (max - min + 1)) + min;
}

public class app {
    private static int contadorMinutos = 0;

    static void main() {

        Supplier<Registro> generadorRegistros = () -> {
            LocalDateTime fecha = LocalDateTime.now().minusHours(2).plusMinutes(contadorMinutos++);
            double temp = generarNumero(30, 10);
            double hum = generarNumero(60, 40);
            return new Registro(fecha, temp, hum);
        };

        List<Registro> registros = Stream.generate(generadorRegistros)
                .limit(100)
                .toList();

        IO.println("--- 1 ---");
        registros.stream()
                .filter(r -> r.getTemperatura() > 25.0)
                .filter(r -> r.getHumedad() < 70.0)
                .filter(r -> r.getFechaHora().isBefore(LocalDateTime.now()))
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
                .map(r -> new Registro(r.getFechaHora(), r.getTemperatura(), r.getHumedad() + 5.0))
                .forEach(IO::println);

        IO.println("--- 5 ---");
        registros.stream()
                .filter(r -> r.getHumedad() > 80.0)
                .min(Comparator.comparing(Registro::getTemperatura))
                .ifPresent(IO::println);

        IO.println("--- 6 ---");
        boolean condicionCumplida = registros.stream()
                .anyMatch(r -> r.getTemperatura() > 30.0 &&
                        r.getHumedad() > 90.0 &&
                        r.getFechaHora().toLocalDate().isEqual(LocalDate.now()));
        IO.println(condicionCumplida ? "SI hay un registro " : "NO hay registros ");

        IO.println("--- 7 ---");
        registros.stream()
                .skip(5)
                .limit(10)
                .forEach(IO::println);

        IO.println("--- 8 ---");
        registros.stream()
                .sorted(Comparator.comparing(Registro::getFechaHora))
                .forEach(IO::println);

        IO.println("--- 9 ---");
        long totalCalor = registros.stream()
                .filter(r -> r.getTemperatura() > 35.0)
                .count();
        IO.println(totalCalor);

        IO.println("--- 10 ---");
        registros.stream()
                .mapToDouble(Registro::getTemperatura)
                .average()
                .ifPresent(IO::println);
    }
}
