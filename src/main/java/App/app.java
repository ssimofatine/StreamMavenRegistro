package App;

import Modeles.Registro;

import java.time.LocalDateTime;
import java.util.*;

public static double generarNumero(int max, int min) {
    return (Math.random() * (max - min + 1)) + min;
}

public class app {
    static void main(String[] args) {
        //Debes crear una colección de 100 registros (genérala con un for o con
        //Stream.generate que cree objetos Registro poniendo valores aleatorios de temperatura y humedad, y
        //la fecha le añada un minuto a cada registro), y deberás resolver lo siguiente utilizando Streams

        List<Registro> EjemploRegistro = new ArrayList<>();
        LocalDateTime ejemploTiempo= LocalDateTime.now();

        for (int i = 0; i < 100; i++) {
            double tempoGenerar = generarNumero(30, 10);
            double humedadGenerar = generarNumero(60, 40);
            LocalDateTime tiempoRegistro = ejemploTiempo.plusMinutes(i);
            EjemploRegistro.add(new Registro(tiempoRegistro, tempoGenerar, humedadGenerar));
        }

        //1. Filtrar los registros de temperatura que sean mayores a 25 grados, la humedad sea menor a 70 y la
        //fecha sea anterior a la fecha actual, y mostrarlos.
        System.out.println("--- 1 ---");
        EjemploRegistro.stream()
                .filter(p -> p.getTemperatura() > 25 && p.getHumedad() < 70 && p.getFechaHora().isBefore(LocalDateTime.now()))
                .forEach(System.out::println);

        //2. Encontrar el registro con la temperatura más alta y mostrar el registro completo
        System.out.println("--- 2 ---");
        Optional<Registro> maxTemp = EjemploRegistro.stream()
                .max(Comparator.comparing(Registro::getTemperatura));

    }
}
