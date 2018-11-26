package co.com.curiosity.school.clases;

import co.com.curiosity.school.reglasnegocio.RNDescuentosCine;

import java.util.List;

public class OpeDescuentosCine {

    private String nombreCliente;
    private String empresaCine;
    private double porcDescuento;
    private double valorBoleta;
    private double valorMinimo;
    private double valorTotal;
    private double valorDescuento;
    private String error;
    private List<String> datosComboRN;

    public OpeDescuentosCine() {
        nombreCliente = "";
        empresaCine = "";
        porcDescuento = -1;
        valorBoleta = -1;
        valorMinimo = -1;
        valorDescuento = -1;
        error = "";
        valorTotal = -1;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<String> getDatosComboRN() {
        return datosComboRN;
    }


    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getEmpresaCine() {
        return empresaCine;
    }

    public void setEmpresaCine(String empresaCine) {
        this.empresaCine = empresaCine;
    }

    public double getPorcDescuento() {
        return porcDescuento;
    }

    public void setPorcDescuento(double porcDescuento) {
        this.porcDescuento = porcDescuento;
    }

    public double getValorBoleta() {
        return valorBoleta;
    }

    public void setValorBoleta(double valorBoleta) {
        this.valorBoleta = valorBoleta;
    }

    public double getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(double valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public double getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(double valorDescuento) {
        this.valorDescuento = valorDescuento;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }



    public void procesarLogicaOpe(String empresaCine, double valorBoleta, double porcDescuento,
                                  double valorMinimo ) {
        this.error = "";
        valorDescuento = valorBoleta * porcDescuento;
        if (valorDescuento <= valorMinimo) {
            valorTotal = valorBoleta + valorMinimo;
        } else {
            valorTotal = valorBoleta + valorDescuento;
        }
    }

    public boolean procesarCombo(String ruta) throws Exception {
        try {
            RNDescuentosCine rnDescuentosCine = new RNDescuentosCine ();
            rnDescuentosCine.valoresCombo ( ruta );
            error = rnDescuentosCine.getError ();
            if ("".equals ( error )) {
                datosComboRN = rnDescuentosCine.getCombos ();
                return true;
            }
            return false;

        } catch (Exception ex) {
            throw ex;
        }
    }

    public void escribirLog(String ruta, List<String> lista) {
        RNDescuentosCine rnDescuentosCine = new RNDescuentosCine ();
        try {
            rnDescuentosCine.escribirLog ( ruta, lista );
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }
}
