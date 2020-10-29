/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yourcad;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author MaXx
 */
public class Form_instalacaoAguaController implements Initializable {

    @FXML
    private Pane panel_instAgua;
    @FXML
    private TextField txt_hidrometroAgua;
    @FXML
    private TextField txt_codsabespAgua;
    @FXML
    private TextField txt_economiasAgua;
    @FXML
    private TextField txt_codclienteAgua;
    @FXML
    private TextField txt_tipoLigacao;
    
    static String hidrometro;
    static String codigoCliente;
    static String codigoSabesp;
    static String economias;
    static String tipoLigacao;
    @FXML
    private Button btn_salvarInstAgua;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                
    }    
        
        
    public Form_instalacaoAguaController() {
        
        
    }

    public Pane getPanel_instAgua() {
        return panel_instAgua;
    }

    public void setPanel_instAgua(Pane panel_instAgua) {
        this.panel_instAgua = panel_instAgua;
    }

    public TextField getTxt_hidrometroAgua() {
        return txt_hidrometroAgua;
    }

    public void setTxt_hidrometroAgua(TextField txt_hidrometroAgua) {
        this.txt_hidrometroAgua = txt_hidrometroAgua;
    }

    public TextField getTxt_codsabespAgua() {
        return txt_codsabespAgua;
    }

    public void setTxt_codsabespAgua(TextField txt_codsabespAgua) {
        this.txt_codsabespAgua = txt_codsabespAgua;
    }

    public TextField getTxt_economiasAgua() {
        return txt_economiasAgua;
    }

    public void setTxt_economiasAgua(TextField txt_economiasAgua) {
        this.txt_economiasAgua = txt_economiasAgua;
    }

    public TextField getTxt_codclienteAgua() {
        return txt_codclienteAgua;
    }

    public void setTxt_codclienteAgua(TextField txt_codclienteAgua) {
        this.txt_codclienteAgua = txt_codclienteAgua;
    }

    public TextField getTxt_tipoLigacao() {
        return txt_tipoLigacao;
    }

    public void setTxt_tipoLigacao(TextField txt_tipoLigacao) {
        this.txt_tipoLigacao = txt_tipoLigacao;
    }

    public static String getHidrometro() {
        return hidrometro;
    }

    public static void setHidrometro(String hidrometro) {
        Form_instalacaoAguaController.hidrometro = hidrometro;
    }

    public static String getCodigoCliente() {
        return codigoCliente;
    }

    public static void setCodigoCliente(String codigoCliente) {
        Form_instalacaoAguaController.codigoCliente = codigoCliente;
    }

    public static String getCodigoSabesp() {
        return codigoSabesp;
    }

    public static void setCodigoSabesp(String codigoSabesp) {
        Form_instalacaoAguaController.codigoSabesp = codigoSabesp;
    }

    public static String getEconomias() {
        return economias;
    }

    public static void setEconomias(String economias) {
        Form_instalacaoAguaController.economias = economias;
    }

    public static String getTipoLigacao() {
        return tipoLigacao;
    }

    public static void setTipoLigacao(String tipoLigacao) {
        Form_instalacaoAguaController.tipoLigacao = tipoLigacao;
    }

    @FXML
    private void salvarInstAgua(ActionEvent event) throws SQLException, Exception {
        String hidrometro = txt_hidrometroAgua.getText();
        String codigoCliente = txt_codclienteAgua.getText();
        String codigoSabesp = txt_codsabespAgua.getText();
        String economias = txt_economiasAgua.getText();
        String tipoLigacao = txt_tipoLigacao.getText();  
        
        Connection conn = null;
        conn = DBConexao.abrirConexao();
        Statement stm = conn.createStatement();
        String query1;
        query1 = "INSERT INTO instalacao_agua(instalacao_id," +
                     "instalacao_agua_hidrometro,\n" +
                     "instalacao_agua_cod_sabesp,\n" +
                     "instalacao_agua_cod_cliente,\n" +
                     "instalacao_agua_economias,\n" +
                     "instalacao_agua_tipo_ligacao) VALUES "
                    + "('"+ Form_CadInstalacoesController.instalacao_id +"','"
                    + hidrometro +"','"
                    + codigoSabesp +"','"
                    + codigoCliente +"','"
                    + economias +"','"
                    + tipoLigacao +"');";
            System.out.println(query1);
            stm.executeUpdate(query1);
            
            System.out.println("Salvo");
        
    }


    
    
}
