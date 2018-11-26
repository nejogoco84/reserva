package co.com.curiosity.school.reglasnegocio;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class RNDescuentosCine {

    List<String> combos;
    Path path;
    String error;
    String ruta;

    public RNDescuentosCine() {
        combos = new ArrayList<> ();
        error = "";
        ruta = "";
    }
    public List<String> getCombos() {
        return combos;
    }

    public String getError() {
        return error;
    }

    public boolean validar() throws Exception {
        try {
            path = Paths.get ( ruta );
            combos = Files.readAllLines ( path );

            if (Files.readAllLines ( path ).isEmpty ()) {
                error = "Archivo Vacio";
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
        return true;
    }

    public void valoresCombo(String ruta) throws Exception {
        this.ruta = ruta;
        if (validar ()) {
            combos = new ArrayList<> ();
            try {
                combos = Files.readAllLines ( path );
            } catch (Exception e) {
                throw e;
            }
        }
    }

    public void escribirLog(String ruta, List<String> log) throws Exception {
        this.ruta = ruta;
        Path path = Paths.get ( ruta );

        try (BufferedWriter br = Files.newBufferedWriter ( path, Charset.defaultCharset (), StandardOpenOption.CREATE )) {
            for (int i = 0; i < log.size (); i++) {
                br.write ( log.get ( i ) );
                br.newLine ();
            }

        } catch (Exception e) {
            throw e;
        }
    }
}
