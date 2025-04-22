-- ========================================
-- 1. CREACIÓN DE LA BASE DE DATOS
-- ========================================
CREATE DATABASE db_soporte_123digital
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'es_PE.UTF-8'
    LC_CTYPE = 'es_PE.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- ========================================
-- 2. CONECTARSE A LA BASE DE DATOS
-- ========================================
\c db_soporte_123digital;

-- ========================================
-- 3. CREACIÓN DE ESQUEMA LÓGICO
-- ========================================
CREATE SCHEMA IF NOT EXISTS soporte AUTHORIZATION postgres;

-- Establecer esquema por defecto para esta sesión
SET search_path TO soporte;

-- ========================================
-- 4. TABLAS MAESTRAS
-- ========================================

-- Roles de usuario
CREATE TABLE soporte.mae_roles (
   n_id_rol SERIAL PRIMARY KEY,
   s_nombre_rol VARCHAR(50) NOT NULL
);

-- Tipos de solicitud
CREATE TABLE soporte.mae_tipos_solicitud (
     n_id_tipo_solicitud SERIAL PRIMARY KEY,
     s_nombre_tipo VARCHAR(100) NOT NULL
);

-- ========================================
-- 5. TABLA DE USUARIOS
-- ========================================
CREATE TABLE soporte.mov_usuarios (
      n_id_usuario SERIAL PRIMARY KEY,
      s_nombre VARCHAR(100) NOT NULL,
      s_correo VARCHAR(100) NOT NULL UNIQUE,
      s_contrasena TEXT NOT NULL,
      n_id_rol INT NOT NULL,
      FOREIGN KEY (n_id_rol) REFERENCES soporte.mae_roles(n_id_rol)
);

-- ========================================
-- 6. TABLA DE SOLICITUDES
-- ========================================
CREATE TABLE soporte.mov_solicitudes (
     n_id_solicitud SERIAL PRIMARY KEY,
     n_id_usuario INT NOT NULL,
     n_id_tipo_solicitud INT NOT NULL,
     s_descripcion TEXT NOT NULL,
     f_fecha_registro TIMESTAMP NOT NULL DEFAULT NOW(),
     s_estado VARCHAR(30) NOT NULL DEFAULT 'Pendiente',
     f_fecha_cierre TIMESTAMP,
     n_id_coordinador INT,
     FOREIGN KEY (n_id_usuario) REFERENCES soporte.mov_usuarios(n_id_usuario),
     FOREIGN KEY (n_id_tipo_solicitud) REFERENCES soporte.mae_tipos_solicitud(n_id_tipo_solicitud),
     FOREIGN KEY (n_id_coordinador) REFERENCES soporte.mov_usuarios(n_id_usuario)
);

-- ========================================
-- 7. ASIGNACIONES A COLABORADORES
-- ========================================
CREATE TABLE soporte.mov_asignaciones (
      n_id_asignacion SERIAL PRIMARY KEY,
      n_id_solicitud INT NOT NULL,
      n_id_colaborador INT NOT NULL,
      b_es_coordinador BOOLEAN DEFAULT FALSE,
      f_fecha_asignacion TIMESTAMP NOT NULL DEFAULT NOW(),
      FOREIGN KEY (n_id_solicitud) REFERENCES soporte.mov_solicitudes(n_id_solicitud),
      FOREIGN KEY (n_id_colaborador) REFERENCES soporte.mov_usuarios(n_id_usuario)
);

-- ========================================
-- 8. ACTIVIDADES DE SEGUIMIENTO
-- ========================================
CREATE TABLE soporte.mov_actividades (
     n_id_actividad SERIAL PRIMARY KEY,
     n_id_asignacion INT NOT NULL,
     f_fecha_actividad DATE NOT NULL,
     s_descripcion TEXT NOT NULL,
     n_horas_trabajadas NUMERIC(4,2) NOT NULL,
     FOREIGN KEY (n_id_asignacion) REFERENCES soporte.mov_asignaciones(n_id_asignacion)
);

-- ========================================
-- 9. NOTIFICACIONES AUTOMÁTICAS
-- ========================================
CREATE TABLE soporte.mov_notificaciones (
    n_id_notificacion SERIAL PRIMARY KEY,
    n_id_usuario INT NOT NULL,
    n_id_solicitud INT NOT NULL,
    s_tipo VARCHAR(50) NOT NULL,
    s_mensaje TEXT,
    f_fecha_envio TIMESTAMP DEFAULT NOW(),
    s_estado VARCHAR(20) DEFAULT 'Enviada',
    FOREIGN KEY (n_id_usuario) REFERENCES soporte.mov_usuarios(n_id_usuario),
    FOREIGN KEY (n_id_solicitud) REFERENCES soporte.mov_solicitudes(n_id_solicitud)
);

-- ========================================
-- 10. BITÁCORA DE ACCESOS
-- ========================================
CREATE TABLE soporte.mov_bitacora_accesos (
      n_id_evento SERIAL PRIMARY KEY,
      n_id_usuario INT NOT NULL,
      s_accion VARCHAR(100) NOT NULL,
      f_fecha_hora TIMESTAMP DEFAULT NOW(),
      s_ip_origen VARCHAR(50),
      FOREIGN KEY (n_id_usuario) REFERENCES soporte.mov_usuarios(n_id_usuario)
);
