import java.util.ArrayList;

public class CajaRetoExtendido {

    static class Persona {
        int minutoLlegada;
        boolean esPreferente;

        Persona(int minutoLlegada, boolean esPreferente) {
            this.minutoLlegada = minutoLlegada;
            this.esPreferente = esPreferente;
        }
    }

    public static void main(String[] args) {
        int minutosTotales = 2 * 60;
        ArrayList<Persona> fila = new ArrayList<>();
        int atendidos = 0;
        int aburridos = 0;

        for (int min = 1; min <= minutosTotales; min++) {

            if (min >= 20 && min % 5 == 0) {
                for (int i = fila.size() - 1; i >= 0; i--) {
                    if (min - fila.get(i).minutoLlegada > 8) {
                        if (Math.random() < 0.30) {
                            fila.remove(i);
                            aburridos++;
                        }
                    }
                }
            }

            if (Math.random() < 0.6) {
                if (puedeIncorporarse(fila)) {
                    fila.add(new Persona(min, false));
                }
            }

            if (min >= 20) {
                if (Math.random() < 0.15) {
                    if (puedeIncorporarse(fila)) {
                        int pos = -1;
                        for (int i = fila.size() - 1; i >= 0; i--) {
                            if (fila.get(i).esPreferente) {
                                pos = i;
                                break;
                            }
                        }
                        if (pos != -1) {
                            fila.add(pos + 1, new Persona(min, true));
                        } else {
                            fila.add(0, new Persona(min, true));
                        }
                    }
                }

                if (Math.random() < 0.10) {
                    if (!fila.isEmpty() && puedeIncorporarse(fila)) {
                        int posConocido = (int) (Math.random() * fila.size());
                        fila.add(posConocido + 1, new Persona(min, false));
                    }
                }

                if (Math.random() < 0.05) {
                }
            }

            if (Math.random() < 0.4) {
                if (!fila.isEmpty()) {
                    fila.remove(0);
                    atendidos++;
                }
            }

            if (min % 15 == 0 && fila.size() > 25) {
                System.out.println("Porfavor vayan pasando por esta caja en orden de fila.");
                int extraAtendidos = Math.min(5, fila.size());
                for (int i = 0; i < extraAtendidos; i++) {
                    fila.remove(0);
                    atendidos++;
                }
            }

            System.out.println("Minuto " + min + ": " + fila.size() + " metros");
        }

        System.out.println("----------------------------------------------------");
        System.out.println("Resultados al cierre:");
        System.out.println("Personas atendidas: " + atendidos);
        System.out.println("Personas aburridas que se fueron: " + aburridos);
        System.out.println("Personas en fila: " + fila.size() + " (" + fila.size() + " metros)");
    }

    private static boolean puedeIncorporarse(ArrayList<Persona> fila) {
        if (fila.size() > 30) {
            return Math.random() >= 0.5;
        }
        return true;
    }
}