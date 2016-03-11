package mario;


import java.util.ArrayList;

public class Temporada {
    private ArrayList<String> capitulos;
    private ArrayList<String> idiomas;

    public Temporada() {
    }

    public Temporada(ArrayList<String> capitulos, ArrayList<String> idiomas) {
        this.capitulos = capitulos;
        this.idiomas = idiomas;
    }

    public ArrayList<String> getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(ArrayList<String> capitulos) {
        this.capitulos = capitulos;
    }

    public ArrayList<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(ArrayList<String> idiomas) {
        this.idiomas = idiomas;
    }

    @Override
    public String toString() {
        return "Temporada{" +
                "capitulos=" + capitulos +
                ", idiomas=" + idiomas +
                '}';
    }
}
