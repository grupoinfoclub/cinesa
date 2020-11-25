package com.cinesa.controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.cinesa.modelo.Usuario;
import com.cinesa.transversal.BaseDeDatos;
import java.sql.Connection;

public class Usuario_CRUD {
	
	public Usuario usuario;//recibimos objeto 
	/**
	 * constructor que recibe por parámetro la instancia de la clase Usuario
	 * @param usuario
	 */
	public Usuario_CRUD(Usuario usuario) {
		this.usuario = usuario;
	}
	
    private final int rol = 3; // 3:cliente  TOKEN 

	/**
	 * Procemiento de inserción de un usuario en BD. Controla la conexión y el registro.
	 * @param usuario instancia de la clase Usuario
	 * @throws SQLException controla errores SQL
	 */
    public void agregar(Usuario usuario) throws SQLException {
        String query = "";
        BaseDeDatos bd = new BaseDeDatos();
        Connection conexion = bd.conectar();
        Statement sentencia = null;
        if (conexion != null) {
            sentencia = conexion.createStatement();                                
            query = "INSERT INTO usuarios(dni, nombre, apellidos,CP, mail, movil, rol, clave, activo, borrado) VALUES ('" + usuario.getDni()
                    + "','" + usuario.getNombre() + "','" + usuario.getApellidos() + "', '" + usuario.getCP() + "', '" + usuario.getMail() + "', " 
            		+ usuario.getMovil() + ", " + rol + ", " + "'" + usuario.getClave() + "', " + usuario.getActivo() + ", " + usuario.getBorrado() + ");";
			//query = "INSERT INTO usuarios(dni, nombre, apellidos,CP, mail, movil, rol, clave, activo, borrado)"+
			// "VALUES (?,?,?,?,?,?,?,?,?,?)";

            if (sentencia.executeUpdate(query) > 0) {
                System.out.println("El registro se insertó exitosamente.");
            } else {
                System.out.println("No se pudo insertar el registro.");
            }
            System.out.println(query);
        }else {
        	System.out.println("Ha habido un problema al conectar con la base de datos");
        }
        try {
        	if (sentencia!=null) {
				try {
					sentencia.close();
					bd.conexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
        }catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Procedimiento update del nombre de usuario. Se filtrará por ID en la query. Controla errores SQL
     * @param usuario instancia de la clase Usuario
     * @param nombre
     */
    public void editar_nombre(Usuario usuario, String nombre) {
        String query = "";
        BaseDeDatos bd = new BaseDeDatos();
        PreparedStatement sentenciaP = null;
        try {
            query = "UPDATE usuarios SET nombre = ? WHERE id = ?;";
			//query = "UPDATE usuarios SET nombre = '"+nombre+"'"+" WHERE id = "+usuario.getId()+";";
            Connection conexion = bd.conectar();
            if (conexion != null) {
            	   sentenciaP = conexion.prepareStatement(query);
                   sentenciaP.setString(1, nombre);
                   sentenciaP.setInt(2, usuario.getId());

                   sentenciaP.executeUpdate();
                   System.out.println("El registro se actualizó exitosamente.");
            }else {
            	System.out.println("Ha habido un problema al conectar con la base de datos");
            }
         
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
        	if (sentenciaP!=null) {
				try {
					sentenciaP.close();
					bd.conexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
        }
    }
    
    /**
     * Procedimiento delete de un usuario según su rol. Controla errores SQL
     * @param usuario instancia de la clase Usuario
     */
    public void eliminar(Usuario usuario) {
        String query = "";
        BaseDeDatos bd = new BaseDeDatos();
        PreparedStatement sentenciaP = null;
        try {
            query = "DELETE FROM usuarios WHERE rol = ?;";
            Connection conexion = bd.conectar();
            if (conexion != null) {
            	 sentenciaP = conexion.prepareStatement(query);
                 sentenciaP.setInt(1, 3);

                 sentenciaP.execute();
                 System.out.println("El registro se eliminó exitosamente.");
                 sentenciaP.close();
                 bd.conexion.close();
            }else {
            	System.out.println("Ha habido un problema al conectar con la base de datos");
            }
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
        	if (sentenciaP!=null) {
				try {
					sentenciaP.close();
					bd.conexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
        }
    }

    /**
     * Función select filtrando por DNI para el registro
     * @param usuario
     * @return el resultado de la query para la clase menuRegistro. Controla errores SQL
     */
    public int obtener(Usuario usuario) {
        String query = "";
        int r = 0;
        BaseDeDatos bd = new BaseDeDatos();
        ResultSet resultado = null;
        Statement sentencia = null;
        try {
            query = "SELECT * FROM usuarios WHERE dni= '" + usuario.getDni() + "';";
            Connection conexion = bd.conectar();
            if (conexion != null) {
                sentencia = conexion.createStatement();
                resultado = sentencia.executeQuery(query);
				System.out.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════╗");
				System.out.println("║ NÚMERO DE USUARIO - DNI - NOMBRE - APELLIDOS - CP - MAIL - MOVIL - ROL - CLAVE - ACTIVO - BORRADO  ║");
				System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════╝");

                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    String dni = resultado.getString("dni");
                    String nombre = resultado.getString("nombre");
                    String apellidos = resultado.getString("apellidos");
                    String CP = resultado.getString("CP");
                    String mail = resultado.getString("mail");       
                    int movil = resultado.getInt("movil");
                    int rol = resultado.getInt("rol");
                    String clave = resultado.getString("clave");
                    int activo = resultado.getInt("activo");
                    int borrado = resultado.getInt("borrado");

                    // Imprimir los resultados.
                    System.out.format("%d, %s, %s, %s, %s, %s, %d, %d, %s, %d, %d\n", id, dni, nombre, apellidos, CP, mail, movil, rol, clave, activo, borrado);
                    r = 1;
                }
                     
            }else {
            	System.out.println("Ha habido un problema al conectar con la base de datos");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
        	if (sentencia!=null) {
				try {
					sentencia.close();
					resultado.close();
					bd.conexion.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        }
        return r;
    }
    
    /**
     * Función select filtrando por DNI y clave para el login
     * @param usuario
     * @return el resultado de la query para la clase menuLogin. Controla errores SQL
     */
    public int obtener2(Usuario usuario) {
        String query = "";
        int r = 0;
        BaseDeDatos bd = new BaseDeDatos();
        ResultSet resultado = null;
        Statement sentencia = null;
        try {
            query = "SELECT * FROM usuarios WHERE dni= '" + usuario.getDni() + "' and clave='" + usuario.getClave() + "';";
            Connection conexion = bd.conectar();
            if (conexion != null) {
                sentencia = conexion.createStatement();
                resultado = sentencia.executeQuery(query);
				System.out.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════╗");
				System.out.println("║ NÚMERO DE USUARIO - DNI - NOMBRE - APELLIDOS - CP - MAIL - MOVIL - ROL - CLAVE - ACTIVO - BORRADO  ║");
				System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════╝");

                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    String dni = resultado.getString("dni");
                    String nombre = resultado.getString("nombre");
                    String apellidos = resultado.getString("apellidos");
                    String CP = resultado.getString("CP");
                    String mail = resultado.getString("mail");       
                    int movil = resultado.getInt("movil");
                    int rol = resultado.getInt("rol");
                    String clave = resultado.getString("clave");
                    int activo = resultado.getInt("activo");
                    int borrado = resultado.getInt("borrado");

                    // Imprimir los resultados.
                    System.out.format("%d, %s, %s, %s, %s, %s, %d, %d, %s, %d, %d\n", id, dni, nombre, apellidos, CP, mail, movil, rol, clave, activo, borrado);
                    r = 1;
                }
            }else {
            	System.out.println("Ha habido un problema al conectar con la base de datos");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
        	if (sentencia!=null) {
				try {
					sentencia.close();
					resultado.close();
					bd.conexion.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        }
        return r;
    }
    
    /**
     * Función select filtrando por un random aleatorio de sql. Retorna el id del usuario para recogerlo en la table premios como premiado
     * @param usuario
     * @return el resultado de la query para la clase menuLog. Controla errores SQL
     */
    public int obtenerRnd(Usuario usuario) {
        String query = "";
        int r = 0;
        BaseDeDatos bd = new BaseDeDatos();
        ResultSet resultado = null;
        Statement sentencia = null;
        try {
            query = "SELECT * From usuarios where rol = 3 order by rand() limit 1;";
            Connection conexion = bd.conectar();
            if (conexion != null) {
                sentencia = conexion.createStatement();
                resultado = sentencia.executeQuery(query);
				System.out.println("╔══════════════════════════════════════════════════════════╗");
				System.out.println("║             ID DEL USUARIO PREMIADO DEL DÍA:             ║");
				System.out.println("╚══════════════════════════════════════════════════════════╝");

                while (resultado.next()) {
                    int id = resultado.getInt("id");

                    // Imprimir los resultados.
                    System.out.format("%d\n", id);
                    r = id;
                }
                     
            }else {
            	System.out.println("Ha habido un problema al conectar con la base de datos");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
        	if (sentencia!=null) {
				try {
					sentencia.close();
					resultado.close();
					bd.conexion.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        }
        return r;
    }
    /**
     * funcion para comprobar si el usuario introducido es administrador. 
     * @param usuario
     * @return
     */
    public int obtener3(Usuario usuario) {
        String query = "";
        int r = 0;
        BaseDeDatos bd = new BaseDeDatos();
        ResultSet resultado = null;
        Statement sentencia = null;
        //int rol = 1;
        try {
            query = "SELECT * FROM usuarios WHERE dni= '" + usuario.getDni() + "' and clave='" + usuario.getClave() + "' and rol=" + usuario.getRol() + ";";
            Connection conexion = bd.conectar();
            if (conexion != null) {
                sentencia = conexion.createStatement();
                resultado = sentencia.executeQuery(query);
				System.out.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════╗");
				System.out.println("║ NÚMERO DE USUARIO - DNI - NOMBRE - APELLIDOS - CP - MAIL - MOVIL - ROL - CLAVE - ACTIVO - BORRADO  ║");
				System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════╝");

                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    String dni = resultado.getString("dni");
                    String nombre = resultado.getString("nombre");
                    String apellidos = resultado.getString("apellidos");
                    String CP = resultado.getString("CP");
                    String mail = resultado.getString("mail");       
                    int movil = resultado.getInt("movil");
                    int rol = resultado.getInt("rol");
                    String clave = resultado.getString("clave");
                    int activo = resultado.getInt("activo");
                    int borrado = resultado.getInt("borrado");

                    // Imprimir los resultados.
                    System.out.format("%d, %s, %s, %s, %s, %s, %d, %d, %s, %d, %d\n", id, dni, nombre, apellidos, CP, mail, movil, rol, clave, activo, borrado);
                    r = 1;
    				System.out.println("╔══════════════════════════════╗");
    				System.out.println("║     ¡¡¡¡¡BIENVENIDO!!!!!     ║");
    				System.out.println("╚══════════════════════════════╝");
                }
            }else {
            	System.out.println("Ha habido un problema al conectar con la base de datos");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
        	if (sentencia!=null) {
				try {
					sentencia.close();
					resultado.close();
					bd.conexion.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        }
        return r;
    }
    /**
     * funcion select filtrando por id para la opción del menú del premiado.
     * @param usuario
     * @return
     */
    public int obtener4(Usuario usuario) {
        String query = "";
        int r = 0;
        BaseDeDatos bd = new BaseDeDatos();
        ResultSet resultado = null;
        Statement sentencia = null;
        try {
            query = "SELECT * FROM usuarios WHERE id= " + usuario.getId() + ";";
            Connection conexion = bd.conectar();
            if (conexion != null) {
                sentencia = conexion.createStatement();
                resultado = sentencia.executeQuery(query);
				System.out.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════╗");
				System.out.println("║ NÚMERO DE USUARIO - DNI - NOMBRE - APELLIDOS - CP - MAIL - MOVIL - ROL - CLAVE - ACTIVO - BORRADO  ║");
				System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════╝");

                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    String dni = resultado.getString("dni");
                    String nombre = resultado.getString("nombre");
                    String apellidos = resultado.getString("apellidos");
                    String CP = resultado.getString("CP");
                    String mail = resultado.getString("mail");       
                    int movil = resultado.getInt("movil");
                    int rol = resultado.getInt("rol");
                    String clave = resultado.getString("clave");
                    int activo = resultado.getInt("activo");
                    int borrado = resultado.getInt("borrado");

                    // Imprimir los resultados.
                    System.out.format("%d, %s, %s, %s, %s, %s, %d, %d, %s, %d, %d\n", id, dni, nombre, apellidos, CP, mail, movil, rol, clave, activo, borrado);
                    r = 1;
                }
                     
            }else {
            	System.out.println("Ha habido un problema al conectar con la base de datos");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
        	if (sentencia!=null) {
				try {
					sentencia.close();
					resultado.close();
					bd.conexion.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        }
        return r;
    }
    
    /**
     * Función select filtrando por DNI para las reservas
     * @param usuario
     * @return el resultado de la query para la clase menuLog. Controla errores SQL
     */
    @SuppressWarnings("unused")
	public int obtener5(Usuario usuario) {
        String query = "";
        int r = 0;
        BaseDeDatos bd = new BaseDeDatos();
        ResultSet resultado = null;
        Statement sentencia = null;
        try {
            query = "SELECT * FROM usuarios WHERE dni= '" + usuario.getDni() + "';";
            Connection conexion = bd.conectar();
            if (conexion != null) {
                sentencia = conexion.createStatement();
                resultado = sentencia.executeQuery(query);
			
                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    String dni = resultado.getString("dni");
                    String nombre = resultado.getString("nombre");
                    String apellidos = resultado.getString("apellidos");
                    String CP = resultado.getString("CP");
                    String mail = resultado.getString("mail");       
                    int movil = resultado.getInt("movil");
                    int rol = resultado.getInt("rol");
                    String clave = resultado.getString("clave");
                    int activo = resultado.getInt("activo");
                    int borrado = resultado.getInt("borrado");
                    // Imprimir los resultados.
                   // System.out.format("%d, %s, %s, %s, %s, %s, %d, %d, %s, %d, %d\n", id, dni, nombre, apellidos, CP, mail, movil, rol, clave, activo, borrado);
                    r = id;
                }
            }else {
            	System.out.println("Ha habido un problema al conectar con la base de datos");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
        	if (sentencia!=null) {
				try {
					sentencia.close();
					resultado.close();
					bd.conexion.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        }
        return r;
    }
    
    /**
     * funcion select filtrando por id para el envío del mail.
     * @param usuario
     * @return
     */
    public String obtener6(Usuario usuario) {
        String query = "";
        String email = "";
        BaseDeDatos bd = new BaseDeDatos();
        ResultSet resultado = null;
        Statement sentencia = null;
        try {
            query = "SELECT mail FROM usuarios WHERE id= " + usuario.getId() + ";";
            Connection conexion = bd.conectar();
            if (conexion != null) {
                sentencia = conexion.createStatement();
                resultado = sentencia.executeQuery(query);

                while (resultado.next()) {
                    String mail = resultado.getString("mail");   
                    
                    //System.out.format("%s\n", mail);
                    email = mail;
                }
                     
            }else {
            	System.out.println("Ha habido un problema al conectar con la base de datos");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
        	if (sentencia!=null) {
				try {
					sentencia.close();
					resultado.close();
					bd.conexion.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        }
        return email;
    }
}