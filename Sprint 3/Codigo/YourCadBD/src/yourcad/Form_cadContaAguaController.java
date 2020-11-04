/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yourcad;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author mateu
 */
public class Form_cadContaAguaController implements Initializable {

    @FXML
    private TextField txt_ContaAguaInstalacao;
    @FXML
    private TextField txt_ContaAguaCompetencia;
    @FXML
    private TextField txt_ContaAguaVencimento;
    @FXML
    private TextField txt_ContaAguaDataLeituraAnt;
    @FXML
    private TextField txt_ContaAguaDataLeituraAtual;
    @FXML
    private TextField txt_ContaAguaPrevProxLeitura;
    @FXML
    private TextField txt_ContaAguaValorAnt;
    @FXML
    private TextField txt_ContaAguaValorAtual;
    @FXML
    private TextField txt_ContaAguaValorAgua;
    @FXML
    private TextField txt_ContaAguaValorEsgoto;
    @FXML
    private TextField txt_ContaAguaMulta;
    @FXML
    private TextField txt_ContaAguaTrcf;
    @FXML
    private TextField txt_ContaAguaBaseCofins;
    @FXML
    private TextField txt_ContaAguaAliquotaCofins;
    @FXML
    private Button btn_SalvarAgua;
    @FXML
    private Button btn_LimparAgua;
    @FXML
    private TextField txt_IdCliente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SalvarAgua(ActionEvent event) {
    }

    @FXML
    private void LimparAgua(ActionEvent event) {
        txt_ContaAguaAliquotaCofins.setText("");
        txt_ContaAguaBaseCofins.setText("");
        txt_ContaAguaCompetencia.setText("");
        txt_ContaAguaDataLeituraAnt.setText("");
        txt_ContaAguaDataLeituraAtual.setText("");
        txt_ContaAguaInstalacao.setText("");
        txt_ContaAguaMulta.setText("");
        txt_ContaAguaPrevProxLeitura.setText("");
        txt_ContaAguaTrcf.setText("");
        txt_ContaAguaValorAgua.setText("");
        txt_ContaAguaValorAtual.setText("");
        txt_ContaAguaValorEsgoto.setText("");
        txt_ContaAguaVencimento.setText("");
    }
    
}
