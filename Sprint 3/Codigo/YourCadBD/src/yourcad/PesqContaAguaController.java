/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yourcad;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author info-chefe
 */
public class PesqContaAguaController implements Initializable {

    @FXML
    private TableView<?> tbview_Contas;
    @FXML
    private TableColumn<?, ?> col_NInstalacao;
    @FXML
    private TableColumn<?, ?> col_ApelidoInstalacao;
    @FXML
    private TableColumn<?, ?> col_cliente;
    @FXML
    private TableColumn<?, ?> col_ValorConta;
    @FXML
    private TableColumn<?, ?> col_CompetenciaConta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
