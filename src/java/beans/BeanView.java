/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import estudiante.Notas;
import estudiante.Periodos;
//import java.util.Iterator;
import java.util.List;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Anl. Daniel Paguay
 */
@ManagedBean
@ViewScoped
public class BeanView 
{
    private String cedula;
    private String ciclo;
    private Notas datosEstudiante;
    private ArrayList<Notas> notas;
    private String nomEstudiante;
    private String carreraEstudiante;
    private ArrayList<Periodos> periodos;
    private String datoPeriodo [];
   
    public BeanView() {
        nomEstudiante="";
        carreraEstudiante="";
        datoPeriodo= new String[2];
        OptenerPeriodo();
        
    }
    
    public void consegirDatos()
    {
        Client client = ClientBuilder.newBuilder()
                .property("connection.timeout", 100)
                .register(JacksonJsonProvider.class)
                .build();
        
        notas=(ArrayList<Notas>)client.target("http://lisrestful.azurewebsites.net/api/notas?cod_estudiante="+this.cedula+"&cod_lectivo="+this.ciclo+"")
                .request().accept("application/json")
                .get(new GenericType<List<Notas>>(){});
        
        nomEstudiante=notas.get(1).getApe_estudiante()+" "+notas.get(1).getNom_estudiante();
        carreraEstudiante=notas.get(1).getNom_carrera();
       
    }
    
    public void OptenerPeriodo()
    {
        Client client = ClientBuilder.newBuilder()
                .property("connection.timeout", 100)
                .register(JacksonJsonProvider.class)
                .build();
        
        periodos=(ArrayList<Periodos>)client.target("http://lisrestful.azurewebsites.net/api/periodoLectivo")
               .request().accept("application/json")
                .get(new GenericType<List<Periodos>>(){});
  
       /*for(int i=0; i<periodos.size();i++ )
       {
           datoPeriodo= new String[periodos.size()];
           datoPeriodo[i]=periodos.get(i).getNom_periodo();
           System.out.println(periodos.get(i).getCod_periodo());
           System.out.println(periodos.get(i).getNom_periodo());
           System.out.println("array:  "+datoPeriodo[i]);
       }*/
    }
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public Notas getDatosEstudiante() {
        return datosEstudiante;
    }

    public void setDatosEstudiante(Notas datosEstudiante) {
        this.datosEstudiante = datosEstudiante;
    }

    public ArrayList<Notas> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Notas> notas) {
        this.notas = notas;
    }

    public String getNomEstudiante() {
        return nomEstudiante;
    }

    public void setNomEstudiante(String nomEstudiante) {
        this.nomEstudiante = nomEstudiante;
    }

    public String getCarreraEstudiante() {
        return carreraEstudiante;
    }

    public void setCarreraEstudiante(String carreraEstudiante) {
        this.carreraEstudiante = carreraEstudiante;
    }

    public ArrayList<Periodos> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(ArrayList<Periodos> periodos) {
        this.periodos = periodos;
    }

    public String[] getDatoPeriodo() {
        return datoPeriodo;
    }

    public void setDatoPeriodo(String[] datoPeriodo) {
        this.datoPeriodo = datoPeriodo;
    }
    

}
