package co.com.curiosity.school.formularios;

import co.com.curiosity.school.clases.OpeDescuentosCine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DescuentosCine extends JFrame {
    private JTextField txtPorcDescuento;
    private JTextField txtValorBoletas;
    private JButton btnGenerarFactura;
    private JButton btnLimpiar;
    private JButton btnCerrar;
    private JPanel pnlEmpresasCines;
    private JComboBox cboEmpresasCine;
    private JTextField txtValorMinimoDesc;
    private JTextField txtValorPagar;
    private JTextField txtCliente;
    private String rutaEmpresasCine = "D:\\DescuentosCine\\EmpresasCine.txt";
    private String rutaLog= "D:\\DescuentosCine\\factura.txt";
    public DescuentosCine() {
        add ( pnlEmpresasCines );
        setTitle ( "Mi Ciudad" );
        setSize ( 700, 700 );
        llenarCombos ();

        cboEmpresasCine.addActionListener ( new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                datosAsociadosCombo ();
            }
        } );

        btnGenerarFactura.addActionListener ( new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarLogica ();
            }
        } );

    }

    private boolean validar() {

        if ("".equals ( txtCliente.getText () )) {
            JOptionPane.showMessageDialog ( pnlEmpresasCines, "Debe ingresar el nombre del cliente" );
            return false;
        }
        if (this.cboEmpresasCine.getSelectedIndex () <= 0) {
            JOptionPane.showMessageDialog ( pnlEmpresasCines, "Debe seleccionar la empresa de cine" );
            return false;
        }
        if ("".equals ( txtValorBoletas.getText () )) {
            JOptionPane.showMessageDialog ( pnlEmpresasCines, "Debe ingresar el valor de las boletas" );
            return false;
        }
        return true;
    }

    public void cargarDatos(String ruta, JComboBox combo, String mensajeError) {
        try {
            OpeDescuentosCine opeDescuentosCine = new OpeDescuentosCine ();
            if (opeDescuentosCine.procesarCombo ( ruta )) {
                List<String> listaCombo = opeDescuentosCine.getDatosComboRN ();
                for (int i = 0; i < listaCombo.size (); i++) {
                    combo.addItem ( listaCombo.get ( i ) );
                }

            } else {
                mensajeError = opeDescuentosCine.getError ();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog ( pnlEmpresasCines, ex.getMessage () );
        }
    }

    public void llenarCombos() {
        String errorcboAnimal = "";
        String errorcboConsideracion = "";
        cargarDatos ( rutaEmpresasCine, cboEmpresasCine, errorcboAnimal );
    }

/*
public void datosAsociadosCombo() {
OpeDescuentosCine opeDescuentosCine= new OpeDescuentosCine ();
opeDescuentosCine.datosAsociadosCombo (this.cboEmpresasCine.getSelectedItem ().toString ());
this.txtValorMinimoDesc.setText ( String.valueOf ( opeDescuentosCine.getValorMinimo () ));
this.txtPorcDescuento.setText ( String.valueOf ( opeDescuentosCine.getPorcDescuento ()));

    }
*/

    public void datosAsociadosCombo() {
        switch (cboEmpresasCine.getSelectedItem ().toString ()) {
            case "Cine Colombia":
                this.txtPorcDescuento.setText ( "0.03" );
                this.txtValorMinimoDesc.setText ( "2000" );
                break;
            case "Royal Films":
                this.txtPorcDescuento.setText ( "0.025" );
                this.txtValorMinimoDesc.setText ( "3000" );
                break;
            case "Cines Atu Waku":
                this.txtPorcDescuento.setText ( "0.036" );
                this.txtValorMinimoDesc.setText ( "1900" );
                break;
            case "Cinemas Hanya":
                this.txtPorcDescuento.setText ( "0.028" );
                this.txtValorMinimoDesc.setText ( "2500" );
                break;
            case "Seleccione":
                this.txtPorcDescuento.setText ( "0.0" );
                this.txtValorMinimoDesc.setText ( "0.0" );
                break;
            default:
                JOptionPane.showMessageDialog ( pnlEmpresasCines, "Seleccion invalida" );
        }
    }

    public void procesarLogica() {

        if (validar ()) {
            OpeDescuentosCine opeDescuentosCine = new OpeDescuentosCine ();
            opeDescuentosCine.procesarLogicaOpe (
                    this.cboEmpresasCine.getSelectedItem ().toString (),
                    Double.parseDouble ( this.txtValorBoletas.getText () ),
                    Double.parseDouble ( this.txtPorcDescuento.getText () ),
                    Double.parseDouble ( this.txtValorMinimoDesc.getText () ) );
            this.txtValorPagar.setText ( String.valueOf ( opeDescuentosCine.getValorTotal () ) );
            if (!"".equals ( opeDescuentosCine.getError () )) {
                JOptionPane.showMessageDialog ( pnlEmpresasCines, opeDescuentosCine.getError () );
            } else {
                List<String> datosLog = new ArrayList<> ();
                datosLog.add ( "           Factura de venta:" );
                datosLog.add ( "Nombre del cliente : " + txtCliente.getText () );
                datosLog.add ( "Empresa Cine :" + cboEmpresasCine.getSelectedItem ().toString () );
                datosLog.add ( "Valor Boletas :" + txtValorBoletas.getText () );
                double valorReserva = (Double.parseDouble ( this.txtValorPagar.getText () ) -
                        (Double.parseDouble ( this.txtValorBoletas.getText () )));
                datosLog.add ( "Valor reserva :" + valorReserva );
                datosLog.add ( "Valor total :" + txtValorPagar.getText () );
                opeDescuentosCine.escribirLog ( rutaLog, datosLog );
            }
        }
    }

}
