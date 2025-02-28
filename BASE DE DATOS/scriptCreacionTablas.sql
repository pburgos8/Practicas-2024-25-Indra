/*
Con superusuario se crea el usuario en el que se crearan las tablas
*/

-- se crea el usuario llamada 'EVENTOS' con contraseña 'EVENTOS'
CREATE USER EVENTOS IDENTIFIED BY EVENTOS;
-- se le da una tamaño de memoria ilimitado al usuario EVENTOS
ALTER USER EVENTOS QUOTA UNLIMITED ON USERS;

-- se le concede al usuario EVENTOS el rol de conectarse a la base de datos (CONNECT)
-- y el rol para crear objetos (RESOURCE)
GRANT CONNECT,RESOURCE TO EVENTOS;

/*
    A PARTIR DE AQUÍ EL SCRIPT SE EJECUTA CON EL USUARIO EVENTOS
*/

-- Las claves primarias numéricas se generaran como campos autoincrementales

-- Primero se crean las tablas que no contengan claves foráneas (ORGANIZADOR,
-- USUARIO, CATEGORIA)

-- se crea la tabla organizador
CREATE TABLE ORGANIZADOR(
    ID_ORGANIZADOR NUMBER(10) GENERATED ALWAYS AS IDENTITY NOT NULL,
    NOMBRE VARCHAR2(20) NOT NULL,
    TELEFONO VARCHAR2(9) NULL,
    CORREO VARCHAR2(50) NOT NULL,

    CONSTRAINT PK_ORGANIZADOR PRIMARY KEY (ID_ORGANIZADOR),
    CONSTRAINT CK_ORGANIZADOR_1 CHECK CORREO LIKE '%@%'
);

-- se crea la tabla usuario
CREATE TABLE USUARIO(
    ID_USUARIO NUMBER(10) GENERATED ALWAYS AS IDENTITY NOT NULL,
    NOMBRE VARCHAR2(10) NOT NULL,
    CONTRASEÑA VARCHAR2(20) NOT NULL,
    CORREO VARCHAR(50) NOT NULL,

    CONSTRAINT PK_USUARIO PRIMARY KEY (ID_USUARIO),
    CONSTRAINT CK_USUARIO_1 CHECK CORREO LIKE '%@%'
);

-- se crea la tabla participante
CREATE TABLE PARTICIPANTE(
    ID_USUARIO NUMBER(10) NOT NULL,
    CONSTRAINT PK_PARTICIPANTE PRIMARY KEY (ID_USUARIO),
    CONSTRAINT FK_PARTICIPANTE_USUARIO FOREIGN KEY (ID_USUARIO) REFERENCES
        USUARIO(ID_USUARIO)
);

-- se crea la tabla categoria
CREATE TABLE CATEGORIA (
    ID_CATEGORIA NUMBER(10) GENERATED ALWAYS AS IDENTITY NOT NULL,
    NOMBRE VARCHAR2(15) NOT NULL,
    
    CONSTRAINT PK_CATEGORIA PRIMARY KEY (ID_CATEGORIA)
);

-- Se crean las tablas con claves foraneas (EVENTO , PARTICIPANTE_EVENTO , EVENTO_LUGAR)

-- se crea la tabla evento
CREATE TABLE EVENTO(
    ID_EVENTO NUMBER(10) GENERATED ALWAYS AS IDENTITY NOT NULL,
    NOMBRE VARCHAR2(30) NOT NULL,
    FECHA DATE NULL,
    DURACION NUMBER(5) NULL,
    LUGAR VARCHAR2(50) NULL,
    ID_ORGANIZADOR NUMBER(10) NOT NULL,
    ID_CATEGORIA NUMBER(10) NOT NULL,

    CONSTRAINT PK_EVENTO PRIMARY KEY (ID_EVENTO),
    CONSTRAINT FK_EVENTO_ORGANIZADOR FOREIGN KEY (ID_ORGANIZADOR) REFERENCES
        ORGANIZADOR(ID_ORGANIZADOR),
    CONSTRAINT FK_EVENTO_CATEGORIA FOREIGN KEY (ID_CATEGORIA) REFERENCES
        CATEGORIA(ID_CATEGORIA)

);

-- se crea la tabla PARTICIPANTE_EVENTO
CREATE TABLE PARTICIPANTE_EVENTO(
    ID_USUARIO NUMBER(10) NOT NULL,
    ID_EVENTO NUMBER(10) NOT NULL,
    
    CONSTRAINT PK_USUARIO_EVENTO PRIMARY KEY (ID_USUARIO,ID_EVENTO),
    CONSTRAINT FK_USUARIO_EVENTO_USUARIO FOREIGN KEY (ID_USUARIO) REFERENCES
        PARTICIPANTE(ID_USUARIO),
    CONSTRAINT FK_USUARIO_EVENTO_EVENTO FOREIGN KEY (ID_EVENTO) REFERENCES  
        EVENTO(ID_EVENTO)
);
