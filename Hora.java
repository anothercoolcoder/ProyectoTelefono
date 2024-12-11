import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hora {
    public String obtenerFechaActual() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDateTime.now().format(formatter);
    }

    public String obtenerHoraActual() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
