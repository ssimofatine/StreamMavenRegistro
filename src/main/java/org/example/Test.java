package org.example;

import Modeles.Registro;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<Registro> registros = new ArrayList<>();
        LocalDateTime tiempoBase = LocalDateTime.now().minusMinutes(50);

        for (int i = 0; i < 100; i++) {
            double tempRandom = (Math.random() * 30) + 10;
            double humRandom = (Math.random() * 60) + 40;
            LocalDateTime tiempoRegistro = tiempoBase.plusMinutes(i);
            registros.add(new Registro(tiempoRegistro, tempRandom, humRandom));
        }

        System.out.println("--- 1 ---");
        registros.stream()
                .filter(r -> r.getTemperatura() > 25 && r.getHumedad() < 70 && r.getFechaHora().isBefore(LocalDateTime.now()))
                .forEach(r -> System.out.println(r.toString()));

        System.out.println("\n--- 2 ---");
        Registro maxTemp = registros.stream()
                .max(Comparator.comparing(r -> r.getTemperatura()))
                .orElse(null);
        System.out.println(maxTemp.toString());

        System.out.println("\n--- 3 ---");
        List<LocalDateTime> fechas = registros.stream()
                .map(r -> r.getFechaHora())
                .collect(Collectors.toList());
        System.out.println("Lista de fechas extraidas: " + fechas.size() + " fechas.");

        System.out.println("\n--- 4 ---");
        registros.stream()
                .map(r -> new Registro(r.getFechaHora(), r.getTemperatura(), r.getHumedad() + 5))
                .forEach(r -> System.out.println(r.toString()));

        System.out.println("\n--- 5 ---");
        Registro minTempHum80 = registros.stream()
                .filter(r -> r.getHumedad() > 80)
                .min(Comparator.comparing(r -> r.getTemperatura()))
                .orElse(null);
        if (minTempHum80 != null) {
            System.out.println(minTempHum80.toString());
        }

        System.out.println("\n--- 6 ---");
        boolean existeExtremo = registros.stream()
                .anyMatch(r -> r.getTemperatura() > 30 && r.getHumedad() > 90 && r.getFechaHora().toLocalDate().equals(LocalDateTime.now().toLocalDate()));
        if (existeExtremo) {
            System.out.println("SI hay un registro que cumple las condiciones.");
        } else {
            System.out.println("NO hay ningun registro con esas condiciones.");
        }

        System.out.println("\n--- 7 ---");
        registros.stream()
                .skip(5)
                .limit(10)
                .forEach(r -> System.out.println(r.toString()));

        System.out.println("\n--- 8 ---");
        registros.stream()
                .sorted(Comparator.comparing(r -> r.getFechaHora()))
                .forEach(r -> System.out.println(r.toString()));

        System.out.println("\n--- 9 ---");
        long countTemp35 = registros.stream()
                .filter(r -> r.getTemperatura() > 35)
                .count();
        System.out.println("Total de registros con temperatura > 35: " + countTemp35);

        System.out.println("\n--- 10 ---");
        double promedio = registros.stream()
                .mapToDouble(r -> r.getTemperatura())
                .average()
                .orElse(0.0);
        System.out.println("Temperatura promedio: " + promedio);
    }
}