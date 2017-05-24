package WebServicesClasses;

import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.*;
import java.util.ArrayList;
import Domain.*;

/**
 * Created by estefania on 14/05/2017.
 */

@Path("/M05_ServicesActivity")
public class M05_ServicesActivity {



    Gson gson = new Gson();



    @GET

    @Path("/insertActivity")

    @Produces("application/json")
    /**Para insertar actividdades que no son parte de un entrenamiento
     * @param horainicial
     * @param horafinal
     * @param fecha
     * @param km
     * @param calorias
     * @param lugarinicial
     * @param lugarfinal
     * @param idReg
     * @param idSpo
     * @return
     */

    public String insertActivity (@QueryParam("horainicial")  String horainicio,
                                  @QueryParam("horafinal")    String horafinal,
                                  @QueryParam("fecha")        String fecha,
                                  @QueryParam("km")           String km,
                                  @QueryParam("calorias")     String caloria,
                                  @QueryParam("lugarinicial") String lugarinicio,
                                  @QueryParam("lugarfinal")   String lugarfinal,
                                  @QueryParam("idReg")        Integer idreg,
                                  @QueryParam("idSpo")        Integer idspo){

        String query = "select * from M05_insertarActividad('"+horainicio+"','"+horafinal+"','"+fecha+"','"+km+"'," +
                "                                           '"+caloria+"','"+lugarinicio+"','"+lugarfinal+"'," +
                                                            "'"+idreg+"','"+idspo+"')";

        try{
            Connection conn = conectarADb();
            Statement st = conn.createStatement();
            ResultSet rs =  st.executeQuery(query);

            return gson.toJson("Actividad Agregada");
        } catch (Exception e) {
            return  e.getMessage();
        }

    }


    @GET

    @Path("/insertActivityT")

    @Produces("application/json")
    /**
     * Para insertar actividades que son partede un entrenamiento
     * @param horainicial
     * @param horafinal
     * @param fecha
     * @param km
     * @param calorias
     * @param lugarinicial
     * @param lugarfinal
     * @param idReg
     * @param idSpo
     * @param idTra
     * @return
     */
    public String insertActivityT (@QueryParam("horainicial")  String horainicio,
                                   @QueryParam("horafinal")    String horafinal,
                                   @QueryParam("fecha")        String fecha,
                                   @QueryParam("km")           String km,
                                   @QueryParam("calorias")     String caloria,
                                   @QueryParam("lugarinicial") String lugarinicio,
                                   @QueryParam("lugarfinal")   String lugarfinal,
                                   @QueryParam("idReg")        Integer idreg,
                                   @QueryParam("idSpo")        Integer idspo,
                                   @QueryParam("idTra")        Integer idtra){
   
        String query = "select * from M05_insertaractividadentrenamiento('"+horainicio+"','"+horafinal+"','"+fecha+"',"+
                                                                         "'"+km+"','"+caloria+"','"+lugarinicio+"'," +
                                                                         "'"+lugarfinal+"','"+idreg+"','"+idspo+"'," +
                                                                         "'"+idtra+"')";

        try{
            Connection conn = conectarADb();
            Statement    st = conn.createStatement();
            ResultSet    rs =  st.executeQuery(query);

            return gson.toJson(true);
        } catch (Exception e) {
            return  e.getMessage();
        }

    }

    @GET

    @Path("/getActivity")

    @Produces("application/json")
    /**
     * Carga las actividades por fecha
     * @param fechalejana
     * @param fechacerrcana
     * @param idPer
     * @return
     */
    public String getActivity(@QueryParam("fechalejana") String fmayor,
                              @QueryParam("fechacercana") String fmenor,
                              @QueryParam("idPer")      Integer id){


        String query = "select * from M05_obteneractividades ('"+fmayor+"', '"+fmenor+"' ,'"+id+"')";

        Activity resultado = new Activity();

        //Array creado para almacenar las actividades realizadas
        ArrayList<Activity> listaActividades = new ArrayList<>();

        try{
            Connection conn = conectarADb();
            Statement   st  = conn.createStatement();
            ResultSet   rs  =  st.executeQuery(query);

            while(rs.next()){

                resultado.setStartime(rs.getString("horainicio"));
                resultado.setEndtime (rs.getString("horafinal"));
                resultado.setDate    (rs.getString("fecha"));
                resultado.setKm      (rs.getFloat( "km"));
                resultado.setCalor   (rs.getFloat( "caloria"));
                resultado.setStarsite(rs.getString("lugarinicio"));
                resultado.setEndsite (rs.getString("lugarfinal"));
                resultado.setName    (rs.getString("nombredeporte"));

                listaActividades.add(resultado);


            }
            return gson.toJson(listaActividades);

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GET

    @Path("/getCalorie")

    @Produces("application/json")
    /**
     * Obtiene las calorias quemadas a partir de una fecha
     * @param fechalejana
     * @param fechacercana
     * @param idPer
     *@return
     *
     */
    public String getCalorie(@QueryParam("fechalejana")  String fmayor,
                             @QueryParam("fechacercana") String fmenor,
                             @QueryParam("idPer")        Integer id){


        String query = "select * from M05_obtenercaloriasactividades ('"+fmayor+"', '"+fmenor+"' ,'"+id+"')";

        Activity resultado = new Activity();
        ArrayList<Activity> listaActividades = new ArrayList<>();

        try{
            Connection conn=conectarADb();
            Statement st = conn.createStatement();
            ResultSet rs =  st.executeQuery(query);

            while(rs.next()){
                resultado.setDate (rs.getString("dia"));
                resultado.setCalor(rs.getFloat("caloria"));

                listaActividades.add(resultado);


            }
            return gson.toJson(listaActividades);

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GET

    @Path("/getKm")

    @Produces("application/json")
    /**
     * Obtiene los km recorridos por actividades realizadas en una determinada fecha
     * @param fechalejana
     * @param fechacercana
     * @param idPer
     * @return
     */
    public String getKm(@QueryParam("fechalejana")  String fmayor,
                        @QueryParam("fechacercana") String fmenor,
                        @QueryParam("idPer")        Integer id){


        String query = "select * from M05_obtenerkmactividades ('"+fmayor+"', '"+fmenor+"' ,'"+id+"')";

        Activity resultado = new Activity();
        ArrayList<Activity> listaActividades = new ArrayList<>();

        try{
            Connection conn=conectarADb();
            Statement st = conn.createStatement();
            ResultSet rs =  st.executeQuery(query);

            while(rs.next()){
                resultado.setDate(rs.getString("dia"));
                resultado.setKm(  rs.getFloat(  "km"));

                listaActividades.add(resultado);


            }
            return gson.toJson(listaActividades);

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GET

    @Path("/updateKm")

    @Produces("application/json")
    /**
     * Modifica los km recorridos en una determinada actividad
     * @param fecha
     * @param hora
     * @param idReg
     * @param km
     * @return
     */
    public String updateKm(@QueryParam("fecha") String fmayor,
                           @QueryParam("hora")  String hora,
                           @QueryParam("idReg") int idreg,
                           @QueryParam("km")    float km){

        int id = obtenerActId(fmayor,hora,idreg);

       String query ="select m05_modificarkmActividad('"+id+"','"+km+"'); ";

        Activity resultado= new Activity();


       try{
            Connection conn=conectarADb();
            Statement st = conn.createStatement();
            ResultSet rs =  st.executeQuery(query);

            return gson.toJson("Modificado");

        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @GET

    @Path("/updateCalor")

    @Produces("application/json")

    /**
     * Modifica las calorias quemadas en una determinada actividad
     * @param fecha
     * @param hora
     * @param idReg
     * @param calorias
     * @return
     */

    public String updateCalor(@QueryParam("fecha")       String fmayor,
                              @QueryParam("hora")        String hora,
                              @QueryParam("idReg")       int idreg,
                              @QueryParam("calorias")    float caloria){

        int id = obtenerActId(fmayor,hora,idreg);

        String query = "select M05_modificarcaloriaactividad('"+id+"','"+caloria+"'); ";

        Activity resultado= new Activity();


        try{
            Connection conn = conectarADb();
            Statement    st = conn.createStatement();
            ResultSet    rs =  st.executeQuery(query);

            return gson.toJson("Modificado");

        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @GET

    @Path("/deleteActivity")

    @Produces("application/json")
    /**
     * Elimina la actividad del usuario
     * @param fecha
     * @param hora
     * @param idReg
     */
    public  String deleteActivity(@QueryParam("fecha") String fmayor,
                                  @QueryParam("hora")  String hora,
                                  @QueryParam("idReg") int idreg){
        int id = obtenerActId(fmayor,hora,idreg);
        Boolean respuesta;

        String query = "SELECT M05_eliminaractividad('"+id+"')" ;

        try{
            Connection conn = conectarADb();
            Statement st = conn.createStatement();
            ResultSet rs =  st.executeQuery(query);

            return gson.toJson("Actividad Eliminada");

        } catch (Exception e) {
            return  e.getMessage();
        }

    }


    /**
     * Busca a traves de la fecha y la hora de inicio el id dde la actividad
     * @param fechaAct
     * @param hora
     * @param id
     * @return
     */
    public int obtenerActId( String fechaAct, String hora,int id){


        String query = "SELECT * FROM M05_obteneridactividades('"+fechaAct+"','"+fechaAct+" "+hora+"','"+id+"')";

        Activity resultado = new Activity();

        try{
            Connection conn = conectarADb();
            Statement    st = conn.createStatement();
            ResultSet    rs =  st.executeQuery(query);

            while(rs.next()){

                resultado.setId(rs.getInt("id"));
            }

            return resultado.getId();

        } catch (Exception e) {
            return 0;
        }
    }

    private Connection conectarADb(){

        Connection conn = null;

        try {

            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/FitUcabDB";
            conn       = DriverManager.getConnection(url, "postgres",  "postgres");

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {

            e.printStackTrace();
            System.exit(2);
        }
        return conn;
    }



}