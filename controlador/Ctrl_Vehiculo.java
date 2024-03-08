/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Vehiculo;

/**
 *
 * @author Usuario
 */
public class Ctrl_Vehiculo {

    public boolean vehiculoEnEstablecimiento(String patente) {
        boolean result = false;
        Connection cn = Conexion.conectar();
        try {
            String sql = "SELECT dniDueno FROM tb_vehiculo WHERE patente = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, patente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
            }
            cn.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al evaluar si se encuentra el vehiculo en el establecimiento. Error " + e);
        }
        return result;
    }

    public boolean ingresarVehiculo(Vehiculo modelo) {
        boolean result = false;
        Connection cn = Conexion.conectar();
        try {
            String sql = "INSERT INTO tb_vehiculo (dniDueno,patente,tipoVehiculo,ubicacion) VALUES (?,?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, modelo.getDniDueno());
            ps.setString(2, modelo.getPatente());
            ps.setString(3, modelo.getTipoVehiculo());
            ps.setString(4, modelo.getUbicacion());
            if (ps.executeUpdate() > 0) {
                result = true;
            }
            cn.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Error al ingresar un vehiculo. Error " + e);
        }
        return result;
    }
    
    public String devolverTipoVehiculo (String patente){
        String tipoVehiculo = "";
        Connection cn = Conexion.conectar();
        try {
            String sql = "SELECT tipoVehiculo FROM tb_vehiculo WHERE patente = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, patente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                tipoVehiculo = rs.getString("tipoVehiculo");
            }
        } catch (SQLException e) {
            System.out.println("Error al devolver el tipo de vehiculo. Error "+e);
        }
        return tipoVehiculo;
    }
    
    public boolean retirarVehiculo (String patente){
        boolean result = false;
        Connection cn = Conexion.conectar();
        try {
            String sql ="UPDATE tb_vehiculo SET ubicacion = ? WHERE patente = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, "Fuera del establecimiento.");
            ps.setString(2, patente);
            if (ps.executeUpdate() >0){
                result = true;
            }
            cn.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al retirar vehiculo.");
        }
     return result;   
    }
    
}
