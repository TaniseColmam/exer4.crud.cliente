
package controller;

import java.util.ArrayList;
import model.bean.Cliente;
import model.dao.ClienteDAO;

public class ClienteController {
    public void create(String nome, String email, String cpf, String telefone, String dataaniversario){ //dados que vieram da view 
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);
        cliente.setDataaniversario(dataaniversario);
     
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.create(cliente);
    }
    
    public ArrayList<Cliente> read(){
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.read();
    }
    public void update(int idcliente, String nome, String email, 
            String cpf, String telefone, String dataaniversario){
        
        Cliente cliente = new Cliente();
        cliente.setIdcliente(idcliente);
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);
        cliente.setDataaniversario(dataaniversario);
     
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.update(cliente);
    }

    public void delete(int idcliente){
         Cliente cliente = new Cliente();
         cliente.setIdcliente(idcliente);
         
         ClienteDAO clienteDAO = new ClienteDAO();
         clienteDAO.delete(cliente);
     }
    
}
