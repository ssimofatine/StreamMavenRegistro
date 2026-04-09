package Modeles;
import java.time.LocalDateTime;
import java.util.*;

public class Registro {

    private LocalDateTime fechaHora;
    private Double temperatura;
    private Double humedad;

    // Constructor
    public Registro(LocalDateTime fechaHora, Double temperatura, Double humedad) {
        this.fechaHora = fechaHora;
        this.temperatura = temperatura;
        this.humedad = humedad;
    }


    // Getter y Steers


    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getHumedad() {
        return humedad;
    }

    public void setHumedad(Double humedad) {
        this.humedad = humedad;
    }

    // ToString Con StringBuffer


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Registro{");
        sb.append("fechaHora=").append(fechaHora);
        sb.append(", temperatura=").append(temperatura);
        sb.append(", humedad=").append(humedad);
        sb.append('}');
        return sb.toString();
    }
}
