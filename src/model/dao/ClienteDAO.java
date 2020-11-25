
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Cliente;

public class ClienteDAO {
    public void create(Cliente cliente){ //metodo responsavel por inserir dados no BD
          Connection con = ConnectionFactory.getConnection();
          PreparedStatement stmt = null;
          
        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement("INSERT INTO cliente (nome, email, cpf, telefone, dataaniversario) VALUES (?,?,?,?,?)");
            
            
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getDataaniversario());
            
            stmt.executeUpdate(); 
            
            con.commit();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar: " +ex);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            
              try {
                  con.rollback();
              } catch (SQLException ex1) {
                  Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex1);
              }
        }finally{
            ConnectionFactory.closeConnection(con, stmt); //fechando a conexao
        }
          
          
    }
    
    public ArrayList<Cliente> read(){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            ArrayList<Cliente> listaClientes = new ArrayList<>();
            
            try{
                stmt=con.prepareStatement("SELECT * FROM cliente ORDER BY idcliente");
                rs = stmt.executeQuery();
                
                while(rs.next()){
                  Cliente cli = new Cliente();
                  cli.setNome(rs.getString("nome"));
                  cli.setEmail(rs.getString("email"));
                  cli.setCpf(rs.getString("cpf"));
                  cli.setTelefone(rs.getString("telefone"));
                  cli.setDataaniversario(rs.getString("dataaniversario"));
                  cli.setIdcliente(rs.getInt("idcliente"));
                  listaClientes.add(cli);
                }
                
            } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                ConnectionFactory.closeConnection(con, stmt, rs);
            }

            return listaClientes;
            
            
    }
    
    public void update(Cliente cliente){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE cliente set nome = ?, email = ?, cpf = ?, "
                    + "telefone = ?, dataaniversario = ? WHERE idcliente = ?");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getDataaniversario());
            stmt.setInt(6, cliente.getIdcliente());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
       
    }
     public void delete(Cliente cliente){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            
        try {
            stmt = con.prepareStatement("DELETE FROM cliente WHERE idcliente = ?");
            stmt.setInt(1, cliente.getIdcliente());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Excluido com sucesso!!");
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
            
        }
    
    

}
