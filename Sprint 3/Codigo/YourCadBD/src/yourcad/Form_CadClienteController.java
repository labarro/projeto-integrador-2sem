package yourcad;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import yourcad.DBConexao.*;
import yourcad.Cliente;
//import yourcad.PesqClienteController.*;

/**
 * FXML Controller class
 *
 * @author mateu
 */
public class Form_CadClienteController implements Initializable {
    
    @FXML
    private MenuBar menuBar_TelaInicial;
    @FXML
    private MenuItem menuItem_CadCliente;
    @FXML
    private MenuItem menuItem_CadConta;
    @FXML
    private MenuItem menuItem_CadConcessionaria;
    @FXML
    private AnchorPane ancPane_TelaInicio;
    @FXML
    private TextField txtFld_NomeCliente;
    private TextField txtFld_ApelidoCliente;
    @FXML
    private TextField txtFld_DocCliente;
    private TextField txtFld_CepCliente;
    private TextField txtFld_NumEndCliente;
    private TextField txtFld_ComplEndCliente;
    private TextField txtFld_EndCliente;
    private TextField txtFld_CidadeEndCliente;
    private TextField txtFld_UfEndCliente;
    @FXML
    private Button btn_CadCliente;
    @FXML
    private Button btn_NovoInstalacao;
    @FXML
    private Button btn_LimparCliente;
    @FXML
    private MenuItem menuItem_PesqCliente;
    @FXML
    private MenuItem menuItem_PesqConta;
    @FXML
    private MenuItem menuItem_PesqConcessionaria;
    @FXML
    private BorderPane mainPane;
    @FXML
    private TextField txtFld_apelidoCliente;
    @FXML
    private TextField txtFld_numEndCliente;
    @FXML
    private TextField txtFld_ufCliente;
    @FXML
    private TextField txtFld_bairroCliente;
    @FXML
    private TextField txtFld_endcomplementoCliente;
    @FXML
    private TextField txtFld_enderecoCliente;
    @FXML
    private TextField txtFld_cepCliente;
    @FXML
    private TextField txtFld_CidadeCliente;
    @FXML
    private Button btnAlterarInstalacao;
    @FXML
    private Button btnDeletarInstalacao;

    public TextField getTxtFld_NomeCliente() {
        return txtFld_NomeCliente;
    }

    public void setTxtFld_NomeCliente(TextField txtFld_NomeCliente) {
        this.txtFld_NomeCliente = txtFld_NomeCliente;
    }

    public TextField getTxtFld_idCliente() {
        return txtFld_idCliente;
    }

    public void setTxtFld_idCliente(TextField txtFld_idCliente) {
        this.txtFld_idCliente = txtFld_idCliente;
    }
    @FXML
    private TextField txtFld_idCliente; 

    /**
     * Initializes the controller class.
     */
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int id = PesqClienteController.alterClienteId;
        
        if(id != 0)
        {        
            try {
                
                String cliente_id = null;
                String cliente_nome = null;
                String cliente_documento = null;
                String cliente_apelido = null;
                String cliente_endereco = null;
                String cliente_complementoEndereco = null;
                String cliente_numeroEndereco = null;
                String cliente_cep = null;
                String cliente_cidade = null;
                String cliente_uf = null;
                String cliente_bairro = null;
                
                Connection conn = null;
                ResultSet resultadoBanco = null;
                
                conn = DBConexao.abrirConexao();
                Statement stm = conn.createStatement();
                
                String sql = "SELECT cliente_id, cliente_nome, cliente_documento, cliente_apelido,"
                + " cliente_endereco, cliente_complemento_endereco, cliente_numero_endereco, cliente_bairro,"
                + " cliente_cep, cliente_cidade, cliente_uf FROM cliente WHERE cliente_id = "+ id +";";
                resultadoBanco = stm.executeQuery(sql);
                               
                while(resultadoBanco.next())
                {
                      //System.out.printf(resultadoBanco.getString("cliente_nome"));
                      cliente_id = resultadoBanco.getString("cliente_id");
                      cliente_nome = resultadoBanco.getString("cliente_nome");
                      cliente_documento = resultadoBanco.getString("cliente_documento");
                      cliente_apelido = resultadoBanco.getString("cliente_apelido");
                      cliente_endereco = resultadoBanco.getString("cliente_endereco");
                      cliente_complementoEndereco = resultadoBanco.getString("cliente_complemento_endereco");
                      cliente_numeroEndereco = resultadoBanco.getString("cliente_numero_endereco");
                      cliente_bairro = resultadoBanco.getString("cliente_bairro");
                      cliente_cep = resultadoBanco.getString("cliente_cep");
                      cliente_cidade = resultadoBanco.getString("cliente_cidade");
                      cliente_uf = resultadoBanco.getString("cliente_uf");

                }
                txtFld_idCliente.setText(cliente_id);
                txtFld_NomeCliente.setText(cliente_nome);
                txtFld_DocCliente.setText(cliente_documento);
                txtFld_apelidoCliente.setText(cliente_apelido);
                txtFld_enderecoCliente.setText(cliente_endereco);
                txtFld_endcomplementoCliente.setText(cliente_complementoEndereco);
                txtFld_numEndCliente.setText(cliente_numeroEndereco);
                txtFld_ufCliente.setText(cliente_uf);
                txtFld_CidadeCliente.setText(cliente_cidade);
                txtFld_bairroCliente.setText(cliente_bairro);
                txtFld_cepCliente.setText(cliente_cep);
                
            } catch (Exception ex) {
                Logger.getLogger(Form_CadClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        }
    }    
    
        // INICIO MENU BAR //
    // FUNÇÃO PARA ABRIR TELA A PARTIR DE MENU BAR 
    @FXML
    public void gotoCliente(ActionEvent event) throws IOException{ 
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("Form_CadCliente.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    private void gotoConta(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("Form_CadConta.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    private void gotoConcessionaria(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("Form_CadConcessionaria.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    
    @FXML
    private void gotoPesqCliente(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("PesqCliente.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    
    @FXML
    private void gotoPesqConta(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("PesqConta.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    
    @FXML
    private void gotoPesqConcessionaria(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("PesqConcessionaria.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }    
    
    // ---------- FIM MENU BAR ------------ ///

    
    @FXML
    private void insertCliente(ActionEvent event) throws Exception {
        
        if(PesqClienteController.alterClienteId != 0)
        {
            Connection conn = null;
            ResultSet resultadoBanco = null;
                     
            DBConexao c = new DBConexao();
            conn = DBConexao.abrirConexao();
            Statement stm = conn.createStatement();
                        
                String cliente_id = txtFld_idCliente.getText();
                String cliente_nome = txtFld_NomeCliente.getText();
                String cliente_doc = txtFld_DocCliente.getText();
                String cliente_apelido = txtFld_apelidoCliente.getText();
                String cliente_endereco = txtFld_enderecoCliente.getText();
                String cliente_complemento = txtFld_endcomplementoCliente.getText();
                String cliente_numero = txtFld_numEndCliente.getText();
                String cliente_uf = txtFld_ufCliente.getText();
                String cliente_cidade = txtFld_CidadeCliente.getText();
                String cliente_bairro = txtFld_bairroCliente.getText();
                String cliente_cep = txtFld_cepCliente.getText();


            String sql;
            sql = "UPDATE cliente SET "
                    + "cliente_nome = '"+ cliente_nome +"', "
                    + "cliente_documento = '"+ cliente_doc +"', "
                    + "cliente_apelido = '"+ cliente_apelido +"', "
                    + "cliente_endereco = '"+ cliente_endereco +"', "
                    + "cliente_complemento_endereco = '"+ cliente_complemento +"', "
                    + "cliente_numero_endereco = '"+ cliente_numero +"', "
                    + "cliente_bairro = '"+ cliente_bairro +"', "
                    + "cliente_cep = '"+ cliente_cep +"', "
                    + "cliente_cidade = '"+ cliente_cidade +"', "
                    + "cliente_uf = '"+ cliente_uf +"' "                  
                    + "WHERE cliente_id = "+ cliente_id +";";
                       
            stm.executeUpdate(sql);
            
        }else{           
       
            String cliente_nome = txtFld_NomeCliente.getText();
            String cliente_doc = txtFld_DocCliente.getText();
            String cliente_apelido = txtFld_apelidoCliente.getText();
            String cliente_endereco = txtFld_enderecoCliente.getText();
            String cliente_complemento = txtFld_endcomplementoCliente.getText();
            String cliente_numero = txtFld_numEndCliente.getText();
            String cliente_uf = txtFld_ufCliente.getText();
            String cliente_cidade = txtFld_CidadeCliente.getText();
            String cliente_bairro = txtFld_bairroCliente.getText();
            String cliente_cep = txtFld_cepCliente.getText();
        
            Connection conn = null;
            conn = DBConexao.abrirConexao();
            Statement stm = conn.createStatement();
            String query;
            query = "INSERT INTO cliente(cliente_nome,\n" +
                    "cliente_documento, cliente_apelido, cliente_endereco, cliente_complemento_endereco,"
                    + " cliente_numero_endereco, cliente_uf, cliente_cidade, cliente_bairro, cliente_cep) VALUES "
                + "('"+ cliente_nome +"','"
                    + cliente_doc +"','"
                    + cliente_apelido +"','"
                    + cliente_endereco +"','"
                    + cliente_complemento +"','"
                    + cliente_numero +"','"
                    + cliente_uf +"','"
                    + cliente_cidade +"','"
                    + cliente_bairro +"','"
                    + cliente_cep +"');";
            System.out.println(query);
        
//          System.out.println(query);  // TESTE DE FUNCIONAMENTO
            stm.executeUpdate(query);
            System.out.println("Dados Cadastrados com sucesso!!!");
        }
    }

    @FXML
    private void limparCliente(ActionEvent event) {
        
        txtFld_NomeCliente.setText("");
        txtFld_idCliente.setText("");
        txtFld_DocCliente.setText("");
        txtFld_apelidoCliente.setText("");
        txtFld_enderecoCliente.setText("");
        txtFld_endcomplementoCliente.setText("");
        txtFld_numEndCliente.setText("");
        txtFld_ufCliente.setText("");
        txtFld_CidadeCliente.setText("");
        txtFld_bairroCliente.setText("");
        txtFld_cepCliente.setText("");
        
    }

    @FXML
    private void cadInstalacao(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("Form_CadInstalacoes.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    private void alterarInstalacao(ActionEvent event) {
    }

    @FXML
    private void deletarInstalacao(ActionEvent event) {
    }
     
    
    
}
