/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yourcad;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static yourcad.Form_CadClienteController.alterInstalacaoId;
import static yourcad.Form_CadClienteController.clienteInstalacao;

/**
 * FXML Controller class
 *
 * @author MaXx
 */
public class Form_instalacaoAguaController implements Initializable {

    /**
     * @return the panel_instAgua
     */
    public Pane getPanel_instAgua() {
        return panel_instAgua;
    }

    /**
     * @return the txt_hidrometroAgua
     */
    public TextField getTxt_hidrometroAgua() {
        return txt_hidrometroAgua;
    }

    /**
     * @return the txt_codsabespAgua
     */
    public TextField getTxt_codsabespAgua() {
        return txt_codsabespAgua;
    }

    /**
     * @return the txt_economiasAgua
     */
    public TextField getTxt_economiasAgua() {
        return txt_economiasAgua;
    }

    /**
     * @return the txt_codclienteAgua
     */
    public TextField getTxt_codclienteAgua() {
        return txt_codclienteAgua;
    }

    /**
     * @return the txt_tipoLigacao
     */
    public TextField getTxt_tipoLigacao() {
        return txt_tipoLigacao;
    }

    /**
     * @return the txt_idInstalacao
     */
    public TextField getTxt_idInstalacao() {
        return txt_idInstalacao;
    }

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
    @FXML
    private TextField txt_idInstalacao;
    
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
        int instalacao_id = Form_CadClienteController.alterInstalacaoId;
        
        if(instalacao_id != 0)
        {
            try{
                Connection conn = null;
                ResultSet resultadoBanco = null;
                conn = DBConexao.abrirConexao();
                Statement stm = conn.createStatement();

                //****** Selecionando tipo de instalação
                String sql0;
                sql0 = "SELECT * FROM instalacao_agua WHERE instalacao_id = " + alterInstalacaoId +";";
                resultadoBanco = stm.executeQuery(sql0);
                
                String inst_id = null;
                String inst_hidrometro = null;
                String inst_codSabesp = null;
                String inst_codCliente = null;
                String inst_economias = null;
                String inst_tpLigacao = null;
                

                while(resultadoBanco.next())
                { 
                    inst_id = resultadoBanco.getString("instalacao_id"); 
                    inst_hidrometro = resultadoBanco.getString("instalacao_agua_hidrometro"); 
                    inst_codSabesp = resultadoBanco.getString("instalacao_agua_cod_sabesp"); 
                    inst_codCliente = resultadoBanco.getString("instalacao_agua_cod_cliente"); 
                    inst_economias = resultadoBanco.getString("instalacao_agua_economias"); 
                    inst_tpLigacao = resultadoBanco.getString("instalacao_agua_tipo_ligacao"); 

                }
                getTxt_idInstalacao().setText(inst_id);
                getTxt_codclienteAgua().setText(inst_codCliente);
                getTxt_codsabespAgua().setText(inst_codSabesp);
                getTxt_economiasAgua().setText(inst_economias);
                getTxt_hidrometroAgua().setText(inst_hidrometro);
                getTxt_tipoLigacao().setText(inst_tpLigacao);
                
                                
            }catch (Exception ex) { Logger.getLogger(Form_CadClienteController.class.getName()).log(Level.SEVERE, null, ex);}
        }
                
    }    

    @FXML
    private void salvarInstAgua(ActionEvent event) throws SQLException, Exception {
        
        int inst_id = Form_CadClienteController.alterInstalacaoId;
        
        String hidrometro = getTxt_hidrometroAgua().getText();
        String codigoCliente = getTxt_codclienteAgua().getText();
        String codigoSabesp = getTxt_codsabespAgua().getText();
        String economias = getTxt_economiasAgua().getText();
        String tipoLigacao = getTxt_tipoLigacao().getText();  
        String idInstalacao = getTxt_idInstalacao().getText();
        
      if(inst_id == 0)
      {  
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
            stm.executeUpdate(query1);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensagem");
            alert.setHeaderText("Dados salvos com sucesso !");
            alert.showAndWait();
            
            Form_CadClienteController.alterInstalacaoId = 0;
        
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("Form_CadInstalacoes.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(home_page_scene);
            app_stage.show();
            
            
      }else
        {
            Connection conn = null;
            ResultSet resultadoBanco = null;
                     
            conn = DBConexao.abrirConexao();
            Statement stm = conn.createStatement();

            String sql;
            sql = "UPDATE instalacao_agua SET "
                    + "instalacao_agua_hidrometro = '"+ hidrometro +"', "
                    + "instalacao_agua_cod_sabesp = '"+ codigoSabesp +"', "
                    + "instalacao_agua_cod_cliente = '"+ codigoCliente +"', "
                    + "instalacao_agua_economias = '"+ economias +"', "
                    + "instalacao_agua_tipo_ligacao = '"+ tipoLigacao +"' "            
                    + "WHERE instalacao_id = "+ inst_id +";";
            
            stm.executeUpdate(sql);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensagem");
            alert.setHeaderText("Dados alterados com sucesso !");
            alert.showAndWait();
            
        }
        
    }


    
    
}
